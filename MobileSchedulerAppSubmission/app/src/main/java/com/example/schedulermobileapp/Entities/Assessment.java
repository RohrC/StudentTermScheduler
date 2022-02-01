package com.example.schedulermobileapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Assessment_Table")
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    private String assessmentTitle;
    private String assessmentStart;
    private String assessmentEnd;
    private String assessmentType;
    private int courseID;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentStart, String assessmentEnd, String assessmentType, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.assessmentType = assessmentType;
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentStart='" + assessmentStart + '\'' +
                ", assessmentEnd='" + assessmentEnd + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                ", courseID=" + courseID +
                '}';
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public String getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
