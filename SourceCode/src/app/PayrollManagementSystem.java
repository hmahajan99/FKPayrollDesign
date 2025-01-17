package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import app.interfaces.Employee;
import app.interfaces.HourlyEmployee;
import app.interfaces.MonthlyEmployee;
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
    int id = (int) (Math.random() * range) + min;
    while (employees.containsKey(id)) {
      id = (int) (Math.random() * range) + min;
    }

    Employee emp;
    if (type.equals("HourlyEmployee")) {
      emp = new ContractualEmployee(id, name);
    } else {
      emp = new SalariedEmployee(id, name);
    }

    employees.put(id, emp);
    return id;
  }

  @Override
  public Employee getEmployee(int employeeId) {
    if (employees.containsKey(employeeId)) return employees.get(employeeId);
    return null;
  }

  @Override
  public HourlyEmployee getHourlyEmployee(int employeeId) {
    Employee emp = getEmployee(employeeId);
    if(emp!=null&&emp.getType().equals("HourlyEmployee")) return (HourlyEmployee) emp;
    return null;
  }

  @Override
  public MonthlyEmployee getMonthlyEmployee(int employeeId) {
    Employee emp = getEmployee(employeeId);
    if(emp!=null&&emp.getType().equals("MonthlyEmployee")) return (MonthlyEmployee) emp;
    return null;
  }

  @Override
  public void removeEmployee(int employeeId) {
    if (!employees.containsKey(employeeId)) return;
    // Remove from all unions also
    Employee emp = employees.get(employeeId);
    Set<String> unionNames = emp.basicDetails().getUnions();
    for (String unionName : unionNames) {
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
    for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
      int id = entry.getKey();
      String name = entry.getValue().basicDetails().getName();
      list.add(id + " : " + name);
    }
    return list;
  }

  @Override
  public void addUnion(String unionName) {
    Union union = new EmployeeUnion(unionName);
    unions.put(unionName, union);
  }

  @Override
  public Union getUnion(String unionName) {
    if (unions.containsKey(unionName)) return unions.get(unionName);
    return null;
  }

  @Override
  public void removeUnion(String unionName) {
    if (!unions.containsKey(unionName)) return;
    // Remove unionName from all its members list also
    Union union = unions.get(unionName);
    Set<Integer> members = union.getMembers();
    for (Integer employeeId : members) {
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
    Employee emp = getEmployee(employeeId);
    Union union = getUnion(unionName);
    if(emp==null||union==null) return;
    union.addMember(emp);
  }

  @Override
  public void removeEmployeeFromUnion(int employeeId, String unionName) {
    Employee emp = getEmployee(employeeId);
    Union union = getUnion(unionName);
    if(emp==null||union==null) return;
    union.removeMember(employeeId);
  }

  @Override
  public void payEmployeeWeeklyCards(int employeeId, LocalDate payDate) {
    Employee emp = getEmployee(employeeId);
    if (emp==null) return;
    emp.payWeeklyTill(payDate);
  }

  @Override
  public void payEmployeeSalary(int employeeId, LocalDate payDate) {
    MonthlyEmployee mEmp = getMonthlyEmployee(employeeId);
    if(mEmp==null) return;
    mEmp.payMonthlyTill(payDate);
  }

  @Override
  public String generateEmployeePendingPaymentsReciept(int employeeId) {
    Employee emp = getEmployee(employeeId);
    if (emp==null) return "Employee doesnt exist\n";
    return emp.generatePendingPaymentsReciept();
  }

  @Override
  public void addUnionCharge(int employeeId, String unionName, LocalDate date, int charge, String message) {
    Employee emp = getEmployee(employeeId);
    Union union = getUnion(unionName);
    if(emp==null||union==null) return;
    union.levyCharge(employeeId, date, charge, message);
  }

  @Override
  public void addUnionChargeOnAll(String unionName, LocalDate date, int charge, String message) {
    Union union = getUnion(unionName);
    if(union==null) return;
    union.levyChargeOnAll(date, charge, message);
  }

  @Override
  public void setEmployeeHourlyRate(int employeeId, int rate) {
    HourlyEmployee hEmp = getHourlyEmployee(employeeId);
    if(hEmp==null) return;
    hEmp.setHourlyRate(rate);
  }

  @Override
  public void submitEmployeeDailyTimeCard(int employeeId, LocalDate date, int numHours) {
    HourlyEmployee hEmp = getHourlyEmployee(employeeId);
    if(hEmp==null) return;
    hEmp.submitDailyTimeCard(date, numHours);;
  }

  @Override
  public void setEmployeeMonthlySalary(int employeeId, int salary) {
    MonthlyEmployee mEmp = getMonthlyEmployee(employeeId);
    if(mEmp==null) return;
    mEmp.setMonthlySalary(salary);
  }

  @Override
  public void setEmployeeCommisionRate(int employeeId, int rate) {
    MonthlyEmployee mEmp = getMonthlyEmployee(employeeId);
    if(mEmp==null) return;
    mEmp.setCommisionRate(rate);
  }

  @Override
  public void submitEmployeeSalesReciept(int employeeId, LocalDate date, int amount) {
    MonthlyEmployee mEmp = getMonthlyEmployee(employeeId);
    if(mEmp==null) return;
    mEmp.submitSalesReciept(date, amount);
  }




  
}