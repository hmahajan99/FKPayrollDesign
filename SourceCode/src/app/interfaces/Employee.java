package app.interfaces;

import app.EmployeeDetails;

public interface Employee {
  EmployeeDetails basicDetails();
  String getType(); 
}