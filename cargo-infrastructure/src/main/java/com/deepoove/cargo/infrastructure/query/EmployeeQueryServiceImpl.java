package com.deepoove.cargo.infrastructure.query;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.deepoove.Cache;
import com.deepoove.cargo.api.query.EmployeeQueryService;
import com.deepoove.cargo.domain.aggregate.employee.Employee;
import com.deepoove.cargo.infrastructure.assembler.EmployeeDTOAssembler;
import com.deepoove.cargo.infrastructure.db.dataobject.EmployeeDO;
import com.deepoove.cargo.infrastructure.db.mapper.EmployeeMapper;

import org.deepoove.cargo.query.dto.EmployeeDTO;
import org.deepoove.cargo.query.qry.EmployeeFindByNameQry;
import org.deepoove.cargo.query.qry.EmployeeFindByTitleQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {

  @Autowired
  private EmployeeMapper employeeMapper;

  @Autowired
  private EmployeeDTOAssembler converter;

  @Override
  public List<EmployeeDTO> queryEmployees() {
    // Employee e = (Employee) Cache.redis.get("new");
    // EmployeeDO eo =
    // EmployeeDO.builder().id(e.getId()).name(e.getName()).gender(e.getGender()).title(e.getTitle())
    // .yearsOfService(e.getYearsOfService()).age(e.getAge()).build();
    // List<EmployeeDO> lst = Arrays.asList(eo);
    List<EmployeeDO> employees = employeeMapper.all();
    return employees.stream().map(converter::apply).collect(Collectors.toList());
  }

  @Override
  public List<EmployeeDTO> queryEmployees(EmployeeFindByNameQry qry) {
    List<EmployeeDO> employees = employeeMapper.selectByName(qry.getName());
    return employees.stream().map(converter::apply).collect(Collectors.toList());
  }

  @Override
  public EmployeeDTO getEmployee(int id) {
    EmployeeDO employeeDO = employeeMapper.select(id);
    return converter.apply(employeeDO);
  }

  @Override
  public List<EmployeeDTO> queryEmployees(EmployeeFindByTitleQry qry) {
    List<EmployeeDO> employees = employeeMapper.selectByTitle(qry.getTitle());
    return employees.stream().map(converter::apply).collect(Collectors.toList());
  }

}
