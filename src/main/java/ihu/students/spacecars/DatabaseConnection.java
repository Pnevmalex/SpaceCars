package ihu.students.spacecars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static Connection databaseLink;
    public static Statement statement;

    public static Connection getDBConnection() {
        String url = "jdbc:postgresql://dblabs.iee.ihu.gr:5432/";
        String username = "username";
        String passwd = "password";
        try {
            Class.forName("org.postgresql.Driver");
            databaseLink = DriverManager.getConnection(url, username, passwd);
            statement = databaseLink.createStatement();

            statement.execute("CREATE OR REPLACE FUNCTION add_car(int, varchar, varchar, int, int) RETURNS VOID AS $$" +
                    "INSERT INTO CARS (" +
                    "CARID," +
                    "MAKE," +
                    "MODEL," +
                    "YEAR," +
                    "PRICE" +
                    ") VALUES ($1, $2, $3, $4, $5) " +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION update_car(int, varchar, varchar, int, int) RETURNS VOID AS $$" +
                    "UPDATE CARS " +
                    "SET CARID = $1, MAKE = $2, MODEL = $3, YEAR = $4, PRICE = $5" +
                    "WHERE CARID = $1" +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION delete_car(int) RETURNS VOID AS $$" +
                    "DELETE FROM CARS " +
                    "WHERE CARID = $1" +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION user_login(varchar, varchar) RETURNS VARCHAR AS $$" +
                    "SELECT EMAIL FROM ADMINS WHERE USERNAME = $1 AND USERPASS = $2;" +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION add_admin(varchar, varchar, varchar, varchar) RETURNS VOID AS $$" +
                    "INSERT INTO ADMINS (" +
                    "EMAIL," +
                    "FULLNAME," +
                    "USERNAME," +
                    "USERPASS" +
                    ") VALUES ($1, $2, $3, $4) " +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION add_customer(varchar, varchar, int) RETURNS VOID AS $$" +
                    "INSERT INTO CUSTOMERS (" +
                    "FULLNAME," +
                    "PHONE," +
                    "CUSTOMERCARID" +
                    ") VALUES ($1, $2, $3) " +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION update_customer(varchar, varchar, int) RETURNS VOID AS $$" +
                    "UPDATE CUSTOMERS " +
                    "SET FULLNAME = $1, PHONE = $2, CUSTOMERCARID = $3" +
                    "WHERE FULLNAME = $1" +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION delete_customer(varchar, int) RETURNS VOID AS $$" +
                    "DELETE FROM CUSTOMERS " +
                    "WHERE FULLNAME = $1 AND CUSTOMERCARID = $2;" +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION add_emp(varchar, int, varchar, varchar) RETURNS VOID AS $$" +
                    "INSERT INTO EMPLOYEES (" +
                    "FULLNAME," +
                    "AGE," +
                    "PHONE," +
                    "CATEGORY" +
                    ") VALUES ($1, $2, $3, $4) " +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION update_emp(varchar, int, varchar, varchar) RETURNS VOID AS $$" +
                    "UPDATE EMPLOYEES " +
                    "SET FULLNAME = $1, AGE = $2, PHONE = $3, CATEGORY = $4" +
                    "WHERE PHONE = $3;" +
                    "$$ LANGUAGE SQL;");

            statement.execute("CREATE OR REPLACE FUNCTION delete_emp(varchar, varchar) RETURNS VOID AS $$" +
                    "DELETE FROM EMPLOYEES " +
                    "WHERE FULLNAME = $1 AND PHONE = $2;" +
                    "$$ LANGUAGE SQL;");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return databaseLink;
    }
}
