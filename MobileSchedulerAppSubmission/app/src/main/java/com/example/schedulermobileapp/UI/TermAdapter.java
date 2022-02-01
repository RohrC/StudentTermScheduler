package com.example.schedulermobileapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulermobileapp.Entities.Term;
import com.example.schedulermobileapp.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView1;
        private final TextView termItemView2;
        private final TextView termItemView3;

        private TermViewHolder(View itemView) {
            super(itemView);
            termItemView1 = itemView.findViewById(R.id.listTermTextView1);
            termItemView2 = itemView.findViewById(R.id.listTermTextView2);
            termItemView3 = itemView.findViewById(R.id.listTermTextView3);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Term current = mTerms.get(position);
                Intent intent = new Intent(context, EditTermAddCourseActivity.class);
                intent.putExtra("termID", current.getTermID());
                intent.putExtra("termTitle", current.getTermTitle());
                intent.putExtra("termStart", current.getTermStart());
                intent.putExtra("termEnd", current.getTermEnd());
                context.startActivity(intent);
            });
        }
    }

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_term, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mTerms != null) {
            Term currentTerm = mTerms.get(position);

            holder.termItemView1.setText(currentTerm.getTermTitle());
            holder.termItemView2.setText(currentTerm.getTermStart());
            holder.termItemView3.setText(currentTerm.getTermEnd());
        } else {
            holder.termItemView1.setText("No Term Title");
            holder.termItemView2.setText("No Start Date");
            holder.termItemView3.setText("No End Date");

        }
    }

    public void setTerms(List<Term> term) {
        mTerms = term;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }
}