package com.howtodoinjava.employees.services;

import com.howtodoinjava.employees.dao.EmployeeRepository;
import com.howtodoinjava.employees.handlers.RecordNotFoundException;
import com.howtodoinjava.employees.model.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  public Employee save(Employee employee) {
    if (employee.getId() == null) {
      employee = employeeRepository.save(employee);
      return employee;
    } else {
      Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

      if (employeeOptional.isPresent()) {
        Employee newEntity = employeeOptional.get();
        newEntity.setFirstName(employee.getFirstName());
        newEntity.setLastName(employee.getLastName());

        newEntity = employeeRepository.save(newEntity);

        return newEntity;
      } else {
        throw new RecordNotFoundException("No employee record exist for given id");
      }
    }
  }

  public List<Employee> findAll() {
    List<Employee> result = (List<Employee>) employeeRepository.findAll();

    if (!result.isEmpty()) {
      return result;
    } else {
      return new ArrayList<>();
    }
  }

  public void deleteById(Integer id) {
    Optional<Employee> employee = employeeRepository.findById(id);

    if (employee.isPresent()) {
      employeeRepository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No employee record exist for given id");
    }
  }

  void deleteAll() {
    employeeRepository.deleteAll();
  }
}
