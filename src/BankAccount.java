import java.util.Scanner;
public class BankAccount
{
    private int accountNumber;
    private Name clientName;
    private double checkingBalance;
    private int creditScore;
    private int years;

    //no arg constructor
    public BankAccount()
    {
        accountNumber = 0;
        clientName = new Name("N /","A");
        checkingBalance = 0;
        creditScore = 0;
        years = 0;
    }

    //overloaded contructor
    public BankAccount(int lotNumIn, Name nameIn, double priceIn, int sqFtIn, int numBedIn)
    {
        accountNumber = lotNumIn;
        clientName = nameIn;
        checkingBalance = priceIn;
        creditScore = sqFtIn;
        years = numBedIn;
    }

    //@return int accoutNumber, client's account number
    public int getAccountNumber()
    {
        return accountNumber;
    }

    //@return String nameOut, returns Name object as a string
    public String getClientName()
    {
        String nameOut = clientName.getName();
        return nameOut;
    }
    //@return double checkingBalance, client's checking balance
    public double getCheckingBalance()
    {
        return checkingBalance;
    }

    //@return String output, String representation of HouseListing object
    public String toString()
    {
        String output = "\n-----------------------------------";
        output+="\n+++Client Banking Information+++";
        output+=String.format("\nAccount Number: %05d", accountNumber);
        output+="\nClient's Name: "+getClientName();
        output+=String.format("\nChecking Account Balance: $%.2f", checkingBalance);
        output+="\nFICO Credit Score: "+ creditScore;
        output+="\nYears with bank: "+ years;
        output+="\n-----------------------------------";

        return output;
    }
}
