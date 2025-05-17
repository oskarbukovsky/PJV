package cz.cvut.fel.bukovja4;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
// import java.util.*;
import java.util.concurrent.Executors;

public class Network extends Thread {

    static Socket clientSocket = null;
    static ServerSocket serverSocket = null;

    public static void StartClient(String location) throws IOException {
        Socket clientConSocket = null;
        clientConSocket = new Socket("localhost", 9903);
        System.out.println("C Pripojeno k serveru.");

        PrintWriter out = new PrintWriter(clientConSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientConSocket.getInputStream()));
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("C send: LOCATION: " + location);
        out.println("LOCATION: " + location);
        clientConSocket.setSoTimeout(60 * 1000);

        String input;
        while ((input = in.readLine()) != null) {
            System.out.println("C receive: " + input);

            if (input.split("OK: LOCATION: ").length > 1) {
                System.out.println("C send: DATA: user:123, time:1234");
                out.println("DATA: user:123, time:1234");
            }

            if (input.equals("CLOSE")) {
                break;
            }
        }

        clientConSocket.close();
        in.close();
        out.close();
        systemIn.close();
        System.out.println("C Pripojeni klient ukoncen.");
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(9903);
            System.out.println("Server bezi na portu 9903.");
        } catch (IOException e) {
            System.out.println("Port je uz obsazen.");
            System.exit(-1);
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("S Pripojen klient.");
                threadPool.submit(() -> {
                    try {
                        client.setSoTimeout(60 * 1000);
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                        String input;
                        while ((input = in.readLine()) != null) {
                            System.out.println("S receive: " + input);

                            if (input.split("LOCATION: ").length > 1) {
                                System.out.println("S send: OK: LOCATION: " + input.split("LOCATION: ")[1]);
                                out.println("OK: LOCATION: " + input.split("LOCATION: ")[1]);
                            }

                            if (input.split("DATA: ").length > 1) {
                                System.out.println("S send: CLOSE");
                                out.println("CLOSE");
                                in.close();
                                out.close();
                                client.close();
                                break;
                            }

                            if (input.trim().equals("CLOSE")) {
                                in.close();
                                out.close();
                                client.close();
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Pripojeni selhalo.");
                    }
                });
            }
        } catch (IOException e) {
            System.out.println("Pripojeni selhalo.");
            System.exit(-1);
        }
    }
}
