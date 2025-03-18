// Abstract class BankAccount
abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Getter and setter methods (Encapsulation)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Concrete method for deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Amount must be positive.");
        }
    }

    // Concrete method for withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Abstract method to calculate interest
    public abstract double calculateInterest();
}

// SavingsAccount subclass
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }
}

// CurrentAccount subclass
class CurrentAccount extends BankAccount {
    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        // Current account typically doesn't offer interest, so return 0
        return 0;
    }
}

// Loanable interface
interface Loanable {
    void applyForLoan(double loanAmount);
    boolean calculateLoanEligibility(double annualIncome);
}

// Implement Loanable for SavingsAccount
class LoanSavingsAccount extends SavingsAccount implements Loanable {

    public LoanSavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance, interestRate);
    }

    @Override
    public void applyForLoan(double loanAmount) {
        System.out.println("Loan applied for: $" + loanAmount);
    }

    @Override
    public boolean calculateLoanEligibility(double annualIncome) {
        // Assume eligibility for a loan if annual income is greater than $50,000
        return annualIncome > 50000;
    }
}

// BankSystem class to demonstrate polymorphism
public class BankingSystem {
    public static void main(String[] args) {
        // Create different types of accounts
        BankAccount[] accounts = {
            new SavingsAccount("S12345", "John Doe", 1000, 0.05), // Savings Account
            new CurrentAccount("C12345", "Jane Smith", 1500),     // Current Account
            new LoanSavingsAccount("L12345", "Robert Brown", 5000, 0.04) // Loan Savings Account
        };

        // Process each account
        for (BankAccount account : accounts) {
            System.out.println("Account: " + account.getHolderName() + " (" + account.getAccountNumber() + ")");
            System.out.println("Balance: $" + account.getBalance());

            // Calculate interest based on account type
            double interest = account.calculateInterest();
            System.out.println("Interest: $" + interest);
            
            // Demonstrating polymorphism: Loan application if account is Loanable
            if (account instanceof Loanable) {
                Loanable loanableAccount = (Loanable) account;
                loanableAccount.applyForLoan(10000);
                boolean eligible = loanableAccount.calculateLoanEligibility(60000); // Assume $60,000 annual income
                System.out.println("Loan Eligibility: " + (eligible ? "Eligible" : "Not Eligible"));
            }

            System.out.println("-".repeat(40));
        }
    }
}
