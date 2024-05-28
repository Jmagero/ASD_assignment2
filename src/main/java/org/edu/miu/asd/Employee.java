package org.edu.miu.asd;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private long employeeId;
    private  String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private Double yearlySalary;
    private PensionPlan pensionPlan;

    public Employee() {
    }

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, Double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    public Employee(String firstName, String lastName, LocalDate employmentDate, Double yearlySalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, Double yearlySalary, PensionPlan pensionPlan) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = pensionPlan;
    }

    public  boolean isEligible(){
        int year = this.getEmploymentDate().getYear() - LocalDate.now().getYear();
        int dayOfMonth = this.getEmploymentDate().getDayOfMonth();
        return year > 5 || dayOfMonth < 5 || dayOfMonth > 25;
    }
}
