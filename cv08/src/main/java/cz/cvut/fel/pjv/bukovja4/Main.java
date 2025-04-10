package cz.cvut.fel.pjv.bukovja4;

import java.io.BufferedReader;
// import java.io.BufferedOutputStream;
// import java.io.DataOutputStream;
// import java.io.BufferedReader;
// import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.util.Scanner;

// import javax.xml.stream.XMLStreamReader;

public class Main {
    public static void main(String[] args) {
        // InputStream inputStream =
        // Main.class.getResourceAsStream("/test_data/input.txt");

        // try (Scanner sc = new Scanner(System.in);
        // DataOutputStream output = new DataOutputStream(new
        // FileOutputStream("output.bin"));) {
        // while (sc.hasNext()) {
        // output.writeInt(sc.nextInt());
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // String s;
        // try (InputStreamReader isr = new InputStreamReader(inputStream);
        // BufferedReader br = new BufferedReader(isr);) {
        // for (int i = 0; (s = br.readLine()) != null; i++) {
        // System.out.println(i + ". " + s);
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        InputStream inputStream = Main.class.getResourceAsStream("/seznam.csv");

        // XMLStreamReader reader = new XMLStreamReader(inputStream)

        String s;
        try (InputStreamReader isr = new InputStreamReader(inputStream, "Cp1250");
                BufferedReader br = new BufferedReader(isr);) {
            for (int i = 0; (s = br.readLine()) != null; i++) {
                // System.out.println(i + ". " + s);
                for (String split : s.split(";")) {
                    System.out.print(split + " | ");
                }
                System.out.print("\n");
                if (i == 0) {
                    System.out.println(
                            "=====================================================================================================================");
                }
                // break;
                if (i == 5) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}