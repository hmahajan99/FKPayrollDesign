package app;

public class Employee implements BasicEmployee {

  private int id;
  private String name;
  private String paymentMethod;

  public Employee(int id, String name) {
    this.id = id; // check unique
    this.name = name;
    this.paymentMethod = "DEFAULT";
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

}