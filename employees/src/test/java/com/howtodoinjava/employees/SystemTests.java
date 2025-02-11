package com.howtodoinjava.employees;

import com.howtodoinjava.employees.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

class SystemTests {

  @Test
  void testCreateReadDelete() {
    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8080/employee";

    Employee employee = new Employee("Lokesh", "Gupta");
    ResponseEntity<Employee> entity = restTemplate.postForEntity(url, employee, Employee.class);

    Employee[] employees = restTemplate.getForObject(url, Employee[].class);
    Assertions.assertThat(employees).extracting(Employee::getFirstName).containsOnly("Lokesh");

    restTemplate.delete(url + "/" + entity.getBody().getId());
    Assertions.assertThat(restTemplate.getForObject(url, Employee[].class)).isEmpty();
  }

  @Test
  void testErrorHandlingReturnsBadRequest() {

    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8080/wrong";

    try {
      restTemplate.getForEntity(url, String.class);
    } catch (HttpClientErrorException e) {
      Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
  }

}
