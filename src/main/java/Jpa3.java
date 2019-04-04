import entities.Department;
import entities.Employee;
import entities.EmployeeAddress;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class Jpa3 {
    public static void main(String[] args){
        System.out.println();
        combinedObject("Kyrgyz Republic");
        //getDepartmetnsByCountry("Kyrgyz Republic");
//        System.out.println(getEmployeesByParams("t%", 18));
//        System.out.println(getEmployeesByParams("Japan", "Marketing"));
}

    public static void combinedObject(String countryName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery(
                "select e.name," +
                        "e.country.name, " +
                        "e.department.name " +
                        "FROM Employee e where e.country.name = :p_countryName ")
                .setParameter("p_countryName", countryName)
                .list();
        session.close();
        System.out.println(list);
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
    public static void getDepartmetnsByCountry (String countryName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Department> deps  = session.createQuery(
                "select distinct e.department " +
                        "from Employee e " +
                        "where e.country.name = :p_countryName ")
                .setParameter("p_countryName", countryName )
                .list();
        session.close();
        System.out.println(deps);
    }

    public static List<Employee> getEmployeesByParams (String countryName, String departmentName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> list = session.createQuery(
                "FROM Employee e " +
                        "where e.country.name = :p_country_name " +
                        "and e.department.name = :p_dep_name")
                .setParameter("p_country_name", countryName)
                .setParameter("p_dep_name", departmentName )
                .list();
        session.close();
        return list;
    }


}
