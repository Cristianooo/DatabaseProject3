import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class ParseCSV {

    private String firstName;
    private String lastName;
    private String streetAddress;
    private int TvModel;
    private int TvSize;
    private boolean newsLetterBool;
    private String companyName;

    DatabaseFunctions myDatabaseFunctions = new DatabaseFunctions();
    int count;

    private static final String FilePath = "/Users/cristianofirmani/Docs/DataGeneration/DummyData.csv";

    public void readData(String FilePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(FilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)
        ) {
            for (CSVRecord csvRecord : csvParser) {
                count++;
                // Accessing Values by Column Index
                firstName = csvRecord.get(0);
                lastName = csvRecord.get(1);
                streetAddress = csvRecord.get(2);

                TvModel = Integer.valueOf(csvRecord.get(3));
                TvSize = Integer.valueOf(csvRecord.get(4));

                newsLetterBool = Boolean.valueOf(csvRecord.get(5));

                companyName = csvRecord.get(6);
                try {

                    myDatabaseFunctions.PersonInsert(firstName, lastName, streetAddress);
                    myDatabaseFunctions.CompanyInsert(companyName);
                    myDatabaseFunctions.TVInsert(TvModel, TvSize);
                    myDatabaseFunctions.NewspaperInsert();
                    myDatabaseFunctions.PersonTvOrderInsert(TvModel);

                    System.out.println("Tuple " + count + "added to the database");
                }
                catch(SQLException e) {
                    System.out.println(e);
                }


            }
        }
    }
}
