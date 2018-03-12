package com.app.xeross.myapplication.controller;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.model.CustomAdapter;
import com.app.xeross.myapplication.view.Item;

import java.util.ArrayList;
import java.util.List;

//Class who manages the history
public class FinalHActivity extends ListActivity {

    final String TEXT_TEST = "TEXT_TEST";
    final String TEXT_INT = "TEXT_INT";
    LinearLayoutManager polo;
    RecyclerView.LayoutManager mLayoutManager;
    List<Item> mItems = new ArrayList<>();
    private RecyclerView list_item;
    private int it = 5;
    private String pos = "Aujourd'hui";
    private Button mButtonChange;
    private Button mButtonAdd;
    private Button mButtonDelete;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_final_h);

        //ID
        mButtonAdd = findViewById(R.id.button_add);
        mButtonChange = findViewById(R.id.button_change);
        mButtonDelete = findViewById(R.id.button_remove);

        //new Intent
        final Intent intent = getIntent();

        //Integer who retrieves a value of a constant "TEXT_INT" (putting for default 5)
        it = intent.getIntExtra(TEXT_INT, 5);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddView();
                adapter.notifyDataSetChanged();
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteView();
                adapter.notifyDataSetChanged();
            }
        });

        //TEST
        mButtonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start();
                adapter.notifyDataSetChanged();
            }
        });

        list_item = findViewById(R.id.recyclerview);
        polo = new LinearLayoutManager(this);
        mLayoutManager = new LinearLayoutManager(this);
        list_item.setHasFixedSize(true);
        list_item.setLayoutManager(polo);

        getData();
        list_item.getRecycledViewPool().clear();
    }

    public void ChangeView() {
        //new Intent
        final Intent intent = getIntent();
        //String who retrieves  the text of a constant "TEXT_TEST"
        String str = intent.getStringExtra(TEXT_TEST);
        Item hey = mItems.get(mItems.size() - 1);
        mItems.remove(hey);
        switch (it) {
            case 0:
                mItems.add(new Item(pos, "#fff9ec4f", 1, str));
                break;
            case 1:
                mItems.add(new Item(pos, "#ffb8e986", 200, str));
                break;
            case 2:
                mItems.add(new Item(pos, "#a5368ad9", 400, str));
                break;
            case 3:
                mItems.add(new Item(pos, "#3b3b3b", 600, str));
                break;
            case 4:
                mItems.add(new Item(pos, "#ffde3c50", 800, str));
                break;
        }
    }

    public void DeleteView() {
        Item hey = mItems.get(mItems.size() - 1);
        mItems.remove(hey);
    }

    public void AddView() {
        //new Intent
        final Intent intent = getIntent();
        //String who retrieves  the text of a constant "TEXT_TEST"
        String str = intent.getStringExtra(TEXT_TEST);
        switch (it) {
            case 0:
                mItems.add(new Item(pos, "#fff9ec4f", 1, str));
                break;
            case 1:
                mItems.add(new Item(pos, "#ffb8e986", 200, str));
                break;
            case 2:
                mItems.add(new Item(pos, "#a5368ad9", 400, str));
                break;
            case 3:
                mItems.add(new Item(pos, "#3b3b3b", 600, str));
                break;
            case 4:
                mItems.add(new Item(pos, "#ffde3c50", 800, str));
                break;
        }

        switch (pos) {
            case "Aujourd'hui":
                pos = "Hier";
                break;
            case "Hier":
                pos = "Avant-hier";
                break;
            case "Avant-hier":
                pos = "Il y a 3j";
                break;
            case "Il y a 3j":
                pos = "Il y a 4j";
                break;
            case "Il y a 4j":
                pos = "Il y a 5j";
                break;
            case "Il y a 5j":
                pos = "Il y a 6j";
                break;
            case "Il y a 6j":
                pos = "Il y a une semaine";
                break;
            case "Il y a une semaine":
                pos = "clear";
                break;
            case "clear":
                mItems.clear();
                pos = "Aujourd'hui";
                break;
        }
    }

    private void getData() {
        adapter = new CustomAdapter(mItems, this);
        list_item.setAdapter(adapter);
    }

    /*public void start() {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (timeInSec * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set to after " + i + " seconds",Toast.LENGTH_LONG).show();
        Log.i("A BIENTOT", "intent ok");
    }*/

}
