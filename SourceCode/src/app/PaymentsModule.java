package app;

import app.interfaces.Employee;
import app.interfaces.Payments;

public class PaymentsModule implements Payments {

  @Override
  public String generateReciept(Employee emp) {  //TODO: Make static ??
    if (emp.getType().equals("HourlyEmployee")) {
      return generateRecieptForContractualEmployee((ContractualEmployee)emp);
    } else {
      // emp = new SalariedEmployee(id, name);
    }
    return null;
  }

  String generateRecieptForContractualEmployee(ContractualEmployee emp){
    return "asd";
  }

  public static void main(String args[]) {
    PaymentsModule pm = new PaymentsModule();
    ContractualEmployee ce = new ContractualEmployee(100, "CE-P1");
    System.out.println(pm.generateReciept(ce)); 
  }
  
}