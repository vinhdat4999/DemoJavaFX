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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView table;
    @FXML
    private TableColumn studentCode;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn unit;
    @FXML
    private TableColumn phoneNumber;
    @FXML
    private TableColumn address;
    @FXML
    private ObservableList<Student> list;
    private FileService fileService;
    private static final String filedir = "C:\\Users\\nguye\\Desktop\\text2.json";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileService = new FileService();
        list = fileService.readJson(filedir);
        studentCode.setCellValueFactory(new PropertyValueFactory<Student, String>("studentCode"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        unit.setCellValueFactory(new PropertyValueFactory<Student, String>("unit"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        table.setItems(list);
    }

    public boolean checkExistCode(String s) {
        for(Student i:list){
            if(i.getStudentCode().equals(s))
                return true;
        }
        return false;
    }

    public int getIndex(String code) {
        for (Student i : list) {
            if (i.getStudentCode().equals(code))
                return list.indexOf(i);
        }
        return -1;
    }

    public void AddStudent(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AddStudent.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.setScene(scene);
        newWindow.showAndWait();
        AddStudentController addStudent = loader.getController();
        if (!addStudent.getStudent().getName().equals(""))
            list.add(addStudent.getStudent());
        fileService.saveToJson(filedir);
    }

    public void ShowDetailStudent(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/StudentDetail.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        StudentDetailController controller = loader.getController();
        Student selected = (Student) table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn sinh viên cần chỉnh sửa");
            alert.show();
        } else {
            controller.setStudent(selected);
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(stage);
            newWindow.setScene(scene);
            newWindow.showAndWait();
            StudentDetailController detailController = loader.getController();
            int index = getIndex(detailController.getStudent().getStudentCode());
            list.set(index, detailController.getStudent());
            fileService.saveToJson(filedir);
        }
    }

    public void DeleteStudent(ActionEvent e) throws IOException {
        Student selected = (Student) table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn sinh viên cần xóa");
            alert.show();
        } else {
            list.remove(selected);
            fileService.saveToJson(filedir);
        }
    }

    public void SwitchView(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SwitchView.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);

        SwitchViewController switchView = loader.getController();
        switchView.setMenu(list);
    }
}
