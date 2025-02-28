package com.example.studentsoftware;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class addStudentController {

    @FXML
    private Button buttonAddStudent;
    @FXML
    private Label errorLabelName, errorLabelAge, errorLabelCity;
    @FXML
    private TextField textFieldStudentName, textFieldStudentAge, textFieldStudentCity, textFieldSchoolYear;
    private ArrayList<Student> listOf2025Students = new ArrayList<>(); // Liste der 2025 Studenten
    private OverviewController overviewController;

    /** Die Methode bekommt über die Textfelder die Daten des Studenten übergeben und fügt diese in die listOf2025Students ein, wenn die Eingaben korrekt sind,
     * wird entsprechend eine Fehlernachricht auf dem passenden ErrorLabel ausgegeben */
    public void addStudentToList(){
        String studentName = textFieldStudentName.getText();
        String studentAgeString = textFieldStudentAge.getText();
        String studentCity = textFieldStudentCity.getText();
        String year = textFieldSchoolYear.getText();
        errorLabelName.setText(" ");
        errorLabelAge.setText(" ");
        errorLabelCity.setText(" ");

        if(studentAgeString.isEmpty()){
            errorLabelAge.setText("Age is invalid, no empty textfield!");
            textFieldStudentAge.clear();
        }else{
            int studentAge = Integer.parseInt(textFieldStudentAge.getText());
            if(isValidName(studentName) && isValidAge(studentAge) && isValidCity(studentCity)){
                Student newStudent = new Student(studentName,studentAge,studentCity);
                listOf2025Students.add(newStudent);
                overviewController.updateStudents2025();
                textFieldStudentName.clear();
                textFieldStudentAge.clear();
                textFieldStudentCity.clear();
            }else{
                if(isValidName(studentName) == false) {
                    errorLabelName.setText("Name is invalid, only letters allowed!");
                    textFieldStudentName.clear();
                }
                if(isValidAge(studentAge) == false) {
                    errorLabelAge.setText("Age is invalid, only numbers allowed!");
                    textFieldStudentAge.clear();
                }
                if(isValidCity(studentCity) == false) {
                    errorLabelCity.setText("City name is invalid, only letters allowed!");
                    textFieldStudentCity.clear();
                }
            }
        }
    }
    /** Getter übergibt die Werte der listOf2025Students */
    public ArrayList<Student> getListOf2025Students() {
        return listOf2025Students;
    }
    /** Setzt Referenz auf OverviewController für interaktion */
    public void setOverviewController(OverviewController overviewController) {
        this.overviewController = overviewController;
        // System.out.println("OverviewController gesetzt: " + (this.overviewController != null));
    }
    //--------------------------------------------Error Label Methoden--------------------------------------------------
    /** Prüft, ob der eingegebene Studentenname unerlaubte Zeichen enthält
     * @return true, wenn die Eingabe erlaubt ist
     *         false, wenn die Eingabe unerlaubt ist
     * */
    public boolean isValidName(String studentName){
        if(studentName.matches("^[A-Za-zäöüÄÖÜß\\s-]+$") && !studentName.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    /** Prüft, ob das eingegebene Alter unerlaubte Zeichen enthält, wenn Nein wird true ausgegeben, wenn Ja wird false ausgegeben
     * @return true, wenn die Eingabe erlaubt ist
     *         false, wenn die Eingabe unerlaubt ist
     * */
    public boolean isValidAge(int studentAge){
        if((studentAge < 100 && studentAge > 5 )){
            return true;
        }else{
            return false;
        }
    }
    /** Prüft, ob der eingegebene Stadtname unerlaubte Zeichen enthält, wenn Nein wird true ausgegeben, wenn Ja wird false ausgegeben
     *@return true, wenn die Eingabe erlaubt ist
     *        false, wenn die Eingabe unerlaubt ist
     * */
    public boolean isValidCity(String studentCity){
        if (studentCity.matches("^[A-Za-zäöüÄÖÜß\\s-]+$") && !studentCity.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}