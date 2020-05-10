package app;

import java.time.LocalDate;

import app.interfaces.Employee;
import app.interfaces.Union;

public class TestingSystem {
  
  public static void testPayrollManagementSystem() {
    PayrollManagementSystem pms = new PayrollManagementSystem();
    int id1 = pms.addEmployee("Aman", "HourlyEmployee");
    int id2 = pms.addEmployee("Baman", "MonthlyEmployee");
    int id3 = pms.addEmployee("Batman", "MonthlyEmployee");
    int id4 = pms.addEmployee("Don", "MonthlyEmployee");
    int id5 = pms.addEmployee("Pablo", "HourlyEmployee");
    int id6 = pms.addEmployee("Escobar", "HourlyEmployee");
    int id7 = pms.addEmployee("Gabbar", "HourlyEmployee");
    System.out.println("--------------------------");
    System.out.println("Testing getAllEmployeeIds()");
    for (Integer employeeId : pms.getAllEmployeeIds()) {
      System.out.println(employeeId);
    }
    pms.removeEmployee(id7);
    System.out.println("Testing getAllEmployeeIdsWithNames()");
    for (String s : pms.getAllEmployeeIdsWithNames()) {
      System.out.println(s);
    }

    pms.addUnion("SportsUnion");
    pms.addUnion("LabourUnion");
    pms.addUnion("SleepingUnion");
    pms.addEmployeeToUnion(id1, "SportsUnion");
    pms.addEmployeeToUnion(id2, "SportsUnion");
    pms.addEmployeeToUnion(id3, "SportsUnion");
    pms.addEmployeeToUnion(id3, "LabourUnion");
    pms.addEmployeeToUnion(id4, "LabourUnion");
    pms.addEmployeeToUnion(id5, "LabourUnion");
    pms.addEmployeeToUnion(id1, "SleepingUnion");
    pms.addEmployeeToUnion(id3, "SleepingUnion");
    pms.addEmployeeToUnion(id6, "SleepingUnion");

    pms.removeEmployee(id1);
    Union sleepingUnion = pms.getUnion("SleepingUnion");
    Union sportsUnion = pms.getUnion("SportsUnion");
    System.out.println("Members of SportsUnion");
    for (Integer employeeId : sportsUnion.getMembers())
      System.out.println(employeeId);
    System.out.println("Members of sleepingUnion");
    for (Integer employeeId : sleepingUnion.getMembers())
      System.out.println(employeeId);

    Employee e3 = pms.getEmployee(id3);
    System.out.println("Unions of e3");
    for (String unionName : e3.basicDetails().getUnions())
      System.out.println(unionName);
    pms.removeUnion("LabourUnion");
    System.out.println("Unions of e3 after deleting LabourUnion");
    for (String unionName : e3.basicDetails().getUnions())
      System.out.println(unionName);
    pms.removeEmployeeFromUnion(id3, "SportsUnion");
    System.out.println("Unions of e3 after removing membership from SportsUnion");
    for (String unionName : e3.basicDetails().getUnions())
      System.out.println(unionName);

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
    se.payMonthlyTill(date);
    se.payWeeklyTill(tomorrow);
    System.out.println(se.generatePendingPaymentsReciept());

    System.out.println("-----------------------");
  }

  public static void testEmployeeUnion(){
    SalariedEmployee m1 = new SalariedEmployee(10, "Semp1");
    ContractualEmployee m2 = new ContractualEmployee(11, "Cemp1");
    ContractualEmployee m3 = new ContractualEmployee(12, "Cemp2");
    SalariedEmployee m4 = new SalariedEmployee(13, "Semp2");
    EmployeeUnion u = new EmployeeUnion("EmployeeUnion");
    u.addMember(m1);
    u.addMember(m2);
    u.addMember(m3);
    u.addMember(m4);
    System.out.println("-----------------");
    System.out.println("Members ids of members of EmployeeUnion");
    for(Integer mid: u.getMembers()) System.out.println("Member id: " + mid);
    u.removeMember(12);
    System.out.println("Members ids of members of EmployeeUnion removing m3");
    for(Integer mid: u.getMembers()) System.out.println("Member id: " + mid);

    LocalDate date = LocalDate.now();  
    LocalDate yesterday = date.minusDays(1);  
    LocalDate tomorrow = yesterday.plusDays(2);  
    LocalDate tomorrow2 = tomorrow.plusDays(2);  

    m1.submitSalesReciept(date, 3);
    m1.submitSalesReciept(tomorrow2, 5);
    m2.submitDailyTimeCard(yesterday, 9);
    m2.submitDailyTimeCard(date, 5);
    u.levyChargeOnAll(date, 150, "log : Union(" + u.getUnionName() + ") festivities charge");
    u.levyCharge(10, date, 100, "log : Union(" + u.getUnionName() + ") weekly due");

    System.out.println(m1.generatePendingPaymentsReciept());
    System.out.println(m2.generatePendingPaymentsReciept());
    m1.payWeeklyTill(tomorrow);
    System.out.println(m1.generatePendingPaymentsReciept());

    System.out.println("-----------------");
  }

  public static void testEmployeeDetails() {
    System.out.println("-----------------------");
    EmployeeDetails e = new EmployeeDetails(0, "");
    System.out.println(e.getId());
    e.setName("Vegeta");
    System.out.println(e.getName());
    System.out.println(e.getPaymentMethod());
    e.addUnion("abc");
    e.addUnion("def");
    e.addUnion("xyz");
    e.removeUnion("def");
    e.removeUnion("def");
    e.removeUnion("notExists");
    System.out.println("Unions e is part of");
    for(String union: e.getUnions()) System.out.println(union);
    System.out.println("-----------------------");
  }

  public static void main(String[] args) throws Exception {
      test();
  }

  public static void test() {
    // Uncomment Module you want to test
    testPayrollManagementSystem();
    // testContractualEmployee();
    // testSalariedEmployee();
    // testEmployeeUnion();
    // testEmployeeDetails();
  }


}