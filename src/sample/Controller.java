package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;


public class Controller {


    @FXML // fx:id="tableView";
            TableView<Employee> tableView;

    public void doLoad(){

        ObservableList<Employee> values =
                Employee.getEmployees();

        TableColumn<Employee,String> name
                = new TableColumn<Employee, String>("name");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Employee, String> birthdate
                = new TableColumn<Employee, String>("birthdate");
        birthdate.setCellValueFactory(new PropertyValueFactory("birthdate"));

        tableView.getColumns().setAll(name, birthdate);
        tableView.setItems(values);
    }


}