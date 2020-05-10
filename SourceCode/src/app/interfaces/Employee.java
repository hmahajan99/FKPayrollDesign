package app.interfaces;

import java.time.LocalDate;

import app.EmployeeDetails;

public interface Employee {
  EmployeeDetails basicDetails();
  String getType(); 
  LocalDate getLastPayment(); 
  void payTill(LocalDate payLocalDate);
  String generatePendingPaymentsReciept();

}