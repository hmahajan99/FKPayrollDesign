package app;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");

        SalariedEmployee se = new SalariedEmployee(12, "name");
        System.out.println(se.basicDetails().getId());
        se.basicDetails().setName("S-EMP");
        System.out.println(se.basicDetails().getName());
        System.out.println(se.basicDetails().getPaymentMethod());

        ContractualEmployee ce = new ContractualEmployee(12, "name");
        System.out.println(ce.basicDetails().getId());
        ce.basicDetails().setName("C-EMP");
        System.out.println(ce.basicDetails().getName());
        System.out.println(ce.basicDetails().getPaymentMethod());



    }

}