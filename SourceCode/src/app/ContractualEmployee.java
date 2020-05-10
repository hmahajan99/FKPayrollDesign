package app;

import java.time.LocalDate;
import java.util.ArrayList;

import app.interfaces.HourlyEmployee;

public class ContractualEmployee implements HourlyEmployee {

  private class Card {
    public LocalDate date;
    public int numHours;

    public Card(LocalDate date, int numHours) {
      this.date = date;
      this.numHours = numHours;
    }
  }

  private EmployeeDetails emp;
  private int hourlyRate;
  private int overtimeHour;
  private double rateFactor;
  private LocalDate lastPayment;
  private ArrayList<Card> Cards;


  public ContractualEmployee(int id, String name) {
    emp = new EmployeeDetails(id, name);
    lastPayment = LocalDate.of(2000, 1, 1);
    Cards = new ArrayList<Card>();
    hourlyRate = 100; // Default
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
  public void setOvertimeHourlyRate(int overtimeHour, double rateFactor) {
    this.overtimeHour = overtimeHour;
    this.rateFactor = rateFactor;
  }

  @Override
  public void submitDailyTimeCard(LocalDate date, int numHours) {
    Cards.add(new Card(date, numHours));
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
    String reciept="Contractual Employee " + basicDetails().getId() + " : " + basicDetails().getName() + "\n---------------------\n";  
    for(int i=Cards.size()-1;i>=0;i--){
      Card card = Cards.get(i);
      if(card.date.compareTo(lastPayment)>0){
        int overworkedHours = Math.max(card.numHours-overtimeHour, 0);
        int normalHours = Math.min(card.numHours,8);
        double amount = normalHours*hourlyRate + overworkedHours*hourlyRate*rateFactor;
        String payment = card.date + " : +" + amount;
        reciept = reciept + payment + "\n";
      }
    }
    return reciept + "---------------------\n";
  }


  public static void testContractualEmployee() {
    System.out.println("-----------------------");
    ContractualEmployee ce = new ContractualEmployee(1, "name");
    System.out.println(ce.basicDetails().getId());
    ce.basicDetails().setName("Pablo");
    System.out.println(ce.basicDetails().getName());
    System.out.println(ce.basicDetails().getPaymentMethod());
    System.out.println(ce.getType());
    System.out.println("-----------------------");
    
    LocalDate date = LocalDate.now();  
    LocalDate yesterday = date.minusDays(1);  
    LocalDate tomorrow = yesterday.plusDays(2);  
    LocalDate tomorrow1 = tomorrow.plusDays(1);  
    LocalDate tomorrow2 = tomorrow.plusDays(2);  

    ce.submitDailyTimeCard(yesterday, 0);
    ce.submitDailyTimeCard(date, 5);
    ce.submitDailyTimeCard(tomorrow, 8);
    ce.submitDailyTimeCard(tomorrow1, 10);
    ce.submitDailyTimeCard(tomorrow2, 1);
    System.out.println(ce.generatePendingPaymentsReciept());
    ce.payTill(tomorrow);
    System.out.println(ce.generatePendingPaymentsReciept());
  }



}