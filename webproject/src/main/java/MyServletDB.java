import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class demo3
 */
@WebServlet("/demo4")
public class MyServletDB extends HttpServlet {
	static String url = "jdbc:mysql://ec2-52-23-157-72.compute-1.amazonaws.com/myDB";
	static String user = "trevorthomas";
	static String password = "pass";
private static final long serialVersionUID = 1L;
 String dnsOld = "ec2-3-20-227-209.us-east-2.compute.amazonaws.com";
 String dns = "ec2-18-207-120-28.compute-1.amazonaws.com";
    Connection connection = null;
 Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletDB() {
        super();
        // TODO Auto-generated constructor stub
    }
/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse 
response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException {
// TODO Auto-generated method stub
 PrintWriter out = response.getWriter();
 try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("Where is your MySQL JDBC Driver?");
        e.printStackTrace();
        return;
    }
  // Provide your username and password in place of admin1 and root.
    try {
        connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        System.out.println("Connection Failed!:\n" + e.getMessage());
    }
    if (connection != null) {
        System.out.println("SUCCESS!!!! You made it, take control your database now!");
        System.out.println("Creating statement...");
        try {
statement = connection.createStatement();
} catch (SQLException e3) {
// TODO Auto-generated catch block
e3.printStackTrace();
}
        String sql;
        sql = "SELECT * FROM books";
        ResultSet rs = null;
try {
rs = statement.executeQuery(sql);
} catch (SQLException e2) {
// TODO Auto-generated catch block
e2.printStackTrace();
}
        //STEP 5: Extract data from result set
        try {
while (rs.next()) {
    //Retrieve by column name
String title = rs.getString("title");
String author = rs.getString("author");
String genre = rs.getString("genre");
String isbn = rs.getString("isbn");
String summary = rs.getString("summary");
    
//Display values
out.println("Title: " + title + ", ");
out.println("Author: " + author + ", ");
out.println("Genre: " + genre + ", ");
out.println("ISBN: " + isbn + ", ");
out.println("Summary: " + summary);
out.println("<br>");
}
} catch (SQLException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
        //STEP 6: Clean-up environment
        try {
rs.close();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        try {
statement.close();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
        try {
connection.close();
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
    } 
   
    else {
        System.out.println("FAILURE! Failed to make connection!");
    }
}
/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}
}