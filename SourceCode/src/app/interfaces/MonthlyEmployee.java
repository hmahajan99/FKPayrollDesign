package app.interfaces;

import java.util.Date;

public interface MonthlyEmployee extends Employee {
  void setMonthlySalary(int salary);  
  void setCommisionRate(int rate);  
  void submitSalesReciept(Date date,int amount);

  @Override
  default String getType() {
    return "MonthlyEmployee";
  }

  // int calcSalesPayment();
  // int calcSalaryPayment();

}