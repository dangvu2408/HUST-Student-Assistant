package com.example.app_01.Constructor;

public class CourseScore {
    private String CourseID, Alphabet, CourseName;
    private double QT, CK;
    private int TC;

    public CourseScore() {}
    public CourseScore(String CourseName, String CourseID, int TC, double QT, double CK, String Alphabet) {
        this.CourseName = CourseName;
        this.CourseID = CourseID;
        this.TC = TC;
        this.QT = QT;
        this.CK = CK;
        this.Alphabet = Alphabet;
    }

    public String getCourseName() { return CourseName; }
    public void setCourseName(String CourseName) { this.CourseName = CourseName; }
    public String getCourseID() { return CourseID; }
    public void setCourseID(String CourseID) { this.CourseID = CourseID; }
    public int getTC() { return TC; }
    public void setTC(int TC) { this.TC = TC; }
    public double getQT() { return QT; }
    public void setQT(double QT) { this.QT = QT; }
    public double getCK() { return CK; }
    public void setCK(double CK) { this.CK = CK; }
    public String getAlphabet() { return Alphabet; }
    public void setAlphabet(String Alphabet) { this.Alphabet = Alphabet; }
}
