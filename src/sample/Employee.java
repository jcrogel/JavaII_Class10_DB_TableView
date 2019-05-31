package sample;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Employee {
    private StringProperty name;
    private StringProperty birthdate;

    public Employee(String name, String birthdate){
        this.birthdate = new SimpleStringProperty(this, "birthdate");
        this.name = new SimpleStringProperty(this, "name");
        this.setName(name);
        this.setBirthdate(birthdate);
    }

    public String getBirthdate() {
        return birthdate.get();
    }


    public void setBirthdate(String _birthdate) {
        this.birthdate.set(_birthdate);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        this.name.set(value);
    }


    public static ObservableList<Employee> getEmployees(){
        ObservableList<Employee> ret_val = FXCollections.observableArrayList();

        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url);

            ResultSet rs = null;
            boolean w_prepared_stmt = false; // Change me to try prepared or regular statement

            if (w_prepared_stmt){
                // Way with prepared statement
                PreparedStatement stmt = conn.prepareStatement("select first_name, birth_date from employees where first_name = ?");
                stmt.setString(1, "Elvis");
                rs = stmt.executeQuery();
            } else {
                // Way with regular statement
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery("select first_name, birth_date from employees");
            }

            while(rs!=null && rs.next()){
                String name = rs.getString(1);
                String date = rs.getDate(2).toString();
                ret_val.add(new Employee(name, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret_val;
    }
}
