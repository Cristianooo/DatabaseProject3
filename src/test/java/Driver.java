import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    public void startProgram(){
        Connection connect = DatabaseConnection.getdatabaseconnection();


        DatabaseFunctions myFunctions = new DatabaseFunctions();
        ParseCSV CSVparser = new ParseCSV();

        //String filepath = "/Users/cristianofirmani/Docs/DataGeneration/DummyData.csv"; (the file path I used)
        Scanner scan = new Scanner(System.in);
        int response;
        String secondResponse;
        String thirdResponse;

        System.out.println("Hi, welcome to your Database toolset.\n");
        response = 1;


        while(response == 1 || response == 2 || response == 3|| response == 4){
            System.out.println("What would you like to do?");
            System.out.println("Press 1 for Table Creation");
            System.out.println("Press 2 to print all the tables");
            System.out.println("Press 3 to print a specific table");
            System.out.println("Press 4 to read a CSV file");
            System.out.println("Press any other number to exit");
            response = scan.nextInt();

            if(response == 1){
                try{
                    myFunctions.TableCreation();
                    System.out.println("Tables created. \n");
                }catch(SQLException e){
                    System.out.println(e);
                }

            }
            else if(response == 2){
                try{
                    myFunctions.printAllTables();
                    System.out.println("All Tables printed. \n");
                }catch(SQLException e){
                    System.out.println(e);
                }

            }
            else if(response == 3){
                System.out.println("What table do you want to print? (Persons, Company, TV, Newspaper, or PersonTvOrder)");
                scan.nextLine();
                secondResponse = scan.nextLine();
                try{
                    myFunctions.getResults(secondResponse);

                    System.out.println("Table printed \n");
                }
                catch(SQLException e){
                    System.out.println("That table was not found. \n");
                }

            }
            else if(response == 4){
                System.out.println("Please list the filepath for the csv file (ex. /Users/Rene/Documents/FakeData.csv)");
                scan.nextLine();
                thirdResponse = scan.nextLine();
                try {
                    CSVparser.readData(thirdResponse);
                }
                catch(IOException e){
                    System.out.println("That file was not found.");
                }
            }
        }
        System.out.println("Thanks for using the Database toolset");


    }



}
