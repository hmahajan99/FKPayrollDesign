package app.interfaces;

import java.util.Set;

public interface PayrollSystem {
  void addEmployee(String name, String type); // TODO: Update employee also
  void removeEmployee(int employeeId);
  Set<Integer> getAllEmployeeIds();
  
  void addUnion(String unionName);
  void removeUnion(String unionName);
  Set<String> getAllUnionNames();

  void addEmployeeToUnion(Employee emp, Union union);
  void removeEmployeeFromUnion(Employee emp, Union union);

}