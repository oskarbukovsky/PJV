package cz.cvut.fel.pjv.cv05;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContactList {
    ArrayList<Contact> contacts;

    public ContactList() {
        this.contacts = new ArrayList<Contact>();
    }

    public void AddContacts() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                Contact contact = new Contact();
                System.out.println("Creating new Contact:");
                while (true) {
                    System.out.print("  What is your contact name: ");
                    try {
                        contact.setName(scanner.nextLine());
                        break;
                    } catch (NoSuchElementException e) {
                        throw new InterruptedException();
                    } catch (Contact.InvalidNameException e) {
                        System.out.println(" - Invalid name");
                    }
                }
                while (true) {
                    System.out.print("  What is your telephone number: ");
                    try {
                        contact.setNumber(scanner.nextLine());
                        break;
                    } catch (NoSuchElementException e) {
                        throw new InterruptedException();
                    } catch (Contact.InvalidNumberException e) {
                        System.out.println(" - Invalid number");
                    }
                }
                System.out.println("Adding contact to list");
                this.AddContact(contact);
            }
        } catch (InterruptedException e) {
            System.out.println("\n - Interrupted");
        } finally {
            scanner.close();
        }
    }

    public void AddContact(Contact contact) {
        this.contacts.add(contact);
        System.out.println("Jmeno: " + contact.getName() + ", Cislo: " +
                contact.getNumber());
    }

    public void PrintAllContacts() {
        System.out.println("All contacts:");
        this.contacts.forEach(contact -> {
            System.out.println(" Name: " + contact.getName() + ", Number: " + contact.getNumber());
        });
    }
}
