package app;

import java.time.LocalDate;
import java.util.ArrayList;

import app.interfaces.MonthlyEmployee;

public class SalariedEmployee implements MonthlyEmployee {

  private EmployeeDetails emp;
  private int salary;
  private int commisionRate;
  private LocalDate lastWeeklyPayment;
  private LocalDate lastMonthlyPayment;
  private ArrayList<Card> Cards;

  public SalariedEmployee(int id, String name) {
    emp = new EmployeeDetails(id, name);
    lastWeeklyPayment = LocalDate.of(2000, 1, 1);
    lastMonthlyPayment = LocalDate.of(2000, 1, 1);
    Cards = new ArrayList<Card>();
    salary = 10_000; // Default
    commisionRate = 100; // Default
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
  public void submitSalesReciept(LocalDate date, int amount) {
    Cards.add(new Card(date, amount*commisionRate, "log: sales reciept"));
  }

  @Override
  public void addUnionCharge(LocalDate date, int amount, String message) {
    Cards.add(new Card(date, amount, message));
  }

  @Override
  public LocalDate getLastWeeklyPayment() {
    return lastWeeklyPayment;
  }

  @Override
  public void payWeeklyTill(LocalDate payDate) {
    // TODO: Can remove Cards whose date has expired
    this.lastWeeklyPayment = payDate;
  }

  @Override
  public LocalDate getLastMonthlyPayment() {
    return lastMonthlyPayment;
  }

  @Override
  public void payMonthlyTill(LocalDate payDate) {
    this.lastMonthlyPayment = payDate;
  }

  @Override
  public String generatePendingPaymentsReciept() {
    // TODO Use stringBuilder
    String reciept="Salaried Employee " + basicDetails().getId() + " : " + basicDetails().getName() + "\n---------------------\n";  
    reciept = reciept + "Salary : " + salary + " per month, Last Paid on: " + lastMonthlyPayment + "\n";
    reciept = reciept + "Sales Reciepts" + ":-\n" ;
    for(int i=Cards.size()-1;i>=0;i--){
      Card card = Cards.get(i);
      if(card.date.compareTo(lastWeeklyPayment)>0){
        String payment = card.date + " : " + (card.amount>=0?'+':"") + card.amount + " " + card.message;;
        reciept = reciept + payment + "\n";
      }
    }
    return reciept + "---------------------\n";
  }

}