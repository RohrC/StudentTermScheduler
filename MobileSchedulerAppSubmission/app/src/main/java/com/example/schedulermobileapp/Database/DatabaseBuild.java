package com.example.schedulermobileapp.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.schedulermobileapp.DAO.AssessmentDAO;
import com.example.schedulermobileapp.DAO.CourseDAO;
import com.example.schedulermobileapp.DAO.TermDAO;
import com.example.schedulermobileapp.Entities.Assessment;
import com.example.schedulermobileapp.Entities.Course;
import com.example.schedulermobileapp.Entities.Term;

@Database(entities = {Assessment.class, Course.class, Term.class}, version = 1, exportSchema = false)
public abstract class DatabaseBuild extends RoomDatabase {

    //DAO objects
    public abstract CourseDAO courseDAO();
    public abstract TermDAO termDAO();
    public abstract AssessmentDAO assessmentDAO();

    //Creates database instance
    private static volatile DatabaseBuild INSTANCE;

    //Method to get the database
    static DatabaseBuild getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuild.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuild.class, "SchedulerAppDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
