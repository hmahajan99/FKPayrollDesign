package app.interfaces;

import java.time.LocalDate;
import java.util.Set;

public interface Union {
  String getUnionName();
  Set<Integer> getMembers();
  void addMember(Employee emp);
  void removeMember(int employeeId);
  void levyCharge(int employeeId, LocalDate date, int charge, String message);
  void levyChargeOnAll(LocalDate date, int charge, String message);
}