package com.example.schedulermobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulermobileapp.Entities.Assessment;
import com.example.schedulermobileapp.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView1;
        private final TextView assessmentItemView2;
        private final TextView assessmentItemView3;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView1 = itemView.findViewById(R.id.listAssessmentTextView1);
            assessmentItemView2 = itemView.findViewById(R.id.listAssessmentTextView2);
            assessmentItemView3 = itemView.findViewById(R.id.listAssessmentTextView3);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Assessment current = mAssessments.get(position);
                Intent intent = new Intent(context, EditAddAssessmentActivity.class);
                intent.putExtra("assessmentID", current.getAssessmentID());
                intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                intent.putExtra("assessmentStart", current.getAssessmentStart());
                intent.putExtra("assessmentEnd", current.getAssessmentEnd());
                intent.putExtra("assessmentType", current.getAssessmentType());
                intent.putExtra("courseID", current.getCourseID());
                context.startActivity(intent);
            });
        }
    }

    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_assessment, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment currentAssessment = mAssessments.get(position);

            holder.assessmentItemView1.setText(currentAssessment.getAssessmentTitle());
            holder.assessmentItemView2.setText(currentAssessment.getAssessmentStart());
            holder.assessmentItemView3.setText(currentAssessment.getAssessmentEnd());
        } else {
            holder.assessmentItemView1.setText("No Assessment Title");
            holder.assessmentItemView2.setText("No Start Date");
            holder.assessmentItemView3.setText("No End Date");

        }
    }

    public void setAssessments(List<Assessment> assessment) {
        mAssessments = assessment;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }
}
