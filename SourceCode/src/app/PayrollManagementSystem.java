package app;

import java.util.HashMap;
import java.util.Set;

import app.interfaces.Employee;
import app.interfaces.PayrollSystem;
import app.interfaces.Union;

public class PayrollManagementSystem implements PayrollSystem {
  private HashMap<Integer, Employee> employees;
  private HashMap<String, Union> unions;

  public PayrollManagementSystem() {
    employees = new HashMap<Integer, Employee>();
    unions = new HashMap<String, Union>();
  }

  @Override
  public void addEmployee(String name, String type) {
    int max = 10_000_000; 
    int min = 1; 
    int range = max - min + 1;    
    int id = (int)(Math.random() * range) + min;
    while(employees.containsKey(id)){
      id = (int)(Math.random() * range) + min;
    }

    Employee emp;
    if(type.equals("HourlyEmployee")){
      emp = new ContractualEmployee(id, name);
    }else{
      emp = new SalariedEmployee(id, name);
    }

    employees.put(id,emp);

  }

  @Override
  public void removeEmployee(int employeeId) {
    

    // Remove from all unions also
    Employee emp = employees.get(employeeId);
    Set<String> unionNames = emp.basicDetails().getUnions();
    for(String unionName: unionNames){
      Union union = unions.get(unionName);
      union.removeMember(employeeId);
    }

    if(employees.containsKey(employeeId)) employees.remove(employeeId);
  }

  @Override
  public Set<Integer> getAllEmployeeIds() {
    Set<Integer> copy = new HashSet<Integer>();
    copy.addAll(employees.keySet());
    return copy; // Returning copy so that caller cannot modify 
  }

  @Override
  public void addUnion(String unionName) {
    Union union = new EmployeeUnion(unionName);
    unions.put(unionName,union);
  }

  @Override
  public void removeUnion(String unionName) {
    if(!unions.containsKey(unionName)) return;
    // Remove unionName from all its members list also
    Union union = unions.get(unionName);
    Set<Integer> members = union.getMembers();
    for(Integer employeeId: members){
      Employee emp = employees.get(employeeId);
      emp.basicDetails().removeUnion(unionName);
    }

    unions.remove(unionName);
  }

  @Override
  public Set<String> getAllUnionNames() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addEmployeeToUnion(Employee emp, Union union) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeEmployeeFromUnion(Employee emp, Union union) {
    // TODO Auto-generated method stub

  }

  public static void testPayrollManagementSystem() {
    PayrollManagementSystem p = new PayrollManagementSystem();
    SalariedEmployee m1 = new SalariedEmployee(1, "Semp1");
    ContractualEmployee m2 = new ContractualEmployee(2, "Cemp2");
    ContractualEmployee m3 = new ContractualEmployee(3, "Cemp3");
    ContractualEmployee m4 = new ContractualEmployee(4, "Cemp4");
    ContractualEmployee m5 = new ContractualEmployee(5, "Cemp5");
    SalariedEmployee m6 = new SalariedEmployee(6, "Semp6");
    SalariedEmployee m7 = new SalariedEmployee(7, "Semp7");
    p.employees.put(m1.basicDetails().getId(),m1);
    p.employees.put(m2.basicDetails().getId(),m2);
    p.employees.put(m3.basicDetails().getId(),m3);
    p.employees.put(m4.basicDetails().getId(),m4);
    p.employees.put(m5.basicDetails().getId(),m5);
    p.employees.put(m6.basicDetails().getId(),m6);
    p.employees.put(m7.basicDetails().getId(),m7);

    EmployeeUnion u = new EmployeeUnion("empunion1");
    u.addMember(m1);
    u.addMember(m4);
    u.addMember(m3);
    u.addMember(m6);
    // u.removeMember(m3);
    u.addMember(m7);
    p.unions.put(u.getUnionName(), u);

    System.out.println("-----------------");
    System.out.println("All employees");
    for(Integer mid: p.employees.keySet()) System.out.println("Member id: " + mid);    

    System.out.println("Members ids of members of empunion1");
    for(Integer mid: p.unions.get("empunion1").getMembers()) System.out.println("Member id: " + mid);    

    EmployeeUnion u2 = new EmployeeUnion("empunion2");
    EmployeeUnion u3 = new EmployeeUnion("empunion3");
    EmployeeUnion u4 = new EmployeeUnion("empunion4");
    u2.addMember(m1);
    u3.addMember(m1);
    u4.addMember(m1);
    // u3.removeMember(m1);
    System.out.println("Unions m1(empid=1) is a part of");
    Set<String> malicous = m1.basicDetails().getUnions();
    malicous.clear(); // Will not affect m1's unions since I'm returning a copy
    for(String union: m1.basicDetails().getUnions()) System.out.println(union);    
    System.out.println("-----------------");


  }

  
  
}