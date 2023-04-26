package database;

import com.bridgelabz.db.base.BaseClass;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAutomation extends BaseClass
{
    @Test
    public void getTableData_Success() throws SQLException
    {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * From Employee");
        while(rs.next())
        {
            int EmpID = rs.getInt(1);
            String EmpName = rs.getString(2);
            int EmpAge = rs.getInt(3);
            String EmpDept = rs.getString(4);
            System.out.println(EmpID +" "+EmpName+" "+EmpAge+" "+EmpDept );
        }
    }
    @Test
    public void insertIntoTable_Success() throws SQLException
    {
        PreparedStatement pst = con.prepareStatement("INSERT INTO Employee VALUES(?,?,?,?)");
        pst.setInt(1,101);
        pst.setString(2,"Micky");
        pst.setInt(3,25);
        pst.setString(4,"IT");

        pst.executeUpdate();
        getTableData_Success();
    }
    @Test
    public void updateIntoTable_Success() throws SQLException
    {
        PreparedStatement pst = con.prepareStatement("UPDATE Employee SET EmpName = ? WHERE EmpID = ?");
        pst.setString(1,"Mouse");
        pst.setInt(2,102);
        pst.executeUpdate();
        getTableData_Success();
    }
    @Test
    public void extractSpecificData_Success() throws SQLException
    {
        PreparedStatement pst = con.prepareStatement("SELECT * FROM Employee where EmpID = ?");
        pst.setInt(1,103);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            int EmpID = rs.getInt(1);
            String EmpName = rs.getString(2);
            int EmpAge = rs.getInt(3);
            String EmpDept = rs.getString(4);
            System.out.println(EmpID+" "+EmpName+" "+EmpAge+" "+EmpDept);
        }
    }
    @Test
    public void deleteData_Success() throws SQLException
    {
        PreparedStatement pst = con.prepareStatement("DELETE FROM Employee where EmpID = ?");
        pst.setInt(1,103);
        pst.executeUpdate();
        getTableData_Success();
    }

}
