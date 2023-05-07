package com.abii.springboot.sb_project.dao;

import com.abii.springboot.sb_project.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    void deleteEmployee(int id);
}
