package app;

import java.time.LocalDate;
import java.util.ArrayList;

import app.interfaces.HourlyEmployee;

public class ContractualEmployee implements HourlyEmployee {

  private EmployeeDetails emp;
  private int hourlyRate;
  private int overtimeHour;
  private double rateFactor;
  private LocalDate lastWeeklyPayment;
  private ArrayList<Card> Cards;


  public ContractualEmployee(int id, String name) {
    emp = new EmployeeDetails(id, name);
    lastWeeklyPayment = LocalDate.of(2000, 1, 1);
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
    int overworkedHours = Math.max(numHours-overtimeHour, 0);
    int normalHours = Math.min(numHours,8);
    double amount = normalHours*hourlyRate + overworkedHours*hourlyRate*rateFactor;
    Cards.add(new Card(date, amount, "log: daily time card"));
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
  public String generatePendingPaymentsReciept() {
    // TODO Use stringBuilder
    String reciept="Contractual Employee " + basicDetails().getId() + " : " + basicDetails().getName() + "\n---------------------\n";  
    for(int i=Cards.size()-1;i>=0;i--){
      Card card = Cards.get(i);
      if(card.date.compareTo(lastWeeklyPayment)>0){
        String payment = card.date + " : " + (card.amount>=0?'+':"") + card.amount + " " + card.message;
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
    ce.payWeeklyTill(tomorrow);
    System.out.println(ce.generatePendingPaymentsReciept());
  }

}