
package pjv.hodina10.bankaccount;

import java.util.List;

public class Work extends Thread {
    private List<Person> employees;
    private double[] payments;

    public Work(List<Person> employees, double[] payments) {
        this.employees = employees;
        this.payments = payments;
    }

    synchronized public void payPayments() throws InterruptedException {
        for (Person employee : employees) {
            for (double payment : payments) {
                employee.addAmountToBankAccount(payment);
                System.out.println(employee.toString() + " received payment of " + payment);
            }
        }
    }

    @Override
    public void run() {
        try {
            payPayments();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
