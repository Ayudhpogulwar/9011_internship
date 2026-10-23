import java.util.Scanner;
import java.util.Random;

public class Cab {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
Random random = new Random();

    while (true) {
        System.out.println("\n===== CAB BOOKING CHATBOT =====");
        System.out.println("1. Book Ride");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        int choice;

        if (sc.hasNextInt()) {
            choice = sc.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a number.");
            sc.next();
            continue;
        }

        if (choice == 1) {
            System.out.println("\n===== SELECT CAB TYPE =====");
            System.out.println("1. Auto - Rs. 12/km");
            System.out.println("2. Sedan - Rs. 18/km");
            System.out.println("3. SUV - Rs. 25/km");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            int cabChoice = sc.nextInt();
            String cabType;
            int rate;

            if (cabChoice == 1) {
                cabType = "Auto";
                rate = 12;
            } else if (cabChoice == 2) {
                cabType = "Sedan";
                rate = 18;
            } else if (cabChoice == 3) {
                cabType = "SUV";
                rate = 25;
            } else {
                System.out.println("Invalid cab choice!");
                continue;
            }

            System.out.println("\n===== SELECT ROUTE =====");
            System.out.println("1. Pune Station to Shivajinagar - 5 km");
            System.out.println("2. Swargate to Kothrud - 8 km");
            System.out.println("3. Hinjawadi to Wakad - 10 km");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            int routeChoice = sc.nextInt();
            String source;
            String destination;
            int distance;

            if (routeChoice == 1) {
                source = "Pune Station";
                destination = "Shivajinagar";
                distance = 5;
            } else if (routeChoice == 2) {
                source = "Swargate";
                destination = "Kothrud";
                distance = 8;
            } else if (routeChoice == 3) {
                source = "Hinjawadi";
                destination = "Wakad";
                distance = 10;
            } else {
                System.out.println("Invalid route choice!");
                continue;
            }

            int fare = rate * distance;

            System.out.println("\n===== RIDE DETAILS =====");
            System.out.println("Cab Type: " + cabType);
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
            System.out.println("Distance: " + distance + " km");
            System.out.println("Rate: Rs. " + rate + "/km");
            System.out.println("Total Fare: Rs. " + fare);

            System.out.println("\n1. Confirm & Share OTP");
            System.out.println("2. Cancel Ride");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            int confirmChoice = sc.nextInt();

            if (confirmChoice == 1) {
                int otp = 1000 + random.nextInt(9000);

                System.out.println("\nYour OTP is: " + otp);
                System.out.println("OTP shared successfully!");
                System.out.println("Ride booking confirmed!");
                System.out.println("Cancellation is not allowed after OTP sharing.");
            } else if (confirmChoice == 2) {
                System.out.println("\nRide cancelled successfully.");
                System.out.println("OTP has not been shared.");
            } else {
                System.out.println("Invalid choice! Returning to the main menu.");
            }

        } else if (choice == 2) {
            System.out.println("\nThank you for using Cab Booking Chatbot!");
            System.out.println("Goodbye!");
            break;
        } else {
            System.out.println("Invalid choice! Please select 1 or 2.");
        }
    }

    sc.close();
}
}