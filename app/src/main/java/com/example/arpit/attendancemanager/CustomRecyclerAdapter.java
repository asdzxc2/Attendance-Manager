package com.example.arpit.attendancemanager;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder>{
    HashMap<String, Integer> attendance = new HashMap<>();
    String[] subjects;
    SharedPreferences sharedPreferences;
    HashMap<String, String> subjectNames = new HashMap<>();
    private void Logger(String message) {
        Log.v(this.getClass().getSimpleName(), message);
    }
    public CustomRecyclerAdapter(HashMap<String, Integer> attendance, SharedPreferences sharedPreferences) {
        this.attendance = attendance;
        TreeSet<String> temp = new TreeSet<>(attendance.keySet());
        Logger(temp.toString());
        subjects = Arrays.copyOf(temp.toArray(), temp.size(), String[].class);
        this.sharedPreferences = sharedPreferences;
        subjectNames.put("MC - 211", "Real Analysis");
        subjectNames.put("MC - 212", "Linear Algebra");
        subjectNames.put("MC - 213", "Digital Logic Design");
        subjectNames.put("MC - 214", "Object Oriented Programming");
        subjectNames.put("MC - 215", "Scientific Computing");
        subjectNames.put("MC - 216", "Computer Organization and Architecture");
        subjectNames.put("MC - 217", "Scientific Computing Lab");
        subjectNames.put("MC - 218", "Digital Logic Design Lab");
        subjectNames.put("MC - 219", "OOPS Lab");
        //Logger("Adapter Initialised");
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        //Logger("ViewHolder Initialised");
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.subject.setText(subjects[position] + " : " + subjectNames.get(subjects[position]));
        holder.attendance.setText(Integer.toString(attendance.get(subjects[position])));
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int attendance = Integer.parseInt(holder.attendance.getText().toString());
                attendance += 1;
                holder.attendance.setText(Integer.toString(attendance));
                sharedPreferences.edit().remove(subjects[position]).apply();
                sharedPreferences.edit().putInt(subjects[position], attendance).apply();
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int attendance = Integer.parseInt(holder.attendance.getText().toString());
                attendance -= 1;
                if (attendance >= 0) {
                    holder.attendance.setText(Integer.toString(attendance));
                    sharedPreferences.edit().remove(subjects[position]).apply();
                    sharedPreferences.edit().putInt(subjects[position], attendance).apply();
                } else
                    Snackbar.make(v, "Attendance can't be less than zero!", Snackbar.LENGTH_LONG).show();
            }
        });
        //Logger("View Holder bound");
    }

    @Override
    public int getItemCount() {
        return subjects.length;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView attendance, subject;
        Button plus, minus;
        public CustomViewHolder(View itemView) {
            super(itemView);
            this.subject = (TextView) itemView.findViewById(R.id.subject);
            this.attendance = (TextView) itemView.findViewById(R.id.attendance);
            this.plus = (Button) itemView.findViewById(R.id.plus);
            this.minus = (Button) itemView.findViewById(R.id.minus);
            //    Logger("ViewHolder Constructor");
        }
    }
}
