package com.example.schedulermobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulermobileapp.Entities.Course;
import com.example.schedulermobileapp.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView1;
        private final TextView courseItemView2;
        private final TextView courseItemView3;

        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView1 = itemView.findViewById(R.id.listCourseTextView1);
            courseItemView2 = itemView.findViewById(R.id.listCourseTextView2);
            courseItemView3 = itemView.findViewById(R.id.listCourseTextView3);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Course current = mCourses.get(position);
                Intent intent = new Intent(context, EditCourseAddAssessmentActivity.class);
                intent.putExtra("courseID", current.getCourseID());
                intent.putExtra("courseTitle", current.getCourseTitle());
                intent.putExtra("courseStart", current.getCourseStart());
                intent.putExtra("courseEnd", current.getCourseEnd());
                intent.putExtra("courseStatus", current.getCourseEnd());
                intent.putExtra("courseInstructorName", current.getCourseInstructorName());
                intent.putExtra("courseInstructorPhone", current.getCourseInstructorPhone());
                intent.putExtra("courseInstructorEmail", current.getCourseInstructorEmail());
                intent.putExtra("courseNote", current.getCourseNote());
                intent.putExtra("termID", current.getTermID());
                context.startActivity(intent);
            });
        }
    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_course, parent, false);
        return new CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course currentCourse = mCourses.get(position);

            holder.courseItemView1.setText(currentCourse.getCourseTitle());
            holder.courseItemView2.setText(currentCourse.getCourseStart());
            holder.courseItemView3.setText(currentCourse.getCourseEnd());
        } else {
            holder.courseItemView1.setText("No Course Title");
            holder.courseItemView2.setText("No Start Date");
            holder.courseItemView3.setText("No End Date");

        }
    }

    public void setCourses(List<Course> course) {
        mCourses = course;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}
