package app.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public interface PayrollSystem {
  int addEmployee(String name, String type); // TODO: Update employee also
  Employee getEmployee(int employeeId);
  HourlyEmployee getHourlyEmployee(int employeeId);
  MonthlyEmployee getMonthlyEmployee(int employeeId);
  void removeEmployee(int employeeId);
  Set<Integer> getAllEmployeeIds();
  ArrayList<String> getAllEmployeeIdsWithNames();
  
  void addUnion(String unionName);
  Union getUnion(String unionName);
  void removeUnion(String unionName);
  Set<String> getAllUnionNames();

  void addEmployeeToUnion(int employeeId, String unionName);
  void removeEmployeeFromUnion(int employeeId, String unionName);

  void payEmployeeWeeklyCards(int employeeId,LocalDate payDate);
  void payEmployeeSalary(int employeeId,LocalDate payDate);
  String generateEmployeePendingPaymentsReciept(int employeeId);
  void addUnionCharge(int employeeId,String unionName,LocalDate date,int charge,String message);
  void addUnionChargeOnAll(String unionName, LocalDate date,int charge,String message);

  void setEmployeeHourlyRate(int employeeId, int rate);  
  void submitEmployeeDailyTimeCard(int employeeId, LocalDate date,int numHours);

  void setEmployeeMonthlySalary(int employeeId, int salary);  
  void setEmployeeCommisionRate(int employeeId, int rate);  
  void submitEmployeeSalesReciept(int employeeId, LocalDate date,int amount);


}