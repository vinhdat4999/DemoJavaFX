package Controller;

import Model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentDetailController{
    @FXML
    Label studentCode;
    @FXML
    TextField name;
    @FXML
    TextField unit;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField address;

    public void setStudent(Student student) {
        studentCode.setText(student.getStudentCode());
        name.setText(student.getName());
        unit.setText(student.getUnit());
        phoneNumber.setText(student.getPhoneNumber());
        address.setText(student.getAddress());
    }
    public Student getStudent(){
        String studentCodeText = studentCode.getText();
        String nameText = name.getText();
        String unitText = unit.getText();
        String phoneNumberText = phoneNumber.getText();
        String addressText = address.getText();
        Student student = new Student(studentCodeText,nameText,unitText,phoneNumberText,addressText);
        return student;
    }

    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        loader.load();
        stage.close();
    }

    public void UpdateStudent(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        loader.load();
        stage.close();

    }

}
