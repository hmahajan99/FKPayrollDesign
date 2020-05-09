package app.interfaces;

import java.util.Set;

public interface Union {
  String getUnionName();
  Set<Integer> getMembers();
  void addMember(Employee emp);
  void removeMember(Employee emp);
  void levyCharge(Employee emp, int charge, String message);
  void levyAll(int charge, String message);
}