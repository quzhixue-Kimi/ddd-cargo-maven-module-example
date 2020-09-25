package com.deepoove.cargo.infrastructure.web.controller;

import java.util.List;

import com.deepoove.cargo.api.command.EmployeeCmdService;
import com.deepoove.cargo.api.query.EmployeeQueryService;
import com.deepoove.cargo.application.cmd.EmployeeDeleteCommand;
import com.deepoove.cargo.application.cmd.EmployeeSaveCommand;

import org.deepoove.cargo.query.dto.EmployeeDTO;
import org.deepoove.cargo.query.qry.EmployeeFindByNameQry;
import org.deepoove.cargo.query.qry.EmployeeFindByTitleQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

  @Autowired
  private EmployeeCmdService employeeCmdService;

  @Autowired
  private EmployeeQueryService employeeQueryService;

  @GetMapping(value = "/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping()
  public List<EmployeeDTO> queryEmployees(@RequestParam(value = "name", required = false) String name) {
    if (!StringUtils.isEmpty(name)) {
      EmployeeFindByNameQry qry = EmployeeFindByNameQry.builder().name(name).build();
      return employeeQueryService.queryEmployees(qry);
    }
    return employeeQueryService.queryEmployees();
  }

  @GetMapping(value = "/t")
  public List<EmployeeDTO> queryEmployeesByTitle(@RequestParam(value = "title", required = false) String title) {
    EmployeeFindByTitleQry qry = EmployeeFindByTitleQry.builder().title(title).build();
    return employeeQueryService.queryEmployees(qry);
  }

  @GetMapping(value = "/{id}")
  public EmployeeDTO getEmployee(@PathVariable(name = "id", required = true) int id) {
    return employeeQueryService.getEmployee(id);
  }

  @PostMapping()
  public void save(@RequestBody EmployeeSaveCommand employeeSaveCommand) {
    employeeCmdService.save(employeeSaveCommand);
    employeeQueryService.queryEmployees();
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable(name = "id", required = true) int id) {
    EmployeeDeleteCommand command = EmployeeDeleteCommand.builder().id(id).build();
    employeeCmdService.delete(command);
  }

}
