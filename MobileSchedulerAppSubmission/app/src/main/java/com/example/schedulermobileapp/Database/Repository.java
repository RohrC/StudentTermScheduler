package com.example.schedulermobileapp.Database;

import android.app.Application;

import com.example.schedulermobileapp.DAO.AssessmentDAO;
import com.example.schedulermobileapp.DAO.CourseDAO;
import com.example.schedulermobileapp.DAO.TermDAO;
import com.example.schedulermobileapp.Entities.Assessment;
import com.example.schedulermobileapp.Entities.Course;
import com.example.schedulermobileapp.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    //DAO objects
    private AssessmentDAO assessmentDAO;
    private CourseDAO courseDAO;
    private TermDAO termDAO;


    //Entity Lists
    List<Assessment> allAssessments;
    List<Course> allCourses;
    List<Term> allTerms;

    //Number of threads
    private static int NUMBER_OF_THREADS = 4;

    //Database executor to execute number of threads
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        DatabaseBuild db = DatabaseBuild.getDatabase(application);
        assessmentDAO = db.assessmentDAO();
        courseDAO = db.courseDAO();
        termDAO = db.termDAO();
    }

    //Constructor object
    public Repository() {

    }

//------------------------------Assessment------------------------------------------------------------

    //Get all assessments as a list
    public List<Assessment> getAllAssessments() {
        databaseExecutor.execute(() -> {
            allAssessments = assessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssessments;
    }

    //Insert an assessment
    public void insertAssessment(Assessment assessment) {
        databaseExecutor.execute(() -> {
            assessmentDAO.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Update an assessment
    public void updateAssessment(Assessment assessment) {
        databaseExecutor.execute(() -> {
            assessmentDAO.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Delete an assessment
    public void deleteAssessment(Assessment assessment) {
        databaseExecutor.execute(() -> {
            assessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//------------------------------Course------------------------------------------------------------

    //Get all courses as a list
    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            allCourses = courseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    //Insert a course
    public void insertCourse(Course course) {
        databaseExecutor.execute(() -> {
            courseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Update a course
    public void updateCourse(Course course) {
        databaseExecutor.execute(() -> {
            courseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Delete a course
    public void deleteCourse(Course course) {
        databaseExecutor.execute(() -> {
            courseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//------------------------------Term----------------------------------------------------------------

    //Get all schedules as a list
    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            allTerms = termDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allTerms;
    }

    //Insert a schedule
    public void insertTerm(Term term) {
        databaseExecutor.execute(() -> {
            termDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Update a schedule
    public void updateTerm(Term term) {
        databaseExecutor.execute(() -> {
            termDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Delete a schedule
    public void deleteTerm(Term term) {
        databaseExecutor.execute(() -> {
            termDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
