package entities;

import javax.persistence.*;

@Entity
@Table(name = "employee_address")
public class EmployeeAddress {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeeAddress() {
    }

    public EmployeeAddress(int id, String address, Employee employee) {
        this.id = id;
        this.address = address;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", employee=" + employee +
                '}';
    }
}
