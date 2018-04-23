package com.app.xeross.myapplication.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.app.xeross.myapplication.controller.FinalHActivity;
import com.app.xeross.myapplication.controller.MainActivity;

/**
 * Created by XeroSs on 01/03/2018.
 */

public class AddItemList extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("DEBUG", "onReceive (AddItemList)");
        // For our recurring task, we'll just display a message
        Toast.makeText(context, "Nouveau jour ! Vous pouvez dès à présent ajouter une nouvelle humeur", Toast.LENGTH_SHORT).show();
        FinalHActivity.saveData(context);
        MainActivity.newday = true;
    }
}
