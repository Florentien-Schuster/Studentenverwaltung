package com.example.studentsoftware;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DetailStudentController {
    private Student student;
    private OverviewController overviewController;

    @FXML
    private Label nameLabel, ageLabel, addressLabel , GradeMath, GradeGerman, GradeEnglish, GradeBiology, GradeChemistry, GradeComputerScience, errorLabel;
    @FXML
    private Button updateGradeButton;
    @FXML
    private TextField courseTextField, gradeTextField;
    @FXML
    private Button deleteButton;

    //-----------------------------------Student Methoden---------------------------------------------------------------
    /** Methode um Daten für einen bestimmten Studenten in dem Detailfenster anzuzeigen, updatet Labels und handelt Delete & GradeUpdate Button */
    public void setStudentData(Student student, OverviewController overviewController) {
        this.student = student;
        this.overviewController = overviewController;

        nameLabel.setText(student.getName());
        ageLabel.setText(String.valueOf(student.getAge()));
        addressLabel.setText(student.getCity());
        updateGradeLabels();

        deleteButton.setOnAction(event -> handleDeleteStudent());
        updateGradeButton.setOnAction(event -> handleUpdateGrade());
    }

    /** Methode die Noten eines bestimmten Studenten updatet, die Labels entsprechend aktualisiert und die Note in der
     *  HashMap studentsByYear eines Studenten updatet
     */
    @FXML
    private void handleUpdateGrade() {
        String course = courseTextField.getText();
        String grade = gradeTextField.getText();

        if (course.isEmpty() || grade.isEmpty()) {
            errorLabel.setText("Empty text fields are invalid!");
            return;
        }
        if (((course.equals("Math"))||(course.equals("German"))||(course.equals("English"))||(course.equals("Biology"))||(course.equals("Chemistry"))||(course.equals("Computer Science"))) && (grade.equals("1")|| grade.equals("2")|| grade.equals("3") || grade.equals("4")|| grade.equals("5")||grade.equals("6"))){
            student.studentAddGrade(course, grade);
            updateGradeLabels();
            overviewController.updateStudentInYearMap(student);

            courseTextField.clear();
            gradeTextField.clear();
        }else{
            errorLabel.setText("Invalid course name or grade!");
            return;
        }
    }

    //--------------------------------------Label&Button Methoden-------------------------------------------------------
    /** nach Eingabe neuer Noten wird mit dieser Methode der Text in den NotenLabels aktualisiert */
    private void updateGradeLabels() {
        GradeMath.setText(student.getGradeForCourse("Math"));
        GradeGerman.setText(student.getGradeForCourse("German"));
        GradeEnglish.setText(student.getGradeForCourse("English"));
        GradeBiology.setText(student.getGradeForCourse("Biology"));
        GradeChemistry.setText(student.getGradeForCourse("Chemistry"));
        GradeComputerScience.setText(student.getGradeForCourse("Computer Science"));
    }
    /** Methode die durch den Delete Button ausgelöst wird und an overviewController.deleteStudent(student) weiterleitet um den Studenten zu löschen */
    private void handleDeleteStudent() {
        overviewController.deleteStudent(student);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    /** Setzt Referenz auf OverviewController für interaktion */
    public void setOverviewController(OverviewController overviewController) {
        this.overviewController = overviewController;
        //System.out.println("OverviewController gesetzt: " + (this.overviewController != null));
    }
}