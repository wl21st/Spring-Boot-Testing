package com.howtodoinjava.employees;

import com.howtodoinjava.employees.controllers.EmployeeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeesApplicationTests {

  @Autowired
  EmployeeController employeeController;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(employeeController);
  }
}
