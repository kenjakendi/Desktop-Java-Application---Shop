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

    public void dropToBase(Item item, int volume ) throws Exception {
        PreparedStatement pr = null;

        String sql =  "INSERT OR REPLACE INTO products (id, name, price, volume)" + "VALUES( ?, ? , ?, ?);";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setInt(1, item.getId());
            pr.setString(2, item.getName());
            pr.setDouble(3, item.getPrice());
            pr.setInt(4,volume);

            pr.execute();


        } catch (SQLException ex) {

        } finally {
            pr.close();

        }

    }

    public void insertStatistics(int id, double income) throws Exception {
        PreparedStatement pr = null;

        String sql =  "INSERT INTO transactions (id, income)" + "VALUES( ?, ?);";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setInt(1, id);
            pr.setDouble(2, income);
            pr.execute();


        } catch (SQLException ex) {

        } finally {
            pr.close();

        }

    }

    public int getLastId() throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM transactions ORDER BY id DESC";
        try {
            pr = this.connection.prepareStatement(sql);

            rs = pr.executeQuery();
            rs.next();

            int id = rs.getInt(1);
            return id;
        } catch (SQLException ex) {
            return 0;
        } finally {
            pr.close();
            rs.close();
        }
    }

    public double getTransaction(int id) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM transactions where id = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setInt(1, id);

            rs = pr.executeQuery();

            double income = 0;
            while (rs.next()){
                income = income + rs.getDouble(2);
            }
            return income;
        } catch (SQLException ex) {
            return 0;
        } finally {
            pr.close();
            rs.close();
        }
    }
}
