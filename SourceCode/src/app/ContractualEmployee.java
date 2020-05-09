package app;

public class ContractualEmployee implements HourlyEmployee {
  private Employee emp;
  private int hourlyRate;

  public ContractualEmployee(int id, String name) {
    emp = new Employee(id, name);
    hourlyRate = 0;
  }

  public Employee basicDetails(){
    return emp;
  }

  public void setHourlyRate(int rate) {
    this.hourlyRate = rate;
  }

}