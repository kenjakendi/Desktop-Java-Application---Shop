package pap;

import dbUtility.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBinquiry {
    Connection connection;

    public DBinquiry(){
        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection == null){
            System.exit(1);
        }
    }
    public boolean isLogin(String user,String pass) throws Exception{
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM logins where username = ? and password = ?";
        try{
            pr = this.connection.prepareStatement(sql);
            pr.setString(1,user);
            pr.setString(2,pass);

            rs = pr.executeQuery();

            boolean boll1;
            if(rs.next()){
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            return false;
    }
    finally {
            pr.close();
            rs.close();
        }
    }
}
