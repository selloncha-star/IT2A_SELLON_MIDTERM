package com.scc;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScenarioDao dao = new ScenarioDao();
        int choice = 0;

        do {
            System.out.println("------------------------------------------------------------------");
            System.out.println("                   LAUNDRY SHOP JOB ORDER SYSTEM                  ");
            System.out.println("------------------------------------------------------------------\n");
            System.out.println("[1] Add New Job Order");
            System.out.println("[2] View Active Job Orders");
            System.out.println("[3] Exit System\n");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline buffer token
            } else {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scanner.next(); // Clear invalid token
                continue;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("--- ADD NEW JOB ORDER ---");
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Weight (KG): ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid weight format. Please enter a number.");
                        scanner.next();
                        System.out.print("Enter Weight (KG): ");
                    }
                    double weight = scanner.nextDouble();

                    System.out.print("Enter Total Amount: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid amount format. Please enter a number.");
                        scanner.next();
                        System.out.print("Enter Total Amount: ");
                    }
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline buffer token

                    System.out.print("Enter Status (RECEIVED / WASHING / READY): ");
                    String status = scanner.nextLine().toUpperCase();

                    JobOrder newOrder = new JobOrder(name, weight, amount, status);
                    boolean success = dao.addJobOrder(newOrder);

                    if (success) {
                        System.out.println("\nSUCCESS: Laundry job order created!");
                    } else {
                        System.out.println("\nERROR: Failed to create job order.");
                    }
                    break;

                case 2:
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("                     ACTIVE LAUNDRY ORDERS                        ");
                    System.out.println("------------------------------------------------------------------");
                    List<JobOrder> orders = dao.getAllJobOrders();

                    System.out.println("+-----+----------------------+-----------+--------------+------------+");
                    System.out.println("| ID  | CUSTOMER NAME        | WEIGHT_KG | TOTAL AMOUNT | STATUS     |");
                    System.out.println("+-----+----------------------+-----------+--------------+------------+");

                    for (JobOrder o : orders) {
                        System.out.printf("| %-3d | %-20s | %-9.1f | %-12.2f | %-10s |\n",
                                o.getId(), o.getCustomerName(), o.getWeightKg(), o.getTotalAmount(), o.getStatus());
                    }
                    System.out.println("+-----+----------------------+-----------+--------------+------------+");
                    System.out.println("Total Records Displayed: " + orders.size());
                    break;

                case 3:
                    System.out.println("Exiting system... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
            System.out.println("\n");
        } while (choice != 3);

        scanner.close();
    }
}