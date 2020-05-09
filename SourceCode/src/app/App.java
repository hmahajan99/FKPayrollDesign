package app;

public class App {

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() {
        System.out.println("Hello Java");
        testEmployee();
        testContractualEmployee();
        testSalariedEmployee();
    }

    public static void testEmployee() {
        System.out.println("-----------------------");
        Employee e = new Employee(0,"");
        System.out.println(e.getId());
        e.setName("Basic Emloyee");
        System.out.println(e.getName());
        System.out.println(e.getPaymentMethod());
        System.out.println("-----------------------");
    }

    public static void testContractualEmployee() {
        System.out.println("-----------------------");
        ContractualEmployee ce = new ContractualEmployee(1, "name");
        System.out.println(ce.basicDetails().getId());
        ce.basicDetails().setName("Contractual Employee");
        System.out.println(ce.basicDetails().getName());
        System.out.println(ce.basicDetails().getPaymentMethod());
        System.out.println("-----------------------");
    }

    public static void testSalariedEmployee() {
        System.out.println("-----------------------");
        SalariedEmployee se = new SalariedEmployee(2, "name");
        System.out.println(se.basicDetails().getId());
        se.basicDetails().setName("Salaried Employee");
        System.out.println(se.basicDetails().getName());
        System.out.println(se.basicDetails().getPaymentMethod());
        System.out.println("-----------------------");
    }

}