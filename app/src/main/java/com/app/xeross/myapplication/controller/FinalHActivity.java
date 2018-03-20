package com.app.xeross.myapplication.controller;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.model.AddItemList;
import com.app.xeross.myapplication.model.CustomAdapter;
import com.app.xeross.myapplication.view.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

//Class who manages the history
public class FinalHActivity extends ListActivity {

    final String TEXT_TEST = "TEXT_TEST";
    final String TEXT_INT = "TEXT_INT";
    final String TEXT_I = "TEXT_I";
    final String TEXT_COLORS = "TEXT_COLORS";
    final String CLEAR_BOOLEAN = "CLEAR_BOOLEAN";
    final String TEXT_SIZES = "TEXT_SIZES";
    final String TEXT_NAMES = "TEXT_NAMES";
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Item> mItems = new ArrayList<>();
    private RecyclerView list_item;
    private CustomAdapter adapter;
    //int it = intent.getIntExtra(TEXT_INT, 5);
    //int il = intent.getIntExtra(TEXT_I, 0);
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_final_h);

        final Intent intent = getIntent();
        Button madd = findViewById(R.id.button_add);
        String color = intent.getStringExtra(TEXT_COLORS);
        int size = intent.getIntExtra(TEXT_SIZES, 0);
        String name = intent.getStringExtra(TEXT_NAMES);
        String str = intent.getStringExtra(TEXT_TEST);
        boolean clars = intent.getBooleanExtra(CLEAR_BOOLEAN, false);

        loadData();
        if (color != " ") {
            mItems.add(new Item(name, color, size, str));
        }

        if (clars == true) {
            mItems.clear();
        }

        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intentA = new Intent(this, AddItemList.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intentA, 0);

        startAlertAtParticularTime();

        mLayoutManager = new LinearLayoutManager(this);
        list_item = findViewById(R.id.recycler);
        list_item.setLayoutManager(mLayoutManager);
        adapter = new CustomAdapter(mItems, this);
        list_item.setAdapter(adapter);

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mItems);
        editor.putString("TASK", json);
        editor.apply();

    }

    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("TASK", null);
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();
        mItems = gson.fromJson(json, type);

        if (mItems == null) {
            mItems = new ArrayList<>();
        }
    }

    public void startAlertAtParticularTime() {

        // Set the alarm to start at midnight.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

    }
}
