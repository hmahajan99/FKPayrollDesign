package app.interfaces;

import java.time.LocalDate;

import app.EmployeeDetails;

public interface Employee {
  EmployeeDetails basicDetails();
  String getType(); 
  LocalDate getLastWeeklyPayment(); 
  void payWeeklyTill(LocalDate payDate);
  String generatePendingPaymentsReciept();
  void addUnionCharge(LocalDate date,int amount,String message);
}