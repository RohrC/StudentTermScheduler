package com.example.schedulermobileapp.DAO;

import android.widget.EditText;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.schedulermobileapp.Database.DatabaseBuild;
import com.example.schedulermobileapp.Entities.Course;
import com.example.schedulermobileapp.Entities.Term;
import com.example.schedulermobileapp.R;

import java.sql.PreparedStatement;
import java.util.List;

@Dao
public interface TermDAO {

    @Insert
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("SELECT * FROM Term_Table ORDER BY termID ASC")
    List<Term> getAllTerms();
    
}
