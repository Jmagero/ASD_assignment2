package org.edu.miu.asd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee( 1L, "Daniel", "Agar", LocalDate.parse("2018-01-17"),105945.50);
        Employee employee2 = new Employee(2L, "Benard",  "Shaw", LocalDate.parse("2019-05-03"), 197750.00 );
        Employee employee3 = new Employee(4L, "Carly",  "Agar", LocalDate.parse("2019-05-02"), 74500.00 );
        Employee employee4 = new Employee(3L, "Wesley",  "Schneider", LocalDate.parse("2019-05-02"), 197750.00 );
        PensionPlan pensionPlan = new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), 100.00);
        PensionPlan pensionPlan1 = new PensionPlan("SM2307", LocalDate.parse("2019-11-04"),1555.50 );
        employee1.setPensionPlan(pensionPlan);
        employee3.setPensionPlan(pensionPlan1);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        System.out.println(employees);
        System.out.println("***************");
        List<Employee> sortedEmployees = employees.stream().sorted(Comparator.comparing(Employee::getLastName))
                        .sorted(Comparator.comparing((Employee::getYearlySalary)).reversed())
                .collect(Collectors.toList());
        System.out.println("Sorted Employees *********");
        System.out.println(sortedEmployees);
        System.out.println("Monthly Pension plan *********");
        for( Employee employee : employees){
            if(employee.isEligible()){
                System.out.println(employee);
            }

        }
    }
}