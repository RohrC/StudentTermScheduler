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

public class EditTermAddCourseActivity extends AppCompatActivity {

    int termID;
    String title;
    String start;
    String end;

    EditText editTitle;
    EditText editStart;
    EditText editEnd;

    int numberOfAssociatedCourses;

    Repository repository;
    Term currentTerm;

    List<Term> termList;
    List<Course> courseList;
    List<Course> associatedCoursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term_add_course);

        //Sets back navigation arrow in actionbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Variables to hold existing selection values from columns
        termID = getIntent().getIntExtra("termID", -1);
        title = getIntent().getStringExtra("termTitle");
        start = getIntent().getStringExtra("termStart");
        end = getIntent().getStringExtra("termEnd");
        //Variables to hold the corresponding edittext views in the xml screen
        editTitle = findViewById(R.id.TermTitle);
        editStart = findViewById(R.id.StartDate);
        editEnd = findViewById(R.id.EndDate);
        //Put the column values from the selected term into the edittext views
        editTitle.setText(title);
        editStart.setText(start);
        editEnd.setText(end);

        //Set repository objects for terms and course lists
        repository = new Repository(getApplication());
        termList = repository.getAllTerms();
        courseList = repository.getAllCourses();

        for (Term t : termList) {
            if (t.getTermID() == termID) {
                currentTerm = t;
            }
        }

        //Array to hold the courses for the term
        associatedCoursesList = new ArrayList<>(); //CHECK THIS?
        for (Course c : courseList) {
            if (c.getTermID() == termID) {
                associatedCoursesList.add(c);
            }
        }

        numberOfAssociatedCourses = associatedCoursesList.size();

        //Set course recyclerview to display the added courses
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.CoursesRecyclerView);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(associatedCoursesList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                if (numberOfAssociatedCourses == 0) {
                    repository.deleteTerm(currentTerm);
                    //Display toast confirming term was deleted
                    Toast.makeText(EditTermAddCourseActivity.this, "Successfully deleted the selected term.", Toast.LENGTH_LONG).show();
                    //Go to term list after deleting a term
                    Intent intent2 = new Intent(EditTermAddCourseActivity.this, TermListActivity.class);
                    startActivity(intent2);
                } else {
                    Toast.makeText(EditTermAddCourseActivity.this, "Can't delete a term with associated courses.", Toast.LENGTH_LONG).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term, menu);
        return true;
    }

    public void saveTerm(View view) {
        editTitle = findViewById(R.id.TermTitle);
        editStart = findViewById(R.id.StartDate);
        editEnd = findViewById(R.id.EndDate);
        String termTitle = editTitle.getText().toString();
        String termStart = editStart.getText().toString();
        String termEnd = editEnd.getText().toString();


        if (termID < 1) {
            Term newTerm = new Term(++termID, termTitle, termStart, termEnd);
            repository.insertTerm(newTerm);
            //Display toast confirming term was added
            Toast.makeText(EditTermAddCourseActivity.this, "Successfully added the term: " + termTitle + " .", Toast.LENGTH_LONG).show();
            //Go to term list after inserting term
            Intent intent = new Intent(EditTermAddCourseActivity.this, TermListActivity.class);
            startActivity(intent);
        } else {
            Term oldTerm = new Term(termID, termTitle, termStart, termEnd);
            repository.updateTerm(oldTerm);
            //Display toast confirming course was updated
            Toast.makeText(EditTermAddCourseActivity.this, "Successfully updated the term: " + termTitle + " .", Toast.LENGTH_LONG).show();
            //Go to term list after updating term
            Intent intent2 = new Intent(EditTermAddCourseActivity.this, TermListActivity.class);
            startActivity(intent2);
        }
    }

    public void toEditTermAddCourseActivity(View view) {
        if (termID < 1) {
            Toast.makeText(EditTermAddCourseActivity.this, "Courses can only be added to existing terms.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(EditTermAddCourseActivity.this, EditCourseAddAssessmentActivity.class);
            intent.putExtra("termID", currentTerm.getTermID());
            startActivity(intent);
        }
    }
}