package com.example.schedulermobileapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = "Term_Table")
public class Term {

    @PrimaryKey (autoGenerate = true)
    private int termID;

    private String termTitle;
    private String termStart;
    private String termEnd;

    public Term(int termID, String termTitle, String termStart, String termEnd) {
        this.termID = termID;
        this.termTitle = termTitle;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termID=" + termID +
                ", termTitle='" + termTitle + '\'' +
                ", termStart='" + termStart + '\'' +
                ", termEnd='" + termEnd + '\'' +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }
}
