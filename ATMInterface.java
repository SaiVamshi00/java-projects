import java.util.Scanner;

// User class to represent each user of the ATM
class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double balance) {
        this.accountBalance = balance;
    }
}

// ATM class encapsulating ATM functionalities
class ATM {
    private User currentUser;

    public boolean authenticateUser(String userID, String userPIN) {
        // Dummy authentication logic, replace with actual authentication logic
        return currentUser != null && currentUser.getUserID().equals(userID) && currentUser.getUserPIN().equals(userPIN);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public double checkBalance() {
        return currentUser.getAccountBalance();
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            System.out.println("Amount withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
            System.out.println("Amount deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create dummy user
        User user = new User("123456", "7890", 1000.0);
        ATM atm = new ATM();
        atm.setCurrentUser(user);

        // User authentication
        System.out.println("Welcome to the ATM interface program");
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String userPIN = scanner.nextLine();

        if (atm.authenticateUser(userID, userPIN)) {
            System.out.println("Authentication successful");
            // ATM operations
            boolean flag=true;
            while(flag==true){

                System.out.println("Choose an option:");
                System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                System.out.println("Your account balance is: " + atm.checkBalance());
                break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                    case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                    case 4:
                    flag=false;
                    break;
                    default:
                    System.out.println("Invalid choice");
                }
            }
        } else {
            System.out.println("Authentication failed. Please try again.");
        }

        scanner.close();
    }
}
