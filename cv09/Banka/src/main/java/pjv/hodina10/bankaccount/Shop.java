
package pjv.hodina10.bankaccount;

import java.util.List;

public class Shop extends Thread {

    List<Person> customers;
    double[] bills;

    public Shop(List<Person> customers, double[] bills) {
        this.customers = customers;
        this.bills = bills;
    }

    synchronized public void makeCustomersPayBills() throws InterruptedException {
        for (Person customer : customers) {
            for (double payment : bills) {
                customer.removeAmountFromBankAccount(payment);
                System.out.println(customer.toString() + " paid bill of " + payment);
            }
        }
    }

    @Override
    public void run() {
        try {
            makeCustomersPayBills();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
