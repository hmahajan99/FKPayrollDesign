package app.interfaces;

import java.time.LocalDate;

public interface MonthlyEmployee extends Employee {
  void setMonthlySalary(int salary);  
  void setCommisionRate(int rate);  
  void submitSalesReciept(LocalDate date,int amount);
  LocalDate getLastMonthlyPayment(); 
  void payMonthlyTill(LocalDate payDate);

  @Override
  default String getType() {
    return "MonthlyEmployee";
  }

}