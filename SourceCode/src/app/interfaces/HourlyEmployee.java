package app.interfaces;

import java.util.Date;

public interface HourlyEmployee extends Employee {
  void setHourlyRate(int rate);  
  void setOvertimeHourlyRate(int overtimeHour,double rateFactor);  // 8,1.5
  void submitDailyTimeCard(Date date,int numHours);

  @Override
  default String getType() {
    return "HourlyEmployee";
  }

  // int calcPayment();
}