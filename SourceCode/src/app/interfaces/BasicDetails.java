package app.interfaces;

import java.util.Set;

public interface BasicDetails {

  int getId();

  String getName();
  void setName(String name);

  String getPaymentMethod();
  void setPaymentMethod(String paymentMethod);

  Set<String> getUnions();
  void addUnion(String unionName);
  void removeUnion(String unionName);

}
