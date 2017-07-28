
import java.sql.*;

public class DatabaseFunctions
{

    Connection conn;
    Statement stmt;
    ResultSet rs;

    public DatabaseFunctions()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void deleteRow(String table, String rowName, String rowConfirm)
    {
        try
        {
            ResultSet rs = stmt.executeQuery("select * from " + table + " where " + rowName + " = '" + rowConfirm + "'");
            if (rs.next())
            {
                rs.deleteRow();
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
