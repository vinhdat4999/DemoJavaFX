package Controller;

import Model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    @FXML
    private Label studentCode;
    @FXML
    private TextField name;
    @FXML
    private TextField unit;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
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
    public void AddStudent(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        loader.load();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random random = new Random();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller controller = loader.getController();
        int n = random.nextInt(900000)+100000;
        while (controller.checkExistCode(n+"")){
            n = random.nextInt(900000)+100000;
        }
        studentCode.setText(n+"");
    }
}
