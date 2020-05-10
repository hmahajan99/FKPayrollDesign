package app.interfaces;

import java.time.LocalDate;

public interface MonthlyEmployee extends Employee {
  void setMonthlySalary(int salary);  
  void setCommisionRate(int rate);  
  void submitSalesReciept(LocalDate date,int amount);

  @Override
  default String getType() {
    return "MonthlyEmployee";
  }

  // int calcSalesPayment();
  // int calcSalaryPayment();

}