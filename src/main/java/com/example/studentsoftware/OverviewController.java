package com.example.studentsoftware;

import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.util.*;

import static com.example.studentsoftware.HelloApplication.overviewController;

public class OverviewController {
    public OverviewController(){

    }
    @FXML
    private Button logoutButton;
    @FXML
    private Button addStudentButton;

    @FXML
    private ComboBox<String> yearComboBox; // dropdown Menü
    @FXML
    private ListView<HBox> studentListView; // Studenten Liste
    private Map<String,List<Student>> studentsByYear = new HashMap<>();
    private String selectedYear; // Speichert das aktuelle Jahr
    private addStudentController addStudentController;
    private List<Student> students2025List = FXCollections.observableArrayList();
    private List<Student> students2024List = FXCollections.observableArrayList();
    private List<Student> students2023List = FXCollections.observableArrayList();

    //-----------------------Initialisierung Testdaten & Studenten methoden-------------------------------------------------

    /** Initialisiert Standard Testdaten in die Liste der alten Jahrgänge 2023 & 2024 */
    public void addOldStudents(){
        students2023List.add(new Student("Mare Ried", 20, "Frankfurt"));
        students2023List.add(new Student("Alexander Müller", 20, "Berlin"));

        students2024List.add(new Student("Nala Stehle", 16, "Köln"));
        students2024List.add(new Student("Max Mustermann", 18, "Nürnberg"));
    }

    /** Initialisiert Listendaten in HashMap studentsByYear & fügt Jahrgänge(Key) zu Combobox hinzu*/
    @FXML
    public void initialize() {
        addOldStudents();

        studentsByYear.put("2023", students2023List);

        studentsByYear.put("2024", students2024List);

        studentsByYear.put("2025", students2025List);

        // Daten zur ComboBox hinzufügen
        yearComboBox.setItems(FXCollections.observableArrayList(studentsByYear.keySet()));
    }

    /** Ist für Updaten der Listen zuständig, wenn ein neuer Student hinzugefügt wird und prüft ob Liste schon Studenten enthält */
    public void updateStudents2025() {
        if (addStudentController != null) {
            List<Student> students2025 = addStudentController.getListOf2025Students();
            for (Student s : students2025) {
                if (students2025List.contains(s)) {
                    continue;
                } else {
                    students2025List.add(s);
                }
            }
        }
    }
    /** Läd die Studenten (values) zum passenden Jahrgang (Key) in Overlay Liste */
    @FXML
    public void loadStudentsForYear() {
        String selectedYear = yearComboBox.getValue();
        if (selectedYear != null) {
            List<Student> students = studentsByYear.get(selectedYear);
            ObservableList<HBox> studentItems = FXCollections.observableArrayList();

            for (Student student : students) {
                HBox row = createStudentRow(student);
                studentItems.add(row);
            }

            studentListView.setItems(studentItems);
        }
    }
    /** Erstellt die Zeile eines Studenten und den Detailbutton in Liste im Dashboard*/
    private HBox createStudentRow(Student student) {
        Label nameLabel = new Label(student.getName());
        Button detailsButton = new Button("Details");

        // Festlegung der Aktion des Detailbuttons
        detailsButton.setOnAction(event -> showStudentDetails(student));

        HBox row = new HBox(10, nameLabel, detailsButton);
        return row;
    }

    //-------------------------------Öffnen Detailfenster---------------------------------------------------------------

