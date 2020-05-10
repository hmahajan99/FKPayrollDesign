package app;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import app.interfaces.Employee;
import app.interfaces.Union;

public class EmployeeUnion implements Union {
  private String unionName;
  private HashMap<Integer, Employee> members; 

  public EmployeeUnion(String unionName) {
    this.unionName = unionName;
    members = new HashMap<Integer, Employee>();
  }

  @Override
  public String getUnionName() {
    return unionName;
  }

  public Set<Integer> getMembers() {
    Set<Integer> copy = new HashSet<Integer>();
    copy.addAll(members.keySet());
    return copy; // Returning copy so that caller cannot modify 
  }

  public void addMember(Employee emp) {
    emp.basicDetails().addUnion(unionName);
    members.put(emp.basicDetails().getId(),emp);
  }

  public void removeMember(int employeeId) {
    Employee emp = members.get(employeeId);
    emp.basicDetails().removeUnion(unionName);
    if(members.containsKey(employeeId)) members.remove(employeeId);
  }

  @Override
  public void levyCharge(int employeeId, LocalDate date, int charge, String message) {
    if(!members.containsKey(employeeId)) return;
    Employee emp = members.get(employeeId);
    emp.addUnionCharge(date, -charge, message);
  }

  @Override
  public void levyChargeOnAll(LocalDate date, int charge, String message) {
    for(Map.Entry<Integer,Employee> entry: members.entrySet()){
      Employee emp = entry.getValue(); 
      emp.addUnionCharge(date, -charge, message);
    }
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

}