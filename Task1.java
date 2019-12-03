import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Task1 {

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

            checkCredentials(conn);

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
    public static void addDelete(Connection conn){
        Scanner console = new Scanner(System.in);
        boolean done = false;
        try {
            do {
                updateMenu();
                System.out.println("Type in your option: ");
                System.out.flush();
                String chh = readLine();
                System.out.println();
                Statement st = conn.createStatement();
                switch (chh.charAt(0)) {
                    case 'a':
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
                        System.out.println("Number of times this player made the All-Star Team:");
                        String teamSelection = console.next();
                        System.out.println("Number of years with the team:");
                        int yearsTeam = console.nextInt();
                        System.out.println("Position the player plays:");
                        String position = console.next();
                        st.executeUpdate("INSERT INTO Nominee VALUES ('" + ssn + "'" + ", " + "'" + teamSelection + "'" + ", " + yearsTeam + ", " + "'" + position + "'" + ")");
                        System.out.println("Type in an existing team ID the player plays for:");
                        int pTeamID = console.nextInt();
                        st.executeUpdate("INSERT INTO Player VALUES ('" + ssn + "'" + ", " + pTeamID + ")");
                        break;
                    case 'b':
                        System.out.println("Type in the SSN of the coach (9 digits max):");
                        String cssn = console.next();
                        System.out.println("Type in the address of the coach:");
                        String caddress = console.next();
                        System.out.println("Type in the first name of the coach:");
                        String cfname = console.next();
                        System.out.println("Type in the last name of the coach:");
                        String clname = console.next();
                        System.out.println("Type in the birthdate of the coach (in the form 'yyyy-mm-dd'):");
                        String cdate = console.next();
                        System.out.println("Type in the number of years the coach has been with the university (whole number):");
                        int cyears = console.nextInt();
                        System.out.println("Type in the name of the university the coach belongs to:");
                        String cunivname = console.next();
                        st.executeUpdate("INSERT INTO Person VALUES ('" + cssn + "'" + ", " + "'" + caddress + "'" + ", " + "'" + cfname + "'" + ", " + "'" + clname + "'" + ", " + "'" + cdate + "'" + ", " + cyears + ", " + "'" + cunivname + "'" + ")");
                        System.out.println("Type in the number of years the coach has been coaching:");
                        int yearsCoaching = console.nextInt();
                        System.out.println("Type in the number of times the coach's team has won the championship:");
                        int teamsWon = console.nextInt();
                        System.out.println("Type in the number of times the coach led his team to the semifinals:");
                        int teamsInSF = console.nextInt();
                        System.out.println("Type in an existing team ID the coach coaches:");
                        int cTeamID = console.nextInt();
                        st.executeUpdate("INSERT INTO Coach VALUES ('" + cssn + "'" + ", " + yearsCoaching + ", " + teamsWon + ", " + teamsInSF + ", " + cTeamID + ")");
                        break;
                    case 'c':
                        System.out.println("Type in the team's ID:");
                        int teamID = console.nextInt();
                        System.out.println("Type in the name of the team:");
                        String teamName = console.next();
                        System.out.println("Type in the number of games the team has won:");
                        int gamesWon = console.nextInt();
                        System.out.println("Type in the number of games the team has lost:");
                        int gamesLost = console.nextInt();
                        System.out.println("Type in the university the team belongs to:");
                        String tuniversityName = console.next();
                        System.out.println("Type in the team's rank:");
                        int teamRank = console.nextInt();
                        st.executeUpdate("INSERT INTO Team VALUES (" + teamID + ", " + "'" + teamName + "'" + ", " + gamesWon + ", " + gamesLost + ", " + "'" + tuniversityName + "'" + ", " + teamRank + ")");
                        break;
                    case 'd':
                        System.out.println("Type in the SSN of the player you wish to remove:");
                        String rpssn = console.next();
                        String sql = "delete from Person where SSN=" + rpssn;
                        st.executeUpdate(sql);
                        break;
                    case 'e':
                        System.out.println("Type in the SSN of the coach you wish to remove:");
                        String rcssn = console.next();
                        String sql2 = "delete from Person where SSN=" + rcssn;
                        st.executeUpdate(sql2);
                        break;
                    case 'f':
                        System.out.println("Type in the team ID of the team you wish to remove:");
                        int rteamID = console.nextInt();
                        String sql3 = "delete from Team where teamID=" + rteamID;
                        st.executeUpdate(sql3);
                        break;
                    case 'q':
                        done = true;
                        break;
                    default:
                        System.out.println("Not a valid option.");
                        break;
                }
            } while (!done);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public static void checkCredentials(Connection conn){
        Scanner console = new Scanner(System.in);

        //Scanner console = new Scanner(System.in);
        System.out.println("Enter User ID: ");
        String user = console.next();
        System.out.println("Enter password: ");
        String password = console.next();

        while (!user.equals("kevin") || !password.equals("cs331")) {
            System.out.println("Access denied. Press 'c' to try again or press 'u' to quit.");
            String request = console.next();
            if (request.equals("c")) {
                System.out.println("Enter User ID: ");
                user = console.next();
                System.out.println("Enter password: ");
                password = console.next();
            }
            if (request.equals("u")) {
                break;
            }
        }


        if (user.equals("kevin") && password.equals("cs331")) {
            System.out.println("Logging in. Please wait...");
            //TimeUnit.MILLISECONDS.sleep(800L);
            addDelete(conn);
        }
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
            System.out.println("Error in SimpleIO.readLine: IOException was thrown");
            System.exit(1);
        }

        return line;
    }
}
