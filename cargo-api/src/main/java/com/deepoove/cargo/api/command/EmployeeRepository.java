package com.deepoove.cargo.api.command;

import com.deepoove.cargo.domain.aggregate.employee.Employee;

public interface EmployeeRepository {
  void save(Employee employee);

  void delete(int id);
}
