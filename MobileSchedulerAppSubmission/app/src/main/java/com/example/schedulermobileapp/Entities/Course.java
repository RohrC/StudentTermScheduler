package com.example.schedulermobileapp.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Course_Table")
public class Course {

    @PrimaryKey (autoGenerate = true)
    private int courseID;

    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String courseInstructorName;
    private String courseInstructorPhone;
    private String courseInstructorEmail;
    private String courseNote;
    private int termID;

    public Course(int courseID, String courseTitle, String courseStart, String courseEnd, String courseStatus, String courseInstructorName, String courseInstructorPhone, String courseInstructorEmail, String courseNote, int termID) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorPhone = courseInstructorPhone;
        this.courseInstructorEmail = courseInstructorEmail;
        this.courseNote = courseNote;
        this.termID = termID;
    }

    public Course(String courseTitle, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, String courseNote, int termID) {
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseStart='" + courseStart + '\'' +
                ", courseEnd='" + courseEnd + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseInstructorName='" + courseInstructorName + '\'' +
                ", courseInstructorPhone='" + courseInstructorPhone + '\'' +
                ", courseInstructorEmail='" + courseInstructorEmail + '\'' +
                ", courseNote='" + courseNote + '\'' +
                ", termID=" + termID +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseInstructorName() {
        return courseInstructorName;
    }

    public void setCourseInstructorName(String courseInstructorName) {
        this.courseInstructorName = courseInstructorName;
    }

    public String getCourseInstructorPhone() {
        return courseInstructorPhone;
    }

    public void setCourseInstructorPhone(String courseInstructorPhone) {
        this.courseInstructorPhone = courseInstructorPhone;
    }

    public String getCourseInstructorEmail() {
        return courseInstructorEmail;
    }

    public void setCourseInstructorEmail(String courseInstructorEmail) {
        this.courseInstructorEmail = courseInstructorEmail;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
}
