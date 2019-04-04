import entities.Employee;
import entities.EmployeeAddress;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class Application {
    public static void main(String[] args){
        //deleteAll("EmployeeAddress");
        //deleteAll("Employee");
        Employee e1 = new Employee(1, "one", 56);
        Employee e2 = new Employee(2, "two", 46);
        Employee e3 = new Employee(3, "three", 36);

//        create(e1);
//        create(e2);
//        create(e3);
        System.out.println();

        System.out.println(getEmployeesByParams("t%", 18));
//
//        EmployeeAddress ea = new EmployeeAddress(1,"Chui 4", e1);
//        EmployeeAddress ea2 = new EmployeeAddress(2,"Chui 5", e1);
//        createEmployeeAddress(ea);
//        createEmployeeAddress(ea2);
//
//        List<Employee> list = read();
//        list.stream().forEach(System.out::println);
    }

    public static List<Employee> getEmployeesByParams (String firstLetter, int age){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> list = session.createQuery(
                "FROM Employee where name like :p_name and age > :p_age")
                .setParameter("p_name", firstLetter)
                .setParameter("p_age", age )
                .list();
        session.close();
        return list;
    }

    public static Integer createEmployeeAddress(EmployeeAddress ea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(ea);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created: " + ea);
        return ea.getId();
    }

    public static Integer create(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created: " + e);
        return e.getId();
    }

    public static List<Employee> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createQuery("FROM Employee").list();
        session.close();
        //System.out.println("Found " + employees.size() + " employees");
        return employees;
    }

    public static void update(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee em = (Employee) session.load(Employee.class, e.getId());
        em.setName(e.getName());
        em.setAge(e.getAge());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated: " + e.toString());
    }

    public static void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee em = findByID(id);
        session.delete(em);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted: " + em);
    }

    public static Employee findByID(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee em = (Employee) session.load(Employee.class, id);
        session.close();
        return em;
    }

    public static void deleteAll(String table) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM " + table);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("******** DELETED ALL ********");
    }
}
