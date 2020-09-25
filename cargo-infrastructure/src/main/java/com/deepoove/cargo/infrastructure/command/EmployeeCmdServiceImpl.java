package com.deepoove.cargo.infrastructure.command;

import com.deepoove.cargo.api.command.EmployeeCmdService;
import com.deepoove.cargo.api.command.EmployeeRepository;
import com.deepoove.cargo.application.cmd.EmployeeDeleteCommand;
import com.deepoove.cargo.application.cmd.EmployeeSaveCommand;
import com.deepoove.cargo.domain.aggregate.employee.Employee;
import com.deepoove.cargo.domain.aggregate.employee.EmployeeDeleteDomainEvent;
import com.deepoove.cargo.domain.aggregate.employee.EmployeeSaveDomainEvent;
import com.deepoove.cargo.shared.DomainEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCmdServiceImpl implements EmployeeCmdService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private DomainEventPublisher domainEventPublisher;

  @Override
  public void save(EmployeeSaveCommand employeeSaveCommand) {
    Employee employee = Employee.builder().name(employeeSaveCommand.getName()).title(employeeSaveCommand.getTitle())
        .gender(employeeSaveCommand.getGender()).yearsOfService(employeeSaveCommand.getYearsOfService())
        .age(employeeSaveCommand.getAge()).build();

    employeeRepository.save(employee);

    domainEventPublisher.publish(new EmployeeSaveDomainEvent(employee));
  }

  @Override
  public void delete(EmployeeDeleteCommand employeeDeleteCommand) {
    employeeRepository.delete(employeeDeleteCommand.getId());

    domainEventPublisher.publish(new EmployeeDeleteDomainEvent(employeeDeleteCommand.getId()));
  }

}
