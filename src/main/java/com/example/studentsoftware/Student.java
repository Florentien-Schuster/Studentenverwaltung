package com.example.studentsoftware;

import java.util.*;

public class Student {
    private String name;
    private int age;
    private String city;
    private Map<String, String> coursesAndGrades; // Speichert die Kurse und Noten eines Studenten

    //-----------------------------------------------Konstruktor--------------------------------------------------------
    public Student(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.coursesAndGrades = new HashMap<>();
        if(coursesAndGrades.isEmpty()) {
            this.studentNoGradeAll();
        }
    }

    //----------------------------------------Getter--------------------------------------------------------------------
    /** Getter holt Name des Studenten Objekt
     * @return name, Name des Studenten
     * */
    public String getName() {
        return name;
    }
    /** Getter holt Alter des Studenten Objekt
     * @return age, Alter des Studenten
     * */
    public int getAge() {
        return age;
    }
    /** Getter holt Stadtnamen des Studenten Objekt
     * @return city, Stadt des Studenten
     * */
    public String getCity() {
        return city;
    }
    /** Getter holt die HashMap coursesAndGrades
     * @return coursesAndGrades, enthält die Kurse und Noten des Studenten Objekts
     * */
    public Map<String, String> getGrades() {
        return coursesAndGrades;
    }

    //------------------------------------Grade methoden----------------------------------------------------------------
    /** Fügt eine neue Note der HashMap coursesAndGrades hinzu */
    public void studentAddGrade(String course, String grade) {
        this.coursesAndGrades.put(course, grade);
    }
    /** Initialisiert die default Werte "-" der HashMap coursesAndGrades */
    public void studentNoGradeAll() {
        String[] courses = {"Math", "German", "English", "Biology", "Chemistry", "Computer Science"};
        for (String course : courses) {
            coursesAndGrades.put(course, "-");
        }
    }

    /** Getter holt die bestimmte Note eines Kurses
     * @param course, der Kurs für den die Note geholt werden soll
     * @return coursesAndGrades.get(course), gibt die Note des bestimmten Kurses aus der HashMap coursesAndGrades
     */
    public String getGradeForCourse(String course) {
        return coursesAndGrades.get(course);
    }

}