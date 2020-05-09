package app;

import java.util.Date;

import app.interfaces.HourlyEmployee;

public class ContractualEmployee implements HourlyEmployee {
  private EmployeeDetails emp;
  private int hourlyRate;
  private int overtimeHour;
  private double rateFactor;

  public ContractualEmployee(int id, String name) {
    emp = new EmployeeDetails(id, name);
    setOvertimeHourlyRate(8,1.5);
  }

  @Override
  public EmployeeDetails basicDetails() {
    return emp;
  }

  @Override
  public void setHourlyRate(int rate) {
    hourlyRate = rate;
  }

  @Override
  public void submitDailyTimeCard(Date date, int numHours) {
    // TODO
  }

  @Override
  public void setOvertimeHourlyRate(int overtimeHour, double rateFactor) {
    this.overtimeHour = overtimeHour;
    this.rateFactor = rateFactor;
  }

  public static void testContractualEmployee() {
    System.out.println("-----------------------");
    ContractualEmployee ce = new ContractualEmployee(1, "name");
    System.out.println(ce.basicDetails().getId());
    ce.basicDetails().setName("Contractual Employee");
    System.out.println(ce.basicDetails().getName());
    System.out.println(ce.basicDetails().getPaymentMethod());
    System.out.println(ce.getType());
    System.out.println("-----------------------");
  }


}