import java.util.Scanner;


class BankAccount {

    private String accountHolderName;
    protected double balance;

    public BankAccount(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }
}


class SavingsAccount extends BankAccount {

    public SavingsAccount(String name, double balance) {
        super(name, balance); 
    }

    public void addInterest() {
        double interest = balance * 0.05;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}


class CurrentAccount extends BankAccount {

    private static final double SERVICE_CHARGE = 500;

    public CurrentAccount(String name, double balance) {
        super(name, balance);
    }

    public void deductServiceCharge() {
        if (balance >= SERVICE_CHARGE) {
            balance -= SERVICE_CHARGE;
            System.out.println("Service charge deducted: " + SERVICE_CHARGE);
        } else {
            System.out.println("Insufficient balance for service charge!");
        }
    }
}


public class BankApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount account; 
        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        int choice = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double initialDeposit = sc.nextDouble();

        if (choice == 1) {
            account = new SavingsAccount(name, initialDeposit);
        } else {
            account = new CurrentAccount(name, initialDeposit);
        }

        int option;
        do {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Add Interest / Deduct Charges");
            System.out.println("5. Exit");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    account.deposit(sc.nextDouble());
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    account.withdraw(sc.nextDouble());
                    break;

                case 3:
                    account.displayAccountDetails();
                    break;

                case 4:
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).addInterest();
                    } else if (account instanceof CurrentAccount) {
                        ((CurrentAccount) account).deductServiceCharge();
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the Bank Account System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (option != 5);

        sc.close();
    }
}

