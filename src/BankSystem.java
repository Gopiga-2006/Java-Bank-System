import java.util.HashMap;
import java.util.Scanner;

public class BankSystem {

    // Week 2: HashMap stores Account objects using Account ID as the key.
    static HashMap<Integer, Account> accounts = new HashMap<>();
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

    // Create an account using HashMap.
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

        Account account = new Account(accountId, customerName, 0.0);
        accounts.put(accountId, account);

        System.out.println("Account created successfully.");
    }

    // Credit uses the Account ID directly as a HashMap key.
    static void credit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        if (!accounts.containsKey(accountId)) {
            System.out.println("Account not found.");
            return;
        }

        Account account = accounts.get(accountId);

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

    // Debit uses the Account ID directly as a HashMap key.
    static void debit() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        if (!accounts.containsKey(accountId)) {
            System.out.println("Account not found.");
            return;
        }

        Account account = accounts.get(accountId);

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

    // Balance check uses the Account ID directly as a HashMap key.
    static void checkBalance() {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        if (!accounts.containsKey(accountId)) {
            System.out.println("Account not found.");
            return;
        }

        Account account = accounts.get(accountId);

        System.out.println("Customer Name: " + account.getCustomerName());
        System.out.println("Current Balance: " + account.getBalance());
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
