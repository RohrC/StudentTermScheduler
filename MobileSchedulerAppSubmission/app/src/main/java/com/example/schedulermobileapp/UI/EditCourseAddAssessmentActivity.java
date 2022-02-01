package com.example.schedulermobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedulermobileapp.Database.Repository;
import com.example.schedulermobileapp.Entities.Assessment;
import com.example.schedulermobileapp.Entities.Course;
import com.example.schedulermobileapp.Entities.Term;
import com.example.schedulermobileapp.MainActivity;
import com.example.schedulermobileapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EditCourseAddAssessmentActivity extends AppCompatActivity {

    int courseID;
    String title;
    String start;
    String end;
    String status;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String note;
    int termID;

    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editStatus;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editEmail;
    EditText editNote;

    int assessmentID;
    int associatedAssessments;

    Repository repository;
    Assessment currentAssessment;
    Course currentCourse;
    Term currentTerm;

    List<Term> termList;
    List<Course> courseList;
    List<Assessment> assessmentList;
    List<Assessment> associatedAssessmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_add_assessment);

        //Sets back navigation arrow in actionbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Variables to hold existing selection values from course columns
        courseID = getIntent().getIntExtra("courseID", -1);
        title = getIntent().getStringExtra("courseTitle");
        start = getIntent().getStringExtra("courseStart");
        end = getIntent().getStringExtra("courseEnd");
        status = getIntent().getStringExtra("courseStatus");
        instructorName = getIntent().getStringExtra("courseInstructorName");
        instructorPhone = getIntent().getStringExtra("courseInstructorPhone");
        instructorEmail = getIntent().getStringExtra("courseInstructorEmail");
        note = getIntent().getStringExtra("courseNote");
        termID = getIntent().getIntExtra("termID", -1);
        //Variables to represent the corresponding EditText views by their id in the xml screen
        editTitle = findViewById(R.id.CourseTitleEditText);
        editStart = findViewById(R.id.CourseStartDateEditText);
        editEnd = findViewById(R.id.CourseEndDateEditText);
        editStatus = findViewById(R.id.CourseStatusEditText);
        editInstructorName = findViewById(R.id.InstructorNameEditText);
        editInstructorPhone = findViewById(R.id.InstructorPhoneNumberEditText);
        editEmail = findViewById(R.id.InstructorEmailEditText);
        editNote = findViewById(R.id.CourseNoteEditText);
        //Populate the corresponding EditTexts with the existing course values
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);
        editStatus.setText(status);
        editInstructorName.setText(instructorName);
        editInstructorPhone.setText(instructorPhone);
        editEmail.setText(instructorEmail);
        editNote.setText(note);

        //Note to send
        //String note = ;

        //Set repository objects for terms, course, and assessment lists
        repository = new Repository(getApplication());
        termList = repository.getAllTerms();
        courseList = repository.getAllCourses();
        assessmentList = repository.getAllAssessments();
        associatedAssessmentsList = new ArrayList<>();

        for (Term t : termList) {
            if (t.getTermID() == termID) {
                currentTerm = t;
            }
        }

        for (Course c : courseList) {
            if (c.getCourseID() == courseID) {
                currentCourse = c;
            }
        }

        for (Assessment a : assessmentList) {
            if (a.getAssessmentID() == assessmentID) {
                currentAssessment = a;
            }
        }

        for (Assessment a : assessmentList) {
            if (a.getCourseID() == courseID) {
                associatedAssessmentsList.add(a);
            }
        }

        associatedAssessments = associatedAssessmentsList.size();

        //Set assessment recyclerview to the associated assessments list
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.EditCoursesAssessmentsRecyclerView);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(associatedAssessmentsList);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Course Note: ");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.delete:
                if (associatedAssessments == 0) {
                    repository.deleteCourse(currentCourse);
                    //Display toast confirming course was deleted
                    Toast.makeText(EditCourseAddAssessmentActivity.this, "Successfully deleted the selected course.", Toast.LENGTH_LONG).show();
                    //Go to term list after deleting a term
                    Intent intent2 = new Intent(EditCourseAddAssessmentActivity.this, TermListActivity.class);
                    startActivity(intent2);
                } else {
                    Toast.makeText(EditCourseAddAssessmentActivity.this, "Can't delete a course with associated assessments.", Toast.LENGTH_LONG).show();
                }
            case R.id.notify:
                //Wait 3 seconds after the notification button is clicked to display the course title, start, and end
                //The fields will be shown as null when the toast is displayed if no course values exist.
                Long timeAtClick = System.currentTimeMillis();
                Long threeSeconds = Long.valueOf(1000 * 3);
                Long trigger = timeAtClick + threeSeconds;
                Intent intent = new Intent(EditCourseAddAssessmentActivity.this, MyReceiver.class);
                intent.putExtra("key", "Course: " + title + " begins " + start + " and ends on " + end + ".");
                PendingIntent sender = PendingIntent.getBroadcast(EditCourseAddAssessmentActivity.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    public void saveCourse(View view) {

        String courseTitle = editTitle.getText().toString();
        String courseStart = editStart.getText().toString();
        String courseEnd = editEnd.getText().toString();
        String courseStatus = editStatus.getText().toString();
        String instructorName = editInstructorName.getText().toString();
        String instructorPhone = editInstructorPhone.getText().toString();
        String instructorEmail = editEmail.getText().toString();
        String courseNote = editNote.getText().toString();

        //Check to see if a term exists and is selected to add a course to. Display a message if there is no existing term.
        if (termID == -1) {
            Toast.makeText(EditCourseAddAssessmentActivity.this, "Course can only be added to existing terms.", Toast.LENGTH_LONG).show();////////////
        } else {
            //insert a new course; default is -1
            if (courseID == -1) {
                Course newCourse = new Course(++courseID, courseTitle, courseStart, courseEnd, courseStatus, instructorName, instructorPhone, instructorEmail, courseNote, termID);
                repository.insertCourse(newCourse);
                //Display toast confirming course was added
                Toast.makeText(EditCourseAddAssessmentActivity.this, "Successfully added " + courseTitle + " to the term.", Toast.LENGTH_LONG).show();
                //Go to term list after inserting new course
                Intent intent = new Intent(EditCourseAddAssessmentActivity.this, TermListActivity.class);
                startActivity(intent);
            } else {
                //update an existing course
                Course oldCourse = new Course(courseID, courseTitle, courseStart, courseEnd, courseStatus, instructorName, instructorPhone, instructorEmail, courseNote, termID);
                repository.updateCourse(oldCourse);
                //Display toast confirming course was updated
                Toast.makeText(EditCourseAddAssessmentActivity.this, "Successfully updated the course: " + courseTitle + " .", Toast.LENGTH_LONG).show();
                //Go to term list after updating course
                Intent intent2 = new Intent(EditCourseAddAssessmentActivity.this, TermListActivity.class);
                startActivity(intent2);
            }
        }
    }


    public void toEditAddAssessmentActivity(View view) {
        //Check if the course exists to add an assessment. If not, display a message indicating so. Else, proceed to the activity to create an assessment.
        if (courseID < 1) {
            Toast.makeText(EditCourseAddAssessmentActivity.this, "Assessments can only be added to existing courses.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(EditCourseAddAssessmentActivity.this, EditAddAssessmentActivity.class);
            intent.putExtra("courseID", currentCourse.getCourseID());
            startActivity(intent);
        }
    }
}