package cz.cvut.fel.pjv;

public class Test {

    public static void main(String[] args) {
        String password = "abcdaabbaabbbadc";
        // BruteForceAttacker attacker = new BruteForceAttacker();
        BruteForceAttackerTryFast attacker = new BruteForceAttackerTryFast();
        attacker.init(new char[] { 'a', 'b', 'c', 'd' }, password);

        System.out.println("Trying to break password...");
        long startTime = System.currentTimeMillis();
        attacker.breakPassword(password.length());
        long endTime = System.currentTimeMillis();

        if (attacker.isOpened()) {
            System.out.println("[VAULT] opened, password is " + password);
        } else {
            System.out.println("[VAULT] is still closed");
        }

        long duration = endTime - startTime;
        System.out.println("Time taken to break the password: " + duration + " milliseconds");
    }
}