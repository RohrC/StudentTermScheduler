package com.example.schedulermobileapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.schedulermobileapp.Entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {

    @Insert
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM Assessment_Table ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();
}
