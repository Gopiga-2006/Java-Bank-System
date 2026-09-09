import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BankSystem {

    static TreeMap<Integer, Account> accounts = new TreeMap<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "accounts.txt";

    public static void main(String[] args) {
        loadAccountsFromFile();
        int choice;

        do {
            System.out.println("\n===== JAVA BANK SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Credit / Deposit");
            System.out.println("3. Debit / Withdraw");
            System.out.println("4. Balance Check");
            System.out.println("5. View All Accounts (Sorted by Account ID)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: credit(); break;
                case 3: debit(); break;
                case 4: checkBalance(); break;
                case 5: viewAllAccounts(); break;
                case 6: System.out.println("Thank you for using Java Bank System."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    static void createAccount() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        if (accountId <= 0) {
            System.out.println("Account ID must be greater than zero.");
            return;
        }
        if (accounts.containsKey(accountId)) {
            System.out.println("Account ID already exists.");
            return;
        }

        scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }

        accounts.put(accountId, new Account(accountId, customerName, 0.0));
        saveAccountsToFile();
        System.out.println("Account created successfully.");
    }

    static void saveAccountsToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
                Account account = entry.getValue();
                writer.write(account.getAccountId() + ","
                        + account.getCustomerName() + ","
                        + account.getBalance() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to save account data.");
        }
    }

    static void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3) {
                    int accountId = Integer.parseInt(data[0]);
                    String customerName = data[1];
                    double balance = Double.parseDouble(data[2]);
                    accounts.put(accountId, new Account(accountId, customerName, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing account data found.");
        }
    }

    static void credit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = accounts.get(accountId);
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
        saveAccountsToFile();
        System.out.println("Amount deposited successfully.");
        System.out.println("Current Balance: " + account.getBalance());
    }

    static void debit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = accounts.get(accountId);
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
        saveAccountsToFile();
        System.out.println("Amount withdrawn successfully.");
        System.out.println("Current Balance: " + account.getBalance());
    }

    static void checkBalance() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = accounts.get(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Customer Name: " + account.getCustomerName());
        System.out.println("Current Balance: " + account.getBalance());
    }

    static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\n===== ALL ACCOUNTS (SORTED BY ACCOUNT ID) =====");
        for (Map.Entry<Integer, Account> entry : accounts.entrySet()) {
            Account account = entry.getValue();
            System.out.println("Account ID: " + entry.getKey());
            System.out.println("Customer Name: " + account.getCustomerName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("------------------------");
        }
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

        int getAccountId() { return accountId; }
        String getCustomerName() { return customerName; }
        double getBalance() { return balance; }
        void setBalance(double balance) { this.balance = balance; }
    }
}
