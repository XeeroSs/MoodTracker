package com.app.xeross.myapplication.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.app.xeross.controller.R;

/**
 * Created by XeroSs on 01/03/2018.
 */

public class AddItemList extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        Toast.makeText(context, "testt", Toast.LENGTH_SHORT).show();
    }
}
