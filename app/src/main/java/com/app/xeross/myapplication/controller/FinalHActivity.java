package com.app.xeross.myapplication.controller;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    final String TEXT_COLORS = "TEXT_COLORS";
    final String CLEAR_BOOLEAN = "CLEAR_BOOLEAN";
    final String TEXT_SIZES = "TEXT_SIZES";
    final String TEXT_NAMES = "TEXT_NAMES";
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Item> mItems = new ArrayList<>();
    private RecyclerView list_item;
    private CustomAdapter adapter;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_final_h);

        final Intent intent = getIntent();
        Button madd = findViewById(R.id.button_add);
        Button mdel = findViewById(R.id.button_remove);
        String color = intent.getStringExtra(TEXT_COLORS);
        int size = intent.getIntExtra(TEXT_SIZES, 0);
        String name = intent.getStringExtra(TEXT_NAMES);
        String str = intent.getStringExtra(TEXT_TEST);
        boolean clars = intent.getBooleanExtra(CLEAR_BOOLEAN, false);

        loadData();
        if (color != " " && name != "clear") {
            mItems.add(new Item(name, color, size, str));
        }

        if (name == "clear") {
            mItems.clear();
            saveData();
            adapter.notifyDataSetChanged();
        }

        mdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteView();
            }
        });

        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intentA = new Intent(this, AddItemList.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intentA, 0);

        RegisterAlarmBroadcast();

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

    public void DeleteView() {
        //Item hey = mItems.get(mItems.size() - 1);
        //mItems.remove(hey);
        mItems.clear();
        saveData();
        adapter.notifyDataSetChanged();
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

    private void RegisterAlarmBroadcast() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                saveData();
                Toast.makeText(context, "L'humeur à été mémorisée", Toast.LENGTH_LONG).show();
            }
        };

        registerReceiver(mReceiver, new IntentFilter("sample"));
        alarmIntent = PendingIntent.getBroadcast(this, 0, new Intent("sample"), 0);
        alarmMgr = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    }
}
