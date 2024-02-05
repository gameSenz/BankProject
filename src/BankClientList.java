import java.util.ArrayList;
import java.util.List;
public class BankClientList implements BankClientListInterface
{
    //ArrayList holding client objects
    private List<BankAccount> clientListings = new ArrayList<>();

    //No arg constructor
    public BankClientList()
    {}

    //@param int accountNum, using a accountnum find and delete associated client object
    public void deleteListing(int accountNum)
    {
        int index=-1;//ensures no client is deleted if a invalid accountnum is used

        //for each loop iterating through entire ArrayList, and looking for the index associated with inputted accountNumber
        for(BankAccount bankAccount : clientListings)
        {
            if(bankAccount.getAccountNumber() == accountNum)
            {
                //assigns index of clients with accountNumber
                index = clientListings.indexOf(bankAccount);
            }
        }
        clientListings.remove(index); //removes object from List
    }

    //@param client listingIn, adds a new object to ArrayList
    public void setListing(BankAccount listingIn)
    {
        clientListings.add(listingIn);
    }

    //@return String output, contains all clients in ArrayList
    public String getAllListings()
    {
        String output="";

        //for each loop iterating through entire ArrayList, and merging with eachother in a String
        for(BankAccount bankAccount : clientListings)
        {
            output += bankAccount.toString();
        }
        return output;
    }


    //@return String listingOut, specific client is turned into string
    //@param int accountNum, accountNumber to be used for finding client object
    public String getListing(int accountNum)
    {
        String listingOut="";

        //for each loop iterating through entire ArrayList, and assigning a objecr to string with associated accountNumber
        for(BankAccount bankAccount : clientListings) //Cited to understand For-Each https://stackoverflow.com/questions/17526608/how-to-find-an-object-in-an-arraylist-by-property
        {
            if(bankAccount.getAccountNumber() == accountNum)
            {
                listingOut = bankAccount.toString(); //toString to format client object
            }
        }
        return listingOut;
    }
    public boolean checkListingExist(int accountNum)
    {
        boolean valid=false;

        for(BankAccount bankAccount : clientListings) //Cited to understand For-Each https://stackoverflow.com/questions/17526608/how-to-find-an-object-in-an-arraylist-by-property
        {
            if(bankAccount.getAccountNumber() == accountNum)
            {
                valid=true; //toString to format client object
            }
        }
        return valid;
    }

    //@param double lowLim, to hold lower bound of filter && double highLim, to hold higher bound of filter
    public String getListingsCheckingsFilter(double lowLim, double highLim)
    {

        String output="";
        //for each loop iterating through entire ArrayList, and looking for the price values that fall within inputted bounds
        for(BankAccount bankAccount : clientListings)
        {
            if(bankAccount.getCheckingBalance()>=lowLim && bankAccount.getCheckingBalance()<=highLim)
                output += bankAccount.toString(); //clients within bounds are formatted as Strings and concatenated
        }
        return output;
    }
}
