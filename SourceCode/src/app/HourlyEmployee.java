package app;

public interface HourlyEmployee extends Employee {
  void setHourlyRate(int rate);  
  int calcPayment();

  // void setOvertimeHourlyRate(int afterTime,int times);  // 8,1.5
  // void submitDailyTimeCard(Card card);

}