package app.interfaces;

import java.util.ArrayList;
import java.util.Set;

public interface PayrollSystem {
  int addEmployee(String name, String type); // TODO: Update employee also
  Employee getEmployee(int employeeId);
  void removeEmployee(int employeeId);
  Set<Integer> getAllEmployeeIds();
  ArrayList<String> getAllEmployeeIdsWithNames();
  
  void addUnion(String unionName);
  Union getUnion(String unionName);
  void removeUnion(String unionName);
  Set<String> getAllUnionNames();

  void addEmployeeToUnion(int employeeId, String unionName);
  void removeEmployeeFromUnion(int employeeId, String unionName);

}