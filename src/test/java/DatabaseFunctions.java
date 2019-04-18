import java.sql.*;

public class DatabaseFunctions {

    Connection connect = DatabaseConnection.getdatabaseconnection();

    int pID;
    int cID;

    public void deleteTables() throws SQLException {                                        //Deletes all database tables
        PreparedStatement persons = connect.prepareStatement("DROP TABLE Persons");
        persons.executeUpdate();
        PreparedStatement company = connect.prepareStatement("DROP TABLE Company");
        company.executeUpdate();
        PreparedStatement TV = connect.prepareStatement("DROP TABLE TV");
        TV.executeUpdate();
        PreparedStatement newspaper = connect.prepareStatement("DROP TABLE Newspaper");
        newspaper.executeUpdate();
        PreparedStatement personTVorder = connect.prepareStatement("DROP TABLE PersonTvOrder");
        personTVorder.executeUpdate();

    }

    public void TableCreation() throws SQLException {                                                   //Creates all necessary tables
        PreparedStatement Persons = connect.prepareStatement("Create Table IF NOT EXISTS Persons (" +
                "UID INT NOT NULL AUTO_INCREMENT," +
                "firstName VARCHAR(50)," +
                "lastName VARCHAR (50)," +
                "streetAddress VARCHAR (255)," +
                "PRIMARY KEY (UID));");
        Persons.executeUpdate();

        PreparedStatement Company = connect.prepareStatement("CREATE TABLE IF NOT EXISTS Company(" +
                "CompanyID INT NOT NULL AUTO_INCREMENT," +
                "PRIMARY KEY (CompanyID)," +
                "CompanyName VARCHAR(50));");
        Company.executeUpdate();

        PreparedStatement TV = connect.prepareStatement("CREATE TABLE IF NOT EXISTS TV(" +
                "Model INT," +
                "CompanyID INT,"+
                "Size INT,"+
                "PRIMARY KEY (Model),"+
                "FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID));");
        TV.executeUpdate();

        PreparedStatement Newspaper = connect.prepareStatement("CREATE TABLE IF NOT EXISTS Newspaper(" +
                "CompanyID INT," +
                "UID INT,"+
                "FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID),"+
                "FOREIGN KEY (UID) REFERENCES Persons(UID));");
        Newspaper.executeUpdate();

        PreparedStatement PersonTvOrder = connect.prepareStatement("CREATE TABLE IF NOT EXISTS PersonTvOrder(" +
                "UID INT," +
                "Model INT,"+
                "FOREIGN KEY (Model) REFERENCES TV(Model),"+
                "FOREIGN KEY (UID) REFERENCES Persons(UID));");
        PersonTvOrder.executeUpdate();
    }
    public void PersonInsert(String firstName, String lastName, String streetAddress) throws SQLException{


        PreparedStatement PersonTuple = connect.prepareStatement("INSERT INTO Persons(firstName, lastName, streetAddress)" +
                "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

        PersonTuple.setString(1, firstName);
        PersonTuple.setString(2, lastName);
        PersonTuple.setString(3, streetAddress);

        PersonTuple.executeUpdate();

        ResultSet rsKey = PersonTuple.getGeneratedKeys();

        if (rsKey.next()){
            pID = rsKey.getInt(1);
        }
    }
    public void CompanyInsert(String companyName) throws SQLException{
        PreparedStatement CompanyTuple = connect.prepareStatement("INSERT INTO Company(companyName)" +
                "VALUES(?)", Statement.RETURN_GENERATED_KEYS);

        CompanyTuple.setString(1, companyName);

        CompanyTuple.executeUpdate();

        ResultSet rsKey = CompanyTuple.getGeneratedKeys();


        if (rsKey.next()){
            cID = rsKey.getInt(1);
        }
    }
    public void TVInsert(int model, int size) throws SQLException{

        PreparedStatement TVtuple = connect.prepareStatement("INSERT INTO TV(model, companyID, size)" +
                "VALUES(?,?,?)");

        TVtuple.setInt(1, model);
        TVtuple.setInt(2, cID);
        TVtuple.setInt(3, size);

        TVtuple.executeUpdate();
    }
    public void NewspaperInsert() throws SQLException{
        PreparedStatement NewspaperTuple = connect.prepareStatement("INSERT INTO Newspaper(companyID, UID)" +
                "VALUES(?,?)");

        NewspaperTuple.setInt(1, cID);
        NewspaperTuple.setInt(2, pID);

        NewspaperTuple.executeUpdate();
    }

    public void PersonTvOrderInsert(int model) throws SQLException {
        PreparedStatement TvOrderTuple = connect.prepareStatement("INSERT INTO PersonTvOrder(UID, model)" +
                "VALUES(?,?)");

        TvOrderTuple.setInt(1, pID);
        TvOrderTuple.setInt(2, model);

        TvOrderTuple.executeUpdate();
    }
    public void getResults(String tableName) throws SQLException{
        PreparedStatement testTable = connect.prepareStatement("SELECT * FROM `" + tableName + "`"); //Uses dynamic table name and creates necessary select statement
        ResultSet tableResults = testTable.executeQuery();

        printTable(tableResults);
    }


    public void printTable(ResultSet results) throws SQLException {
        ResultSetMetaData rsmd = results.getMetaData();
        int columnsNumber = rsmd.getColumnCount();



        while (results.next()) {

            for (int i = 1; i <= columnsNumber; i++) {

                System.out.print(results.getString(i) + " ");

            }

            System.out.println("\n");

        }
    }
    public void printAllTables() throws SQLException {
        getResults("Persons");
        getResults("Company");
        getResults("TV");
        getResults("Newspaper");
        getResults("PersonTvOrder");
    }

}
