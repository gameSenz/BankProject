public interface BankClientListInterface
{
    //Method to delete a object from list
    public void deleteListing(int lotNum);

    //Method to add a new object to List
    public void setListing(BankAccount listingIn);

    //Method to collect all objects within List as a String
    public String getAllListings();

    //Method to turn a specific object within the List into a String
    public String getListing(int lotNum);

    //Method to filter list by a upper and low price limit, and turn all applicable objects into a String
    public String getListingsCheckingsFilter(double lowLim, double highLim);
}
