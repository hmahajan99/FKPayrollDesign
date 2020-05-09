package app;

import java.util.HashMap;
import java.util.Set;

import app.interfaces.Employee;
import app.interfaces.Union;

public class EmployeeUnion implements Union {
  private String unionName;
  private HashMap<Integer, Employee> members; // TODO: change value to charge later when implementing PayRoll

  public EmployeeUnion(String unionName) {
    this.unionName = unionName;
    members = new HashMap<Integer, Employee>();
  }

  @Override
  public String getUnionName() {
    return unionName;
  }

  public Set<Integer> getMembers() {
    return members.keySet();
  }

  public void addMember(Employee emp) {
    emp.basicDetails().addUnion(unionName);
    members.put(emp.basicDetails().getId(),emp);
  }

  public void removeMember(Employee emp) {
    int id = emp.basicDetails().getId();
    emp.basicDetails().removeUnion(unionName);
    if(members.containsKey(id)) members.remove(id);
  }

  public void levyCharge(Employee emp, int charge, String message) {
    // TODO When Implement PayRoll

  }

  public void levyAll(int charge, String message) {
    // TODO When Implement PayRoll
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
    u.removeMember(m3);
    System.out.println("Members ids of members of EmployeeUnion removing m3");
    for(Integer mid: u.getMembers()) System.out.println("Member id: " + mid);
    System.out.println("-----------------");
  }



}