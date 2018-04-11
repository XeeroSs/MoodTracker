package com.app.xeross.myapplication.controller;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.model.CustomAdapter;
import com.app.xeross.myapplication.view.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

//Class who manages the history
public class FinalHActivity extends ListActivity {

    final public static String SHARED = "SHARED";
    public static ArrayList<Item> mItems = new ArrayList<>();
    final String TEXT_TEST = "TEXT_TEST";
    final String TEXT_COLORS = "TEXT_COLORS";
    final String TEXT_SIZES = "TEXT_SIZES";
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView list_item;
    private CustomAdapter adapter;

    public FinalHActivity() {

    }

    public static void saveData(Context a) {

        Log.i("DEBUG", "SaveData (FinalActivityH)");
        SharedPreferences sharedPreferences = a.getSharedPreferences(SHARED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mItems);
        editor.putString("TASK", json);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_final_h);

        final Intent intent = getIntent();
        String color = intent.getStringExtra(TEXT_COLORS);
        int size = intent.getIntExtra(TEXT_SIZES, 0);
        String str = intent.getStringExtra(TEXT_TEST);

        loadData();
        if (color != " ") {
            Log.i("DEBUG", "ADDList (FinalActivityH)");
            switch (mItems.size()) {
                case 0:
                    mItems.add(new Item("Aujourd'hui", color, size, str));
                    break;
                case 1:
                    mItems.add(0, new Item("Aujourd'hui", color, size, str));
                    mItems.get(1).setName("Hier");
                    break;
                case 2:
                    mItems.add(0, new Item("Aujourd'hui", color, size, str));
                    mItems.get(1).setName("Hier");
                    mItems.get(2).setName("Avant-Hier");
                    break;
                case 3:
                    mItems.add(0, new Item("Aujourd'hui", color, size, str));
                    mItems.get(1).setName("Hier");
                    mItems.get(2).setName("Avant-Hier");
                    mItems.get(3).setName("Il y a 3j");
                    break;
                case 4:
                    mItems.add(0, new Item("Aujourd'hui", color, size, str));
                    mItems.get(1).setName("Hier");
                    mItems.get(2).setName("Avant-Hier");
                    mItems.get(3).setName("Il y a 3j");
                    mItems.get(4).setName("Il y a 4j");
                    break;
                case 5:
                    mItems.add(0, new Item("Aujourd'hui", color, size, str));
                    mItems.get(1).setName("Hier");
                    mItems.get(2).setName("Avant-Hier");
                    mItems.get(3).setName("Il y a 3j");
                    mItems.get(4).setName("Il y a 4j");
                    mItems.get(5).setName("Il y a 5j");
                    break;
                case 6:
                    mItems.add(0, new Item("Aujourd'hui", color, size, str));
                    mItems.get(1).setName("Hier");
                    mItems.get(2).setName("Avant-Hier");
                    mItems.get(3).setName("Il y a 3j");
                    mItems.get(4).setName("Il y a 4j");
                    mItems.get(5).setName("Il y a 5j");
                    mItems.get(6).setName("Il y a 6j");
                    break;
                case 7:
                    DeleteView();
                    mItems.add(new Item("Aujourd'hui", color, size, str));
                    break;

            }
        }

        mLayoutManager = new LinearLayoutManager(this);
        list_item = findViewById(R.id.recycler);
        Log.i("DEBUG", "Main (FinalActivityH)");
        list_item.setLayoutManager(mLayoutManager);
        adapter = new CustomAdapter(mItems, this);
        list_item.setAdapter(adapter);
    }

    public void DeleteView() {
        Log.i("DEBUG", "DeleteView (FinalActivityH)");
        mItems.clear();
        saveData(this);
    }

    private void loadData() {
        Log.i("DEBUG", "LoadData (FinalActivityH)");
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("TASK", null);
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();
        mItems = gson.fromJson(json, type);
        if (mItems == null) {
            mItems = new ArrayList<>();
        }
    }
}
