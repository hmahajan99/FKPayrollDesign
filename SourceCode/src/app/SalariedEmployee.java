package app;

public class SalariedEmployee implements MonthlyEmployee {
  private Employee emp;
  private int salary;

  public SalariedEmployee(int id, String name) {
    emp = new Employee(id, name);
    salary = 0;
  }

  public Employee basicDetails(){
    return emp;
  }

  public void setMonthlySalary(int salary) {
    this.salary = salary;
  }

  public int calcSalaryPayment() {
    return salary*30;
  }
}