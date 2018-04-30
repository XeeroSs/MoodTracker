package com.app.xeross.myapplication.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.app.xeross.myapplication.controller.FinalHActivity;

/**
 * Created by XeroSs on 01/03/2018.
 */

public class AddItemList extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("DEBUG", "onReceive (AddItemList)");

        //FinalHActivity.loadData(context);

        Toast.makeText(context, "A", Toast.LENGTH_LONG).show();

        FinalHActivity.saveData(context);
    }

}
