import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 10000;

        System.out.println("Welcome to Kiran Bank");
        System.out.println("Opening Balance: ₹" + balance);

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid amount. Please enter a valid number.");
                        sc.next();
                        break;
                    }

                    double deposit = sc.nextDouble();

                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("Deposit successful.");
                        System.out.println("Updated Balance: ₹" + balance);
                    } else {
                        System.out.println("Invalid amount. Deposit must be greater than zero.");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid amount. Please enter a valid number.");
                        sc.next();
                        break;
                    }

                    double withdrawal = sc.nextDouble();

                    if (withdrawal <= 0) {
                        System.out.println("Invalid amount. Withdrawal must be greater than zero.");
                    } else if (withdrawal <= balance) {
                        balance -= withdrawal;
                        System.out.println("Withdrawal successful.");
                        System.out.println("Updated Balance: ₹" + balance);
                    } else {
                        System.out.println("Transaction failed: Insufficient balance.");
                        System.out.println("Current Balance: ₹" + balance);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 4.");
            }
        }
    }
}