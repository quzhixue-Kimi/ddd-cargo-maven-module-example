package com.deepoove.cargo.api.query;

import java.util.List;

import org.deepoove.cargo.query.dto.EmployeeDTO;
import org.deepoove.cargo.query.qry.EmployeeFindByNameQry;
import org.deepoove.cargo.query.qry.EmployeeFindByTitleQry;

public interface EmployeeQueryService {
  List<EmployeeDTO> queryEmployees();

  List<EmployeeDTO> queryEmployees(EmployeeFindByNameQry qry);

  List<EmployeeDTO> queryEmployees(EmployeeFindByTitleQry qry);

  EmployeeDTO getEmployee(int id);
}
