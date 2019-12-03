import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Task2 {

    public static void main(String args[]) {
        Connection conn = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ProjectTeam?serverTimezone=UTC&useSSL=TRUE";
            String user, pass;
            user = "student";
            pass = "password";
            //user = readEntry("userid : ");
            //pass = readEntry("password: ");
            conn = DriverManager.getConnection(url, user, pass);

            statisticsMenu(conn);


        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
    }
    private static void statisticsMenu(Connection conn){
        try {
            boolean done = false;
            do {
                printMenu();
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case 'a':
                        findNomineeScore(conn);
                        break;
                    case 'b':
                        findWinsPerTeam(conn);
                        break;
                    case 'c':
                        findParticipation(conn);
                        break;
                    case 'q':
                        done = true;
                        break;
                    default:
                        System.out.println(" Not a valid option ");
                } //switch
            } while (!done);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void findNomineeScore(Connection conn) throws SQLException, IOException {

        //STEP1: CREATE VARIABLE OF TYPE STATEMENT
        Statement stmt = conn.createStatement();
        // STEP 2 DEFINE A STRING THAT IS = TO YOUR query SQL Statement
        String query = "SELECT p.firstName, p.lastName, e.evaluationScore, e.evaluationDate FROM Evaluation AS e INNER JOIN Person AS p ON p.SSN = e.NomineeSSN ORDER BY p.lastName ASC, p.firstName ASC";

        // Step 3: Declare a variable with ResultSet type
        ResultSet rset = stmt.executeQuery(query);
        //Execute your Query and store the return in the declared variable from step 3

        System.out.println("    Nominee Scores");
        System.out.format ("%-15s%-15s%-10s%-15s","FirstName","LastName","Score","Evaluation Date");
        System.out.println("\n--------------------------------------------------");

        // Write a loop to read all the returned rows from the query execution
        while(rset.next()){
            String fname = rset.getString(1);
            String lname = rset.getString(2);
            int score = rset.getInt(3);
            String evalDate = rset.getString(4);
            System.out.format ("%-15s%-15s%-10d%-15s",fname,lname,score,evalDate);
            System.out.println();
        }

        //Close the statement
        stmt.close();
    }


    private static void findWinsPerTeam(Connection conn) throws SQLException, IOException {

        // Complete this method following the same steps above to return the required information
        //STEP1: CREATE VARIABLE OF TYPE STATEMENT
        Statement stmt = conn.createStatement();
        // STEP 2 DEFINE A STRING THAT IS = TO YOUR query SQL Statement
        String query = "SELECT teamName, COUNT(winningTeam) FROM Team LEFT OUTER JOIN Game ON winningTeam = teamName WHERE (YEAR(gameDate) > (YEAR(CURDATE()) - 5)) OR gameDate IS NULL GROUP BY teamName";

        // Step 3: Declare a variable with ResultSet type
        ResultSet rset = stmt.executeQuery(query);
        //Execute your Query and store the return in the declared variable from step 3

        System.out.println("    Wins Per Team");
        System.out.format ("%-15s%-15s","Team","Win Count");
        System.out.println("\n--------------------------------------------------");

        // Write a loop to read all the returned rows from the query execution
        while(rset.next()){
            String tname = rset.getString(1);
            int winCount = rset.getInt(2);
            System.out.format ("%-15s%-15d",tname,winCount);
            System.out.println();
        }

        stmt.close();
    }

    private static void findParticipation(Connection conn) throws SQLException, IOException {

        // Complete this method following the same steps above to return the required information
        //STEP1: CREATE VARIABLE OF TYPE STATEMENT
        Statement stmt = conn.createStatement();
        // STEP 2 DEFINE A STRING THAT IS = TO YOUR query SQL Statement
        String query = "SELECT DISTINCT teamName FROM Game INNER JOIN Play ON Play.playgameID = Game.gameID INNER JOIN Team ON Team.teamID = Play.playteamID WHERE (YEAR(gameDate) > (YEAR(CURDATE()) - 5))";

        // Step 3: Declare a variable with ResultSet type
        ResultSet rset = stmt.executeQuery(query);
        //Execute your Query and store the return in the declared variable from step 3

        System.out.println("Participating Championship Teams");
        System.out.println("--------------------------------------------------");

        // Write a loop to read all the returned rows from the query execution
        while(rset.next()){
            String tname = rset.getString(1);
            System.out.println(tname);
        }

        stmt.close();
    }

    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            int c = System.in.read();
            while(c != '\n' && c != -1) {
                buffer.append((char)c);
                c = System.in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }

    private static String readLine() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr, 1);
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Error in SimpleIO.readLine: " +
                    "IOException was thrown");
            System.exit(1);
        }
        return line;
    }

    private static void printMenu() {
        System.out.println("\n***********************************************************");
        System.out.println("\t Welcome to Selecting an All-Star Team Application");
        System.out.println("\t\t\t\t2. Statistics & Data Mining");
        System.out.println("***********************************************************");
        System.out.println("(a) Score");
        System.out.println("(b) Wins per Team");
        System.out.println("(c) Championship Participation");
        System.out.println("(q) Quit. \n");
    }

}