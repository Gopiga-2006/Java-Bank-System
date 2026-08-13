import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {

    // Week 1: ArrayList is used to store Account objects.
    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== JAVA BANK SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Credit / Deposit");
            System.out.println("3. Debit / Withdraw");
            System.out.println("4. Balance Check");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    credit();
                    break;
                case 3:
                    debit();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using Java Bank System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Create an account and prevent duplicate Account IDs using iteration.
    static void createAccount() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        // Manual search using iteration as required for Week 1.
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                System.out.println("Account ID already exists.");
                return;
            }
        }

        scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        Account account = new Account(accountId, customerName, 0.0);
        accounts.add(account);

        System.out.println("Account created successfully.");
    }

    // Credit means depositing money into the account.
    static void credit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        account.setBalance(account.getBalance() + amount);
        System.out.println("Amount deposited successfully.");
        System.out.println("Current Balance: " + account.getBalance());
    }

    // Debit means withdrawing money from the account.
    static void debit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        account.setBalance(account.getBalance() - amount);
        System.out.println("Amount withdrawn successfully.");
        System.out.println("Current Balance: " + account.getBalance());
    }

    // Display the current balance of an account.
    static void checkBalance() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Customer Name: " + account.getCustomerName());
        System.out.println("Current Balance: " + account.getBalance());
    }

    // Manual iteration is used to find an account in Week 1.
    static Account findAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    static class Account {
        private int accountId;
        private String customerName;
        private double balance;

        Account(int accountId, String customerName, double balance) {
            this.accountId = accountId;
            this.customerName = customerName;
            this.balance = balance;
        }

        int getAccountId() {
            return accountId;
        }

        String getCustomerName() {
            return customerName;
        }

        double getBalance() {
            return balance;
        }

        void setBalance(double balance) {
            this.balance = balance;
        }
    }
}
