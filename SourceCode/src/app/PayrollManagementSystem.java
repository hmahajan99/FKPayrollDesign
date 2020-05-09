package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
  public int addEmployee(String name, String type) {
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
    return id;
  }

  @Override
  public Employee getEmployee(int employeeId) {
    if(employees.containsKey(employeeId)) return employees.get(employeeId);
    return null;
  }

  @Override
  public void removeEmployee(int employeeId) {
    if(!employees.containsKey(employeeId)) return;
    // Remove from all unions also
    Employee emp = employees.get(employeeId);
    Set<String> unionNames = emp.basicDetails().getUnions();
    for(String unionName: unionNames){
      Union union = unions.get(unionName);
      union.removeMember(employeeId);
    }

    employees.remove(employeeId);
  }

  @Override
  public Set<Integer> getAllEmployeeIds() {
    Set<Integer> copy = new HashSet<Integer>();
    copy.addAll(employees.keySet());
    return copy; // Returning copy so that caller cannot modify 
  }

  @Override
  public ArrayList<String> getAllEmployeeIdsWithNames() {
    ArrayList<String> list = new ArrayList<String>();
    for(Map.Entry<Integer,Employee> entry: employees.entrySet()){
      int id = entry.getKey();
      String name = entry.getValue().basicDetails().getName();
      list.add(id + " : " + name);
    }
    return list;
  }

  @Override
  public void addUnion(String unionName) {
    Union union = new EmployeeUnion(unionName);
    unions.put(unionName,union);
  }

  @Override
  public Union getUnion(String unionName) {
    if(unions.containsKey(unionName)) return unions.get(unionName);
    return null;
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
    Set<String> copy = new HashSet<String>();
    copy.addAll(unions.keySet());
    return copy; // Returning copy so that caller cannot modify 
  }

  @Override
  public void addEmployeeToUnion(int employeeId, String unionName) {
    Employee emp = employees.get(employeeId);
    Union union = unions.get(unionName);
    union.addMember(emp);
  }

  @Override
  public void removeEmployeeFromUnion(int employeeId, String unionName) {
    Union union = unions.get(unionName);
    union.removeMember(employeeId);
  }

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
    for(Integer employeeId: pms.getAllEmployeeIds()){
      System.out.println(employeeId);
    }
    pms.removeEmployee(id7);
    System.out.println("Testing getAllEmployeeIdsWithNames()");
    for(String s: pms.getAllEmployeeIdsWithNames()){
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
    for(Integer employeeId: sportsUnion.getMembers()) System.out.println(employeeId);
    System.out.println("Members of sleepingUnion");
    for(Integer employeeId: sleepingUnion.getMembers()) System.out.println(employeeId);

    Employee e3 = pms.getEmployee(id3);
    System.out.println("Unions of e3");
    for(String unionName: e3.basicDetails().getUnions()) System.out.println(unionName);
    pms.removeUnion("LabourUnion");
    System.out.println("Unions of e3 after deleting LabourUnion");
    for(String unionName: e3.basicDetails().getUnions()) System.out.println(unionName);
    pms.removeEmployeeFromUnion(id3, "SportsUnion");
    System.out.println("Unions of e3 after removing membership from SportsUnion");
    for(String unionName: e3.basicDetails().getUnions()) System.out.println(unionName);

  }
  
}