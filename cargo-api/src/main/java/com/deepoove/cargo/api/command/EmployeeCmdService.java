package com.deepoove.cargo.api.command;

import com.deepoove.cargo.application.cmd.EmployeeDeleteCommand;
import com.deepoove.cargo.application.cmd.EmployeeSaveCommand;

public interface EmployeeCmdService {
  void save(EmployeeSaveCommand employeeSaveCommand);

  void delete(EmployeeDeleteCommand employeeDeleteCommand);
}
