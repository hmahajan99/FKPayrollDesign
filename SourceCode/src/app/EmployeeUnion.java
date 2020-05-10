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

}