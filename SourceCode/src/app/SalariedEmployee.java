package app;

import java.util.Date;

import app.interfaces.MonthlyEmployee;

public class SalariedEmployee implements MonthlyEmployee {
  private EmployeeDetails emp;
  private int salary;
  private int commisionRate;

  public SalariedEmployee(int id, String name) {
    emp = new EmployeeDetails(id, name);
    salary = 0;
  }

  @Override
  public EmployeeDetails basicDetails() {
    return emp;
  }

  @Override
  public void setMonthlySalary(int salary) {
    this.salary = salary;
  }

  @Override
  public void setCommisionRate(int rate) {
    commisionRate = rate;
  }

  @Override
  public void submitSalesReciept(Date date, int amount) {
    // TODO
  }

  public static void testSalariedEmployee() {
    System.out.println("-----------------------");
    SalariedEmployee se = new SalariedEmployee(2, "name");
    System.out.println(se.basicDetails().getId());
    se.basicDetails().setName("Salaried Employee");
    System.out.println(se.basicDetails().getName());
    System.out.println(se.basicDetails().getPaymentMethod());
    System.out.println("-----------------------");
  }  

}