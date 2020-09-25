package com.deepoove.cargo.application.cmd;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class EmployeeSaveCommand {
  private String name;
  private int age;
  private String gender;
  private String title;
  private int yearsOfService;
}
