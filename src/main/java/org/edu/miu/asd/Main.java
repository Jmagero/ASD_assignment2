package org.edu.miu.asd;

import org.stringtemplate.v4.ST;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = getEmployees();
        System.out.println(employees);
        System.out.println("***************");
        List<Employee> sortedEmployees = employees.stream().sorted(Comparator.comparing(Employee::getLastName))
                        .sorted(Comparator.comparing((Employee::getYearlySalary)).reversed())
                .collect(Collectors.toList());

        System.out.println("Sorted Employees *********");
        printEmployees(sortedEmployees);

        System.out.println("Monthly Pension plan *********");

        printEmployees(getMonthlyUpcomingEnrollees(sortedEmployees));

    }

    private static List<Employee> getEmployees() {
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
        return employees;
    }

    private static  void printEmployees(List<Employee> employees){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            // Convert list of employees to JSON string
            String jsonString = objectMapper.writeValueAsString(employees);
            // Print the JSON string
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> getMonthlyUpcomingEnrollees(List<Employee> employees) {
        LocalDate now = LocalDate.now();
        LocalDate nextMonthStart = now.plusMonths(1).withDayOfMonth(1);
        LocalDate nextMonthEnd = nextMonthStart.withDayOfMonth(nextMonthStart.lengthOfMonth());

        return employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> ChronoUnit.YEARS.between(e.getEmploymentDate(), nextMonthEnd) >= 5)
                .collect(Collectors.toList());
    }
}