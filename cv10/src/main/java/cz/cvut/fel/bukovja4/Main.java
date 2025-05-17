package cz.cvut.fel.bukovja4;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello world!");

        // Singleton singleton = Singleton.getInstance();
        // singleton.showMessage();

        Network network = new Network();
        network.start();
        Network.StartClient("dvere");
        Network.StartClient("okno");
    }
}