    /** Öffnet das Detailfenster, indem man auf Detailbutton klickt
     * @param student übergebener Student dessen Detailfenster aufgerufen werden soll
     * */
    @FXML
    private void showStudentDetails(Student student) {
        this.selectedYear = findYearOfStudent(student);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/studentsoftware/detailStudent.fxml"));
            if (fxmlLoader.getLocation() == null) {
                System.out.println("FXML-Datei wurde nicht gefunden!");
            }
            Parent root = fxmlLoader.load();

//--------------------------------------Controller zuweisung------------------------------------------------------------
            DetailStudentController studentController = fxmlLoader.getController();
            studentController.setOverviewController(overviewController);
            DetailStudentController controller = fxmlLoader.getController();
//----------------------------------------------------------------------------------------------------------------------
            // Setze die Student-Daten im Controller
            controller.setStudentData(student, this);

            // Legt Werte wie Größe & Titel des Fensters fest
            Stage stage = new Stage();
            stage.setHeight(350);
            stage.setWidth(650);
            stage.setResizable(false);
            stage.setTitle("Student details");
            stage.setScene(new Scene(root, 400, 300));
            stage.show();

        } catch (IOException e) {
            System.out.println("Fehler beim Laden der FXML-Datei: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //--------------------------------Methoden für Student--------------------------------------------------------------

    /** Findet denn Jahrgang eines Studenten in der HashMap studentsByYear
     *
     * @param student Student für den der Jahrgang gesucht werden soll
     * @return entry.getKey() Jahrgang des passenden Studentenobjekts wird übergeben ODER null wenn Student nicht gefunden
     */
    private String findYearOfStudent(Student student) {
        for (Map.Entry<String, List<Student>> entry : studentsByYear.entrySet()) {
            if (entry.getValue().contains(student)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /** Löscht Student aus der studentsByYear HashMap */
    public void deleteStudent(Student student) {
        if (selectedYear != null) {
            List<Student> students = studentsByYear.get(selectedYear);
            if (students != null) {
                students.remove(student);
                loadStudentsForYear();
            }
        }
    }

    public void setStudents2025(){
        addStudentController.getListOf2025Students();
    }

    //------------------------------------------Window & Button Methoden--------------------------------------------------

    /** Öffnet das Fenster add Student, indem man einen Studenten zum aktuellen Jahrgang hinzufügen kann */
    @FXML
    public void openAddStudentWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/studentsoftware/addStudent.fxml"));
            if (fxmlLoader.getLocation() == null) {
                System.out.println("FXML-Datei wurde nicht gefunden!");
            }
            Parent root = fxmlLoader.load();
            //-------------------------------Controller zuweisung--------------------------------------------------------
            addStudentController studentController = fxmlLoader.getController();
            studentController.setOverviewController(overviewController);
            addStudentController = fxmlLoader.getController();
            //----------------------------------------------------------------------------------------------------------
            Stage stage = new Stage();
            stage.setHeight(350);
            stage.setWidth(650);
            stage.setResizable(false);
            stage.setTitle("Add Student");
            stage.setScene(new Scene(root, 400, 300));
            stage.show();
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der FXML-Datei: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Logout Button wodurch man automatisch auf das Loginfenster wieder verwiesen wird, schließt aktuelles Fenster */
    @FXML
    private void handleLogout() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        HelloApplication.openLoginWindow();
    }

    //-----------------------------------------Update Student nach Notenänderung----------------------------------------

    /** Updated die Noten eines Studenten, holt Liste der Studenten eines Jahrgangs, sucht dort nach Student und überschreibt Student mit alten Noten
     * @param updatedStudent Studentenobjekt mit den neuen Noten
     * */
    public void updateStudentInYearMap(Student updatedStudent) {
        String studentYear = findYearOfStudent(updatedStudent);

        if (studentYear != null) {
            List<Student> studentsInYear = studentsByYear.get(studentYear);

            // Durchsuche die Liste und ersetzt den Student mit den neuen Noten
            for (int i = 0; i < studentsInYear.size(); i++) {
                Student currentStudent = studentsInYear.get(i);

                // Wenn der Name des Studenten übereinstimmt ersetzen des alten Studenten
                if (currentStudent.getName().equals(updatedStudent.getName())) {
                    studentsInYear.set(i, updatedStudent);
                    break;
                }
            }
        }
    }
}

