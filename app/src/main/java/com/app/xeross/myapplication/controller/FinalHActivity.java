package com.app.xeross.myapplication.controller;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.model.AddItemList;
import com.app.xeross.myapplication.model.CustomAdapter;
import com.app.xeross.myapplication.view.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//Class who manages the history
public class FinalHActivity extends ListActivity {

    final String TEXT_TEST = "TEXT_TEST";
    final String TEXT_INT = "TEXT_INT";
    private final String LIST_STATE_KEY = "recycler_state";
    LinearLayoutManager polo;
    RecyclerView.LayoutManager mLayoutManager;
    List<Item> mItems = new ArrayList<>();
    private RecyclerView list_item;
    private int it = 5;
    private String pos = "Aujourd'hui";
    private Button mButtonChange;
    private Button mButtonAdd;
    private CustomAdapter adapter;
    private Parcelable mListState;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_final_h);

        mLayoutManager = new LinearLayoutManager(this);

        //ID
        list_item = findViewById(R.id.recycler);
        mButtonAdd = findViewById(R.id.button_add);
        mButtonChange = findViewById(R.id.button_change);

        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intentA = new Intent(this, AddItemList.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intentA, 0);

        startAlertAtParticularTime();

        adapter = new CustomAdapter(mItems, this);
        list_item.setAdapter(adapter);
        //list_item.setAdapter(adapter);

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

        //TEST
        mButtonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start();
                adapter.notifyDataSetChanged();
            }
        });
        polo = new LinearLayoutManager(this);
        list_item.setHasFixedSize(true);
        list_item.setLayoutManager(polo);
        list_item.getRecycledViewPool().clear();
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        // Save list state
        mListState = mLayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, mListState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        // Retrieve list state and list/item positions
        if (state != null)
            mListState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }
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

    public void setItems() {
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

    public void startAlertAtParticularTime() {

        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

    }
}
