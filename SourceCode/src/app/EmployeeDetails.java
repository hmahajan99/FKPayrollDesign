package app;

import java.util.HashSet;
import java.util.Set;

import app.interfaces.BasicDetails;

public class EmployeeDetails implements BasicDetails {

  private int id;
  private String name;
  private String paymentMethod; // TODO: make Enum ? -> Only 3 values
  private Set<String> unions; // unions employee is part of


  public EmployeeDetails(int id, String name) {
    this.id = id; // check unique
    this.name = name;
    this.paymentMethod = "BankDeposit";
    this.unions = new HashSet<String>();
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPaymentMethod() {
    return paymentMethod;
  }

  @Override
  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  @Override
  public Set<String> getUnions() {
    // return unions;  // If do this caller can modify unions, so return a copy
    Set<String> copy = new HashSet<String>();
    copy.addAll(unions);
    return copy;
  }

  @Override
  public void addUnion(String unionName) {
    unions.add(unionName);
  }

  @Override
  public void removeUnion(String unionName) {
    unions.remove(unionName);
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

}