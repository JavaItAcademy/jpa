import entities.Employee;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class Criteria {
    public static void main(String[] args) {
        //deleteAll("EmployeeAddress");
        //fetch();
        nonFetch();

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
