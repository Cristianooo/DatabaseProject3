import com.github.javafaker.Faker;
import java.util.Random;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class dataGenerator {
    Faker faker = new Faker();
    Random rand = new Random();

    private String firstName;
    private String lastName;
    private String streetAddress;
    private int TvModel;
    private int TvSize;
    private String companyName;
    private int TvModelmin = 1000;
    private int TvModelmax = 9999;

    int TvSizemin = 32;
    int TvSizemax = 90;

    ArrayList<String> dataLines = new ArrayList<String>();

    void runData(){
        for(int i =0; i<=500; i++){
            //autoincrement UID
            firstName = faker.name().firstName();
            lastName = faker.name().lastName();
            streetAddress = faker.address().streetAddress();

            TvModel = rand.nextInt((TvModelmax - TvModelmin) + 1) + TvModelmin;
            //foreign key companyID
            TvSize  = rand.nextInt((TvSizemax - TvSizemin)+ 1) + TvSizemin;

            //Foreign key companyID
            //foreign key UID

            //Autoincrement companyID
            companyName = faker.company().name();

            //foreign key UID
            //foreign key model




            try {
                DriverManager.getConnection("jdbc:mysql://35.233.241.135/cpsc408?useSSL=false","test",
                        "password");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }



    }









}
