/*
* Andrid Sandi
* Modified School Project
* Simulating how a bank system could work using console
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingClient
{
      // method to hold program menu
      //@return int userInput, to be used for selecting desired operation
      //@param boolean startUp, to determine whether to print initial startup message
      public static int mainMenu(boolean startUp)
      {
          Scanner kb = new Scanner(System.in);
          int userInput;

          //main menu text
          String output = "\n-----------------------------------";
          output+="\n+++Banking System Main Menu+++";
          output+="\nEnter number to select operation:";
          output+="\n1. Add new banking client";
          output+="\n2. Delete a banking client";
          output+="\n3. Print a select banking client's data";
          output+="\n4. Print banking clients in chosen checking account balance range";
          output+="\n5. Print all banking clients";
          output+="\n6. Exit database";
          output+="\n-----------------------------------";

          //sends out initial welcome message if true
          if(startUp)
          {
              System.out.println("Welcome to Andrid's Awesome Banking System!\n" +
                      "Please utilize the menu below to access our database.");
              return 0; //ends main menu method early
          }

          System.out.println(output);
          userInput = kb.nextInt();
          return userInput;
      }

      //Method to extract all data from external file containing house bankClients
      //@param Scanner fileData, to hold scanned data from Listings file
      //@return BankClientList bankClients, object containing list of all BankAccounts from scanned data
      public static BankClientList reviewFile(Scanner fileData)
      {
          BankClientList bankClients = new BankClientList();

          //while loop that runs until scanned fileData has no more lines of data
          while (fileData.hasNextLine())
          {
              int accountNum = fileData.nextInt();
              Name name = new Name(fileData.next(), fileData.next());
              double checkingBalance = fileData.nextDouble();
              int creditScore = fileData.nextInt();
              int years = fileData.nextInt();

              //adds BankAccount objects made from scanned data to BankClientList
              bankClients.setListing(new BankAccount(accountNum,name,checkingBalance,creditScore,years));
          }

          return bankClients;
      }

      //@param BankClientList bankClients; imports current instance of bankClients object
      //@return BankAccount; completely validated user input BankAccount object to be added to main list
      public static BankAccount accountValidation(BankClientList bankClients)
      {
          Scanner kb = new Scanner(System.in);
          boolean invalidInput;
              System.out.print("Enter an account number: ");
              int accountNum = 0;

              do{ //Do while loop + try catch, in case user inputs an mismatch exception
                  try
                      {
                          //do while loop + if statement to ensure input is in bounds
                          do {
                              accountNum = kb.nextInt();
                              if(accountNum<0 || accountNum>99999)
                              {
                                  System.out.println("Invalid Input: Enter an account number from 00000-99999: ");
                                  invalidInput = true;
                              }
                              else
                              {
                                  //ensures account number is not already in use
                                  if(bankClients.checkListingExist(accountNum))
                                  {
                                      System.out.println("Account Number Already In Use: Enter an account number from 00000-99999: ");
                                      invalidInput = true;
                                  }
                                  //ends loops if input meets all conditions
                                  else
                                      invalidInput = false;
                              }

                          }while (invalidInput);
                      }
                      catch (InputMismatchException e)
                      {
                          System.out.println("Invalid Input: Enter an account number from 00000-99999: ");
                          kb.nextLine();
                          invalidInput = true;
                      }
                  }while(invalidInput);
              kb.nextLine();

              System.out.print("Enter a first name: ");
              String firstName = kb.nextLine();
              System.out.print("Enter a last name: ");
              String lastName = kb.nextLine();
              Name name = new Name(firstName,lastName);

              System.out.print("Enter client's initial checking deposit: $");
              double checkingBalance = 0;

              //do while loop + try catch, for input mismatch
              do{
                  try
                  {
                      checkingBalance = kb.nextDouble();

                      //ensures user inputs a price greater than 0
                      if(checkingBalance<1)
                      {
                          System.out.println("Invalid Input: Enter deposit above $0: ");
                          invalidInput = true;
                      }
                      //ends loop if all conditions are met
                      else
                          invalidInput = false;
                  }
                  catch (InputMismatchException e)
                  {
                      System.out.println("Invalid Input: Enter deposit amount: ");
                      kb.nextLine();
                      invalidInput = true;
                  }
              }while(invalidInput);
              kb.nextLine();

              System.out.print("Enter client's current FICO Score: ");
              int creditScore = 0;

              do{
                  try
                  {
                      creditScore = kb.nextInt();
                      //ensures user inputs a sqft greater than 0
                      if(creditScore<300)
                      {
                          System.out.println("Invalid Input: Enter a FICO Score above 300: ");
                          invalidInput = true;
                      }
                      //ends loop if all conditions are met
                      else
                          invalidInput = false;
                  }
                  catch (InputMismatchException e)
                  {
                      System.out.println("Invalid Input: Enter a FICO Score: ");
                      kb.nextLine();
                      invalidInput = true;
                  }
              }while(invalidInput);
              kb.nextLine();

              System.out.print("Enter client's current years with bank: ");
              int years = 0;

              do{ //do while loop + try catch, for input mismatch
                  try
                  {
                      years = kb.nextInt();
                      if(years<0)
                      {
                          System.out.println("Invalid Input: Enter an amount of years starting from 0: ");
                          invalidInput = true;
                      }
                      //ends loop if all conditions are met
                      else
                          invalidInput = false;
                  }
                  catch (InputMismatchException e)
                  {
                      System.out.println("Invalid Input: Enter number of years with bank: ");
                      kb.nextLine();
                      invalidInput = true;
                  }
              }while(invalidInput);
              kb.nextLine();

              //returns the BankAccount object made by the user's inputs
              return new BankAccount(accountNum,name,checkingBalance,creditScore,years);
      }

      //@param BankClientList bankClients; imports current instance of bankClients object
      //@param boolean dataType; determines if validating for an int or double
      //@return double output; returns the validated user number
      public static double numValidation(boolean dataType, BankClientList bankClients)
      {
          Scanner kb = new Scanner(System.in);
          double output=0;

          if(dataType) //validates for account number int
          {
              boolean invalidInput = false;
              do {
                  try
                  {
                      do {
                          output = kb.nextInt();
                          if(output<0 || output>99999)
                          {
                              System.out.println("Invalid Lot Number: 00000-99999");
                              invalidInput = true;
                          }
                          //checks if user's account number exists
                          else if(!bankClients.checkListingExist((int) output))
                          {
                              System.out.println("Invalid Input: Must use valid lot number.");
                              invalidInput = true;
                          }
                          else
                              invalidInput = false;

                      }while (invalidInput);
                  }
                  catch (IndexOutOfBoundsException e)
                  {
                      System.out.println("Invalid Lot Number: 00000-99999");
                  }
                  catch (InputMismatchException e)
                  {
                      System.out.println("Invalid Input: Enter a lot number");
                      kb.nextLine();
                      invalidInput = true;
                  }
              }while(invalidInput);
              kb.nextLine();

          }
          else //validates for a double
          {
              boolean invalidInput = false;
              do {
                  try
                  {
                      output = kb.nextDouble();
                  }
                  catch (InputMismatchException e)
                  {
                      System.out.println("Invalid Input: Use numbers only");
                      kb.nextLine();
                      invalidInput = true;
                  }
              }while(invalidInput);
              kb.nextLine();
          }
          return output;
      }
      //Method containing the logic behind all of the menu operations
      public static void bankingLogic() throws FileNotFoundException
      {
          mainMenu(true); //Startup message method

          boolean dataTypeInt=true;
          boolean dataTypeDouble=false;
          Scanner fileData = new Scanner(new File("CLIENTS")); //Scanning Listings file

          //Create BankClientList object to hold all BankAccount data
          //Method is used to add data from Listings file into our list
          BankClientList bankClients = reviewFile(fileData);

          boolean powerButton=true; //boolean for while loop for menu

          do{
              //runs mainMenu method, and takes userInput to determine switch case
              switch(mainMenu(false))
              {
                  case 1: //adding a listing
                      BankAccount newHouse = accountValidation(bankClients);
                      bankClients.setListing(newHouse);
                      System.out.println("\nThe following listing has been added:"+bankClients.getListing(newHouse.getAccountNumber()));
                  break;

                  case 2: //deleting a listing via account number
                      System.out.print("Enter the lot number of the listing to be removed: ");

                      int lotIn = (int) numValidation(dataTypeInt,bankClients);
                      System.out.println("\nThe following listing has been removed:" + bankClients.getListing(lotIn));
                      bankClients.deleteListing(lotIn);
                  break;

                  case 3://printing a specific listing via account number
                      System.out.print("Enter the lot number of the listing to be retrieved: ");

                      int lotIn3 = (int) numValidation(dataTypeInt,bankClients);
                      System.out.print(bankClients.getListing(lotIn3));
                  break;

                  case 4: //printing all house bankClients that fall within desired balance range
                      System.out.print("Enter a lower checking balance limit: $");
                      double lowLim = numValidation(dataTypeDouble,bankClients);
                      System.out.print("Enter a upper checking balance limit: $");
                      double highLim = numValidation(dataTypeDouble,bankClients);
                      System.out.print("The following clients match your query for checking balances between" +
                              " $"+lowLim+", and $"+highLim);
                      System.out.print(bankClients.getListingsCheckingsFilter(lowLim,highLim));
                  break;

                  case 5: //prints all bankClients
                      System.out.println(bankClients.getAllListings());
                  break;

                  case 6: //ends while loop, thus ending program
                      powerButton = false;
                      System.out.println("Logging Off: Have a nice day!");
                  break;

                  default:
                      System.out.println("Invalid Selection, please enter a number between 1-6");
              }
          }while (powerButton);
      }

      public static void main(String[] args) throws FileNotFoundException
      {
          bankingLogic(); //method holding program logic
      }

}
/*
Welcome to Andrid's Awesome Banking System!
Please utilize the menu below to access our database.

-----------------------------------
+++Banking System Main Menu+++
Enter number to select operation:
1. Add new banking client
2. Delete a banking client
3. Print a select banking client's data
4. Print banking clients in chosen checking account balance range
5. Print all banking clients
6. Exit database
-----------------------------------
1
Enter an account number: 72181
Enter a first name: Andrid
Enter a last name: Sandi
Enter client's initial checking deposit: $700
Enter client's current FICO Score: 700
Enter client's current years with bank: 7

The following listing has been added:
-----------------------------------
+++Client Banking Information+++
Account Number: 72181
Client's Name: Andrid Sandi
Checking Account Balance: $700.00
FICO Credit Score: 700
Years with bank: 7
-----------------------------------

-----------------------------------
+++Banking System Main Menu+++
Enter number to select operation:
1. Add new banking client
2. Delete a banking client
3. Print a select banking client's data
4. Print banking clients in chosen checking account balance range
5. Print all banking clients
6. Exit database
-----------------------------------
2
Enter the lot number of the listing to be removed: 1337

The following listing has been removed:
-----------------------------------
+++Client Banking Information+++
Account Number: 01337
Client's Name: Jorge Ramirez
Checking Account Balance: $190.00
FICO Credit Score: 400
Years with bank: 5
-----------------------------------

-----------------------------------
+++Banking System Main Menu+++
Enter number to select operation:
1. Add new banking client
2. Delete a banking client
3. Print a select banking client's data
4. Print banking clients in chosen checking account balance range
5. Print all banking clients
6. Exit database
-----------------------------------
3
Enter the lot number of the listing to be retrieved: 12345

-----------------------------------
+++Client Banking Information+++
Account Number: 12345
Client's Name: Raymond Lara
Checking Account Balance: $2500.00
FICO Credit Score: 700
Years with bank: 4
-----------------------------------
-----------------------------------
+++Banking System Main Menu+++
Enter number to select operation:
1. Add new banking client
2. Delete a banking client
3. Print a select banking client's data
4. Print banking clients in chosen checking account balance range
5. Print all banking clients
6. Exit database
-----------------------------------
5

-----------------------------------
+++Client Banking Information+++
Account Number: 12345
Client's Name: Raymond Lara
Checking Account Balance: $2500.00
FICO Credit Score: 700
Years with bank: 4
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 67890
Client's Name: Ian Grunch
Checking Account Balance: $23000.00
FICO Credit Score: 825
Years with bank: 12
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 23456
Client's Name: Eli Cortezano
Checking Account Balance: $12.54
FICO Credit Score: 680
Years with bank: 2
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 34567
Client's Name: James Lu
Checking Account Balance: $154200.00
FICO Credit Score: 795
Years with bank: 5
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 45678
Client's Name: Kanta Nakano
Checking Account Balance: $8000.00
FICO Credit Score: 710
Years with bank: 3
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 56789
Client's Name: Raimi Karim
Checking Account Balance: $14500.00
FICO Credit Score: 760
Years with bank: 4
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 42069
Client's Name: Yihan Jiang
Checking Account Balance: $500.00
FICO Credit Score: 670
Years with bank: 6
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 01738
Client's Name: Ivan Cruz
Checking Account Balance: $-100.65
FICO Credit Score: 350
Years with bank: 7
-----------------------------------
-----------------------------------
+++Client Banking Information+++
Account Number: 72181
Client's Name: Andrid Sandi
Checking Account Balance: $700.00
FICO Credit Score: 700
Years with bank: 7
-----------------------------------

-----------------------------------
+++Banking System Main Menu+++
Enter number to select operation:
1. Add new banking client
2. Delete a banking client
3. Print a select banking client's data
4. Print banking clients in chosen checking account balance range
5. Print all banking clients
6. Exit database
-----------------------------------
1
Enter an account number: 12345
Account Number Already In Use: Enter an account number from 00000-99999:
-4
Invalid Input: Enter an account number from 00000-99999:
67890
Account Number Already In Use: Enter an account number from 00000-99999:
87342
Enter a first name: James
Enter a last name: Franco
Enter client's initial checking deposit: $-42
Invalid Input: Enter deposit above $0:
test
Invalid Input: Enter deposit amount:
100
Enter client's current FICO Score: 0
Invalid Input: Enter a FICO Score above 300:
300
Enter client's current years with bank: -4
Invalid Input: Enter an amount of years starting from 0:
0

The following listing has been added:
-----------------------------------
+++Client Banking Information+++
Account Number: 87342
Client's Name: James Franco
Checking Account Balance: $100.00
FICO Credit Score: 300
Years with bank: 0
-----------------------------------

-----------------------------------
+++Banking System Main Menu+++
Enter number to select operation:
1. Add new banking client
2. Delete a banking client
3. Print a select banking client's data
4. Print banking clients in chosen checking account balance range
5. Print all banking clients
6. Exit database
-----------------------------------
6
Logging Off: Have a nice day!

Process finished with exit code 0

 */





