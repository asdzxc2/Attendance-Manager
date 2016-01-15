package com.example.arpit.attendancemanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    HashMap<String, Integer> attendance = new HashMap<>();
    RecyclerView attendanceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */
        sharedPreferences = getSharedPreferences("Attendance", MODE_PRIVATE);
        prepareSharedPreferences(sharedPreferences);
        Logger("Preferences Ready");
        attendance = getAttendance(sharedPreferences);
        Logger("Attendance Ready");
        attendanceList = (RecyclerView) findViewById(R.id.main_view);
        attendanceList.setHasFixedSize(true);
        attendanceList.setLayoutManager(new LinearLayoutManager(this));
        Logger("Setting Adapter");
        attendanceList.setAdapter(new CustomRecyclerAdapter(attendance, sharedPreferences));
    }
    private void Logger(String message) {
        Log.v(this.getClass().getSimpleName(), message);
    }
    void prepareSharedPreferences(SharedPreferences sharedPreferences) {
        if(!sharedPreferences.contains("MC - 211"))
            sharedPreferences.edit().putInt("MC - 211", 0).apply();
        if(!sharedPreferences.contains("MC - 212"))
            sharedPreferences.edit().putInt("MC - 212", 0).apply();
        if(!sharedPreferences.contains("MC - 213"))
            sharedPreferences.edit().putInt("MC - 213", 0).apply();
        if(!sharedPreferences.contains("MC - 214"))
            sharedPreferences.edit().putInt("MC - 214", 0).apply();
        if(!sharedPreferences.contains("MC - 215"))
            sharedPreferences.edit().putInt("MC - 215", 0).apply();
        if(!sharedPreferences.contains("MC - 216"))
            sharedPreferences.edit().putInt("MC - 216", 0).apply();
        if(!sharedPreferences.contains("MC - 217"))
            sharedPreferences.edit().putInt("MC - 217", 0).apply();
        if(!sharedPreferences.contains("MC - 218"))
            sharedPreferences.edit().putInt("MC - 218", 0).apply();
        if(!sharedPreferences.contains("MC - 219"))
            sharedPreferences.edit().putInt("MC - 219", 0).apply();
    }
    HashMap<String, Integer> getAttendance(SharedPreferences sharedPreferences) {
        HashMap<String, Integer> temp = new HashMap<>();
        temp.put("MC - 211", sharedPreferences.getInt("MC - 211", 0));
        temp.put("MC - 212", sharedPreferences.getInt("MC - 212", 0));
        temp.put("MC - 213", sharedPreferences.getInt("MC - 213", 0));
        temp.put("MC - 214", sharedPreferences.getInt("MC - 214", 0));
        temp.put("MC - 215", sharedPreferences.getInt("MC - 215", 0));
        temp.put("MC - 216", sharedPreferences.getInt("MC - 216", 0));
        temp.put("MC - 217", sharedPreferences.getInt("MC - 217", 0));
        temp.put("MC - 218", sharedPreferences.getInt("MC - 218", 0));
        temp.put("MC - 219", sharedPreferences.getInt("MC - 219", 0));
        return temp;
    }
}
