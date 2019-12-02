import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ProjectTeam {
    public ProjectTeam() {
    }

    public static void main(String[] args) throws Exception {
        menu();
        System.out.println("Type in your option: ");
        Scanner console = new Scanner(System.in);
        int number = console.nextInt();

        while (number != 4) {
            if (number == 1) {
                searchAndBrowse();
                break;
            } else if (number == 3) {
                System.out.println("Enter User ID: ");
                String user = console.next();
                System.out.println("Enter password: ");
                String password = console.next();

                while (!user.equals("kevin") || !password.equals("cs331")) {
                    System.out.println("Access denied. Press 'c' to try again or press 'q' to quit.");
                    String request = console.next();
                    if (request.equals("c")) {
                        System.out.println("Enter User ID: ");
                        user = console.next();
                        System.out.println("Enter password: ");
                        password = console.next();
                    }

                    if (request.equals("q")) {
                        menu();
                        System.out.println("Type in your option: ");
                        number = console.nextInt();
                        break;
                    }
                }

                if (user.equals("kevin") && password.equals("cs331")) {
                    System.out.println("Logging in. Please wait...");
                    TimeUnit.MILLISECONDS.sleep(800L);
                    Connection conn = null;

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/ProjectTeam?useSSL=TRUE";
                        String username = "student";
                        String pass = "password";
                        conn = DriverManager.getConnection(url, username, pass);
                        boolean done = false;

                        do {
                            updateMenu();
                            System.out.println("Type in your option: ");
                            System.out.flush();
                            String ch = readLine();
                            System.out.println();
                            switch (ch.charAt(0)) {
                                case 'a':
                                    Statement st = conn.createStatement();
                                    System.out.println("Type in the SSN of the player (9 digits max):");
                                    String ssn = console.next();
                                    System.out.println("Type in the address of the player:");
                                    String address = console.next();
                                    System.out.println("Type in the first name of the player:");
                                    String fname = console.next();
                                    System.out.println("Type in the last name of the player:");
                                    String lname = console.next();
                                    System.out.println("Type in the birthdate of the player (in the form 'yyyy-mm-dd'):");
                                    String date = console.next();
                                    System.out.println("Type in the number of years the player attended the university (whole number):");
                                    int years = console.nextInt();
                                    System.out.println("Type in the name of the university the player attends:");
                                    String univname = console.next();
                                    st.executeUpdate("INSERT INTO Person VALUES ('" + ssn + "'" + ", " + "'" + address + "'" + ", " + "'" + fname + "'" + ", " + "'" + lname + "'" + ", " + "'" + date + "'" + ", " + years + ", " + "'" + univname + "'" + ")");
                                case 'b':
                                case 'c':
                                case 'd':
                                case 'e':
                                case 'f':
                                    break;
                                case 'g':
                                case 'h':
                                case 'i':
                                case 'j':
                                case 'k':
                                case 'l':
                                case 'm':
                                case 'n':
                                case 'o':
                                case 'p':
                                default:
                                    System.out.println("Not a valid option.");
                                    break;
                                case 'q':
                                    done = true;
                            }
                        } while (!done);
                    } catch (ClassNotFoundException var29) {
                        System.out.println("Could not load the driver");
                    } catch (SQLException var30) {
                        System.out.println(var30);
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException var28) {
                                ;
                            }
                        }
                    }
                }
                break;
            }
        }

        System.out.println("Program terminated.");
        console.close();
    }

    public static void menu() {
        System.out.println("*****************************************************************************");
        System.out.println("                                 ***********");
        System.out.println("                    Welcome to Selecting an All-Star Team");
        System.out.println("                                *************");
        System.out.println("*****************************************************************************");
        System.out.println("                       1. Search & Browse the Database");
        System.out.println("                         2. Statistics & Data Mining");
        System.out.println("                                  3. Updates");
        System.out.println("                                    4. Quit");
    }

    public static void searchAndBrowse() {
        Connection conn = null;
        try {
            conn = getConnection();
            boolean done = false;

            do {
                searchAndBrowseMenu();
                System.out.println("Type in your option: ");
                System.out.flush();
                Scanner console = new Scanner(System.in);
                int sbOption = console.nextInt();
                System.out.println();
                switch(sbOption) {
                    case 1:
                        findNomineesFromEachTeam(conn);
                        break;
                    case 2:
                        search();
                        break;
                    case 3:
                        done = true;
                        break;
                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
            } while (!done);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var28) {
                }
            }
        }
    }

    public static void searchAndBrowseMenu() {
        System.out.println("*****************************************************************************");
        System.out.println("                    Welcome to Selecting an All-Star Team");
        System.out.println("                       1. Search & Browse the Database");
        System.out.println("*****************************************************************************");
        System.out.println("                         1. Nominees from each team");
        System.out.println("                                   2. Search");
        System.out.println("                                    3. Quit");
    }

    private static void findNomineesFromEachTeam(Connection conn) throws SQLException, IOException {
        Statement stmt = conn.createStatement();
        String query = "select NSSN, firstName, lastName, position, evaluationScore\n" +
                "from Nominee n" +
                " join Evaluation e on n.NSSN = e.NomineeSSN" +
                " join Person p on n.NSSN = p.SSN" +
                " order by position, evaluationScore desc";

        System.out.println("Nominees from each team");

        ResultSet rset = stmt.executeQuery(query);
        String position = "";
        while(rset.next()){
            String currentPosition = rset.getString(4).trim();
            if (!position.equals(currentPosition)) {
                position = currentPosition;
                System.out.println("----------------------------");
                System.out.println("Position: " + currentPosition);
                System.out.println("----------------------------");
                System.out.println("FirstName\tLastName\tEvaluationScore");
            }
            System.out.println(rset.getString(2) + "\t" + rset.getString(3) + "\t" + rset.getString(5));
        }

        stmt.close();
    }

    public static void search() {
        Connection conn = null;
        try {
            conn = getConnection();
            boolean done = false;

            do {
                searchMenu();
                System.out.println("Type in your option: ");
                System.out.flush();
                Scanner console = new Scanner(System.in);
                int sbOption = console.nextInt();
                System.out.println();
                switch(sbOption) {
                    case 1:
                        teamInfo(conn);
                        break;
                    case 2:
                        gameInfo(conn);
                        break;
                    case 3:
                        coachInfo(conn);
                        break;
                    case 4:
                        done = true;
                        break;
                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
            } while (!done);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var28) {
                }
            }
        }
    }

    public static void searchMenu() {
        System.out.println("*****************************************************************************");
        System.out.println("                    Welcome to Selecting an All-Star Team");
        System.out.println("                       1. Search & Browse the Database");
        System.out.println("                                  2. Search");
        System.out.println("*****************************************************************************");
        System.out.println("                                  1. Team info");
        System.out.println("                                  2. Game info");
        System.out.println("                                  3. Coach info");
        System.out.println("                                     4. Quit");
    }

    private static void teamInfo(Connection conn) throws SQLException, IOException {
        System.out.println("Enter Team name: ");
        Scanner console = new Scanner(System.in);
        String teamname = console.next();

        Statement stmt = conn.createStatement();
        String query = "select firstName, lastName, teamRank, teamID from Team t" +
                " join Coach c on t.teamID = c.CteamID" +
                " join Person p on p.SSN = c.CSSN" +
                " where t.teamName = '" + teamname + "';";

        ResultSet rset = stmt.executeQuery(query);
        while(rset.next()){
            System.out.println("Coach name: " + rset.getString(1) + "\t" + rset.getString(2));
            System.out.println("Team rank: " + rset.getString(3));
            String teamId = rset.getString(3);

            Statement stmt1 = conn.createStatement();
            String query1 = "Select firstName, lastName from Person p" +
                    " join Player pl on p.SSN = pl.PSSN" +
                    " where pl.PteamID = "+ teamId + ";";

            System.out.println("Players");
            System.out.println("----------");
            ResultSet rset1 = stmt1.executeQuery(query1);
            while(rset1.next()){
                System.out.println(rset1.getString(1) + "\t" + rset1.getString(2));
            }
        }

        stmt.close();
    }

    private static void gameInfo(Connection conn) throws SQLException, IOException {
        System.out.println("Enter Team name: ");
        Scanner console = new Scanner(System.in);
        String teamName = console.next();

        Statement stmt = conn.createStatement();
        String query = "Select winningTeam, winningScore, losingScore, t.teamName playedAgainst from Game g" +
                " join" +
                " (Select p.*, tp.teamName from Play p" +
                " join (select t.teamName, p.* from Team t join Play p on p.playteamID = t.teamID where t.teamName = '" + teamName + "') tp on p.playgameID = tp.playGameID" +
                " where p.playteamID != tp.playteamID) gamesPlayed on g.gameID = gamesPlayed.playGameID" +
                " join Team t on gamesPlayed.playTeamID = t.teamID;";

        System.out.println("Games played");
        System.out.println("-------------");
        ResultSet rset = stmt.executeQuery(query);
        while(rset.next()){
            boolean won = teamName.equals(rset.getString(1));
            String result = won
                    ? "Win (" + rset.getString(2) + " - " + rset.getString(3) + ")"
                    : "Loss (" + rset.getString(3) + " - " + rset.getString(2) + ")";
            System.out.println("vs " + rset.getString(4) + ". " + result);
        }

        stmt.close();
    }

    private static void coachInfo(Connection conn) throws SQLException, IOException {
        System.out.println("Enter Coach name: ");
        Scanner console = new Scanner(System.in);
        String coachName = console.next();

        Statement stmt = conn.createStatement();
        String query = "Select teamName from Coach c" +
                " join Team t on c.CteamID = t.teamID" +
                " join Person p on c.CSSN = p.SSN" +
                " where p.firstName = '" + coachName + "' or p.lastName = '"+ coachName + "';";

        System.out.println("Team coached");
        ResultSet rset = stmt.executeQuery(query);
        while(rset.next()){
            System.out.println(rset.getString(1));
        }

        stmt.close();
    }

    public static void updateMenu() {
        System.out.println("                     Option A: Insert new information:");
        System.out.println("                         • Add a new player (press 'a')");
        System.out.println("                         • Add a new coach (press 'b')");
        System.out.println("                         • Add a new team (press 'c')");
        System.out.println();
        System.out.println("                     Option B: Delete some information:");
        System.out.println("                         • Delete a specific player (press 'd')");
        System.out.println("                         • Delete a specific coach (press 'e')");
        System.out.println("                         • Delete a specific team (press 'f')");
        System.out.println();
        System.out.println("                               *Press 'q' to QUIT");
    }

    static String readEntry(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();

            for (int c = System.in.read(); c != 10 && c != -1; c = System.in.read()) {
                buffer.append((char) c);
            }

            return buffer.toString().trim();
        } catch (IOException var3) {
            return "";
        }
    }

    private static String readLine() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr, 1);
        String line = "";

        try {
            line = br.readLine();
        } catch (IOException var4) {
            System.out.println("Error in SimpleIO.readLine: IOException was thrown");
            System.exit(1);
        }

        return line;
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ProjectTeam?serverTimezone=UTC&useSSL=TRUE";
            String username = "student";
            String pass = "password";
            conn = DriverManager.getConnection(url, username, pass);
        } catch (ClassNotFoundException var29) {
            System.out.println("Could not load the driver");
        } catch (SQLException var30) {
            System.out.println(var30);
        }

        return conn;
    }
}
