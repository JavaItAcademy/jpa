import entities.Country;
import entities.Department;
import entities.Employee;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class Criteria {
    public static void main(String[] args) {

        //printQuery();
        criteriaJoin();
        //fetch();
        //nonFetch();

    }

    public static void printQuery() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> rootEmployee = query.from(Employee.class);
//        query.select(rootEmployee).where(
//                cb.and(cb.like(rootEmployee.get("country").get("name"),"%K%"),
//                        cb.greaterThan(rootEmployee.get("age"),18),
//                        cb.lessThanOrEqualTo(rootEmployee.get("age"),60)));
        query.select(rootEmployee).where(
                cb.or(cb.equal(rootEmployee.get("country").get("name"),"US"),
                        cb.equal(rootEmployee.get("department").get("name"),"IT")));

        TypedQuery<Employee> employeeTypedQuery = session.createQuery(query);
        List<Employee> employeeList = employeeTypedQuery.getResultList();
        for (Employee employee : employeeList){
            System.out.println(employee);
        }
        session.close();
        HibernateUtil.shutdown();
    }

    public static void criteriaJoin(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Employee> rootEmployee = query.from(Employee.class);
        Join<Employee, Country> country = rootEmployee.join("country");
        Join<Employee, Department> dept = rootEmployee.join("department");

        query.multiselect(rootEmployee.get("name"),
                rootEmployee.get("age"),
                country.get("name"),
                dept.get("name"));
        Query qry = session.createQuery(query);
        List<Object[]> fieldsList = qry.getResultList();

        for (Object[] object : fieldsList){
            System.out.println(object[0] + " " + object[1]
                    + " "+ object[2]+ " "+ object[3]);
        }

        session.close();
        HibernateUtil.shutdown();
    }

    public static void fetch(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e join fetch e.department");
        List<Employee> employeeList = query.getResultList();
        for(Employee d : employeeList){
            System.out.println(d.getId() + " " + d.getName() + " "+ d.getDepartment().getName());
        }
        session.close();
        HibernateUtil.shutdown();
    }

    public static void nonFetch(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "select e from Employee e join e.department");
        List<Employee> employeeList = query.getResultList();
        for(Employee d : employeeList){
            System.out.println(d.getId() + " " + d.getName() + " "+ d.getDepartment().getName());
        }

        session.close();
        HibernateUtil.shutdown();
    }
}
