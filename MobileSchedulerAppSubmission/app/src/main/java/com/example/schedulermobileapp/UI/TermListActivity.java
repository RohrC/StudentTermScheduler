package com.example.schedulermobileapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.schedulermobileapp.Database.Repository;
import com.example.schedulermobileapp.Entities.Term;
import com.example.schedulermobileapp.R;

import java.util.List;
import java.util.Objects;

public class TermListActivity extends AppCompatActivity {

    private Repository repository;
    List<Term> allTerms;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        repository = new Repository(getApplication());
        allTerms = repository.getAllTerms();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }

    public void toEditTermAddCourse(View view) {
        Intent intent = new Intent(TermListActivity.this, EditTermAddCourseActivity.class);
        startActivity(intent);
    }
}