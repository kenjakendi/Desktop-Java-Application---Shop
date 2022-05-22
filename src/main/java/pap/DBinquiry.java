package pap;

import dbUtility.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBinquiry {
    Connection connection;

    public DBinquiry() {
        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isLogin(String user, String pass) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM logins where username = ? and password = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);

            rs = pr.executeQuery();

            boolean boll1;
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        } finally {
            pr.close();
            rs.close();
        }
    }
    public String getProduct(String product_name) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM products where name = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, product_name);

            rs = pr.executeQuery();
            rs.next();

            String name = rs.getString(2);
            return name;
        } catch (SQLException ex) {
            return "falsz";
        } finally {
            pr.close();
            rs.close();
        }
    }

    public Map getALLProduct() throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;
        Map<Item, Integer> items = new HashMap<>();

        String sql = "SELECT * FROM products ";
        try {
            pr = this.connection.prepareStatement(sql);

            rs = pr.executeQuery();
            while (rs.next()){
                Item pulled_item = new Item();
                pulled_item.setId(rs.getInt(1));
                pulled_item.setName(rs.getString(2));
                pulled_item.setPrice(rs.getDouble(3));
                items.put(pulled_item, rs.getInt((4)));
            }

            return items;
        } catch (SQLException ex) {
            Map<Item, Integer> err = new HashMap<>();
            return err;
        } finally {
            pr.close();
            rs.close();
        }
    }

    public void setProduct(String product_name, int new_volume) throws Exception {
        PreparedStatement pr = null;

         String sql =  "UPDATE products SET volume = "+ new_volume +"  WHERE name = ?";

        try {
            pr = this.connection.prepareStatement(sql);
           pr.setString(1, product_name);

            pr.execute();


        } catch (SQLException ex) {

        } finally {
            pr.close();

        }
    }
}