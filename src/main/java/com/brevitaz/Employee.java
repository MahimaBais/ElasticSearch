package com.brevitaz;

public class Employee {

    private String employeeId;
    private String name;
    private String surname;
    private String emailId;
    private String department;
    private String mobileNo;
    private String salary;
    private String expertise;

    public Employee() {
    }

    public Employee(String employeeId, String name, String surname, String emailId, String department, String mobileNo, String salary, String expertise) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.emailId = emailId;
        this.department = department;
        this.mobileNo = mobileNo;
        this.salary = salary;
        this.expertise = expertise;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailId='" + emailId + '\'' +
                ", department='" + department + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", salary='" + salary + '\'' +
                ", expertise='" + expertise + '\'' +
                '}';
    }
}
