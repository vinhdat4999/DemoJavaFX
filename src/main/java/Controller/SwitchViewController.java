package Controller;

import Model.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SwitchViewController{
    @FXML
    Label studentCode;
    @FXML
    Label name;
    @FXML
    Label unit;
    @FXML
    Label phoneNumber;
    @FXML
    Label address;
    @FXML
    MenuButton dropDown;

    public void setMenu(ObservableList<Student> list){
        for(Student i:list){
            dropDown.getItems().add(new MenuItem(i.getStudentCode()));
        }
    }
    public void DropDownClicked(ActionEvent e){
//        Student student = (Student) dropDown.getItems();
//        System.out.println(student.toString());
        System.out.println("ffffggggggg");
    }

    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }
}
