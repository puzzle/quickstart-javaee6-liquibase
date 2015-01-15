package ch.puzzle.quickstart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@SuppressWarnings("serial")
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Resource(lookup = "java:comp/env/jdbc/liquibaseDS")
    private DataSource dataSource;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = dataSource.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("<html><head><title>helloworld</title></head><body>");
            writer.println("<h1>Hello from OpenShift</h1>");
            writer.println();
            writer.println("<h2>DBMS</h2>" + databaseMetaData.getDatabaseProductName() + " " + databaseMetaData.getDatabaseProductVersion());           
            writer.println("<h2>Tables</h2>");
            
            ResultSet result = databaseMetaData.getTables(null, null, null, new String[]{ "TABLE" });
            while(result.next()) {
                writer.println("<h3>Table " + result.getString("TABLE_NAME") + "</h3><table><tr><th align=\"left\">Name</th><th></th><th align=\"left\">Type</th></tr>");
                ResultSet result2 = databaseMetaData.getColumns(null, null, result.getString("TABLE_NAME"), null);
                while(result2.next()) {
                    String type = result2.getString("TYPE_NAME");                    
                    if (type.contains("char")) {
                      type += "(" + result2.getString("CHAR_OCTET_LENGTH") + ")";
                    }
                    writer.println("<tr><td>" + result2.getString("COLUMN_NAME") + "</td><td>&nbsp;</td><td>" + type + "</td></tr>");                    
                }
                writer.println("</table>");
            }
                     
            writer.println();
            writer.println("</body></html>");
            writer.close();

            connection.close();      
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
