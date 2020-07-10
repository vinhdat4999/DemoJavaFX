package Controller;

import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.json.*;
import java.io.*;

public class FileService {
    ObservableList<Student> list;
    public FileService(){
        list = FXCollections.observableArrayList();
    }
    public ObservableList<Student> readJson(String fileDir) {

        try {
            InputStream input = new FileInputStream(fileDir);
            JsonReader reader = Json.createReader(input);
            JsonArray jsonArray = reader.readArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                String studentCode = jsonArray.getJsonObject(i).getString("studentCode");
                String name = jsonArray.getJsonObject(i).getString("name");
                String unit = jsonArray.getJsonObject(i).getString("unit");
                String phoneNumber = jsonArray.getJsonObject(i).getString("phoneNumber");
                String address = jsonArray.getJsonObject(i).getString("address");
                Student student = new Student(studentCode, name, unit, phoneNumber, address);
                list.add(student);
            }
            reader.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void saveToJson(String fileDir) throws IOException {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Student i : list) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("studentCode", i.getStudentCode());
            objectBuilder.add("name", i.getName());
            objectBuilder.add("unit", i.getUnit());
            objectBuilder.add("phoneNumber", i.getPhoneNumber());
            objectBuilder.add("address", i.getAddress());
            jsonArrayBuilder.add(objectBuilder);
        }
        OutputStream output = new FileOutputStream(fileDir, false);
        try (JsonWriter writer = Json.createWriter(output)) {
            writer.writeArray(jsonArrayBuilder.build());
        }
        output.close();
    }
}
