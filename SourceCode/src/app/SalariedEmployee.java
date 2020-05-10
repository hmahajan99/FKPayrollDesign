package app;

import java.time.LocalDate;
import java.util.ArrayList;

import app.interfaces.MonthlyEmployee;

public class SalariedEmployee implements MonthlyEmployee {

  private class Card {
    public LocalDate date;
    public int amount;

    public Card(LocalDate date, int amount) {
      this.date = date;
      this.amount = amount;
    }
  }

  private EmployeeDetails emp;
  private int salary;
  private int commisionRate;
  private LocalDate lastPayment;
  private ArrayList<Card> Cards;

  public SalariedEmployee(int id, String name) {
    emp = new EmployeeDetails(id, name);
    lastPayment = LocalDate.of(2000, 1, 1);
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
    Cards.add(new Card(date, amount));
  }

  @Override
  public LocalDate getLastPayment() {
    return lastPayment;
  }

  @Override
  public void payTill(LocalDate payDate) {
    this.lastPayment = payDate;
  }

  @Override
  public String generatePendingPaymentsReciept() {
    // TODO Use stringBuilder
    String reciept="Salaried Employee " + basicDetails().getId() + " : " + basicDetails().getName() + "\n---------------------\n";  
    reciept = reciept + "Salary : " + salary + " per month, Last Paid on: " + lastPayment + "\n";
    reciept = reciept + "Sales Reciepts" + ":-\n" ;
    for(int i=Cards.size()-1;i>=0;i--){
      Card card = Cards.get(i);
      if(card.date.compareTo(lastPayment)>0){
        double amount = card.amount*commisionRate;
        String payment = card.date + " : +" + amount;
        reciept = reciept + payment + "\n";
      }
    }
    return reciept + "---------------------\n";
  }


  public static void testSalariedEmployee() {
    System.out.println("-----------------------");
    SalariedEmployee se = new SalariedEmployee(2, "name");
    System.out.println(se.basicDetails().getId());
    se.basicDetails().setName("Escobar");
    System.out.println(se.basicDetails().getName());
    System.out.println(se.basicDetails().getPaymentMethod());

    LocalDate date = LocalDate.now();  
    LocalDate yesterday = date.minusDays(1);  
    LocalDate tomorrow = yesterday.plusDays(2);  
    LocalDate tomorrow1 = tomorrow.plusDays(1);  
    LocalDate tomorrow2 = tomorrow.plusDays(2);  

    se.submitSalesReciept(yesterday, 0);
    se.submitSalesReciept(date, 5);
    se.submitSalesReciept(tomorrow, 8);
    se.submitSalesReciept(tomorrow1, 10);
    se.submitSalesReciept(tomorrow2, 1);
    System.out.println(se.generatePendingPaymentsReciept());
    se.payTill(tomorrow);
    System.out.println(se.generatePendingPaymentsReciept());

    System.out.println("-----------------------");
  }

  


}