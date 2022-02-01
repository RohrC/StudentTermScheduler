package com.example.schedulermobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class EditAddAssessmentActivity extends AppCompatActivity {

    int assessmentID;
    String type;
    String title;
    String start;
    String end;
    int courseID;

    EditText editTitle;
    EditText editType;
    EditText editStart;
    EditText editEnd;

    Repository repository;

    Course currentCourse;
    Assessment currentAssessment;

    List<Course> courseList;
    List<Assessment> assessmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add_assessment);

        //Sets back navigation arrow in actionbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Variables to column values from the Assessment_Table
        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        type = getIntent().getStringExtra("assessmentType");
        title = getIntent().getStringExtra("assessmentTitle");
        start = getIntent().getStringExtra("assessmentStart");
        end = getIntent().getStringExtra("assessmentEnd");
        courseID = getIntent().getIntExtra("courseID", -1);
        //Variables to represent the corresponding EditText views by their id in the xml screen
        editType = findViewById(R.id.AssessmentTypeEditText);
        editTitle = findViewById(R.id.AssessmentTitleEditText);
        editStart = findViewById(R.id.AssessmentStartDateEditText);
        editEnd = findViewById(R.id.AssessmentEndDateEditText);
        //Populate the corresponding EditTexts with the existing assessment values
        editType.setText(type);
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);


        //Set repository objects for course and assessment lists
        repository = new Repository(getApplication());
        courseList = repository.getAllCourses();
        assessmentList = repository.getAllAssessments();

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                repository.deleteAssessment(currentAssessment);
                //Display toast confirming assessment was deleted
                Toast.makeText(EditAddAssessmentActivity.this, "Successfully deleted the selected assessment.", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(EditAddAssessmentActivity.this, TermListActivity.class);
                startActivity(intent2);
            case R.id.notify:
                //Wait 3 seconds after the notification button is clicked to display the assessment title, start, and end
                //The fields will be shown as null when the toast is displayed if no assessment values exist.
                Long timeAtClick = System.currentTimeMillis();
                Long threeSeconds = Long.valueOf(1000 * 3);
                Long trigger = timeAtClick + threeSeconds;
                Intent intent = new Intent(EditAddAssessmentActivity.this, MyReceiver.class);
                intent.putExtra("key", "Assessment: " + title + " begins " + start + " and ends on " + end + ".");
                PendingIntent sender = PendingIntent.getBroadcast(EditAddAssessmentActivity.this, ++MainActivity.numAlert, intent, 0);
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

    public void saveAssessment(View view) {

        String assessmentType = editType.getText().toString();
        String assessmentTitle = editTitle.getText().toString();
        String assessmentStart = editStart.getText().toString();
        String assessmentEnd = editEnd.getText().toString();

        //Check if course exists by checking for a valid courseID and display a message if there is no course.
        if (courseID == -1) {
            Toast.makeText(EditAddAssessmentActivity.this, "Assessments can only be added to existing courses.", Toast.LENGTH_LONG).show();
        } else {
            //check if the assessment is a new assessment to insert or old assessment to be updated using assessmentID --using listsize for id was affecting id unique constraints
            if (assessmentID < 1) {
                Assessment newAssessment = new Assessment(++assessmentID, assessmentTitle, assessmentStart, assessmentEnd, assessmentType, courseID);
                repository.insertAssessment(newAssessment);
                //Display toast confirming assessment was added
                Toast.makeText(EditAddAssessmentActivity.this, "Successfully added " + assessmentTitle + " to the course.", Toast.LENGTH_LONG).show();
                //Go to term list after inserting new assessment
                Intent intent = new Intent(EditAddAssessmentActivity.this, TermListActivity.class);
                startActivity(intent);
            } else {
                //update the selected assessment
                Assessment oldAssessment = new Assessment(assessmentID, assessmentTitle, assessmentStart, assessmentEnd, assessmentType, courseID);
                repository.updateAssessment(oldAssessment);
                //Display toast confirming assessment was updated
                Toast.makeText(EditAddAssessmentActivity.this, "Successfully updated the assessment: " + assessmentTitle + " .", Toast.LENGTH_LONG).show();
                //Go to term list after updating assessment
                Intent intent2 = new Intent(EditAddAssessmentActivity.this, TermListActivity.class);
                startActivity(intent2);
            }
        }
    }
}