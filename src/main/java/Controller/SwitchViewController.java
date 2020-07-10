package Controller;

import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

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
    ComboBox comboBox;
    ObservableList<Student> list;

    public void setDetail(Student student){
        studentCode.setText("Mã sinh viên : " + student.getStudentCode());
        name.setText(       "Tên                : " + student.getName());
        unit.setText(       "Lớp                : " + student.getUnit());
        phoneNumber.setText("SĐT                : " + student.getPhoneNumber());
        address.setText(    "Địa chỉ           : " + student.getAddress());
    }

    public void setMenu(ObservableList<Student> listNew){
        list = FXCollections.observableArrayList();
        list = listNew;
        ObservableList<String> codeList = FXCollections.observableArrayList();
        for(Student i:listNew){
            codeList.add(i.getStudentCode());
        }
        comboBox.setItems(codeList);
    }

    public Student getStudent(String id){
        for(Student i:list){
            if(i.getStudentCode().equals(id))
                return i;
        }
        return null;
    }

    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }

    public void ClickedCB(ActionEvent e) {
        String id = comboBox.getSelectionModel().getSelectedItem().toString();
        setDetail(getStudent(id));
    }
}
