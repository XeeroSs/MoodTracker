package com.app.xeross.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.model.AddItemList;

import java.util.Calendar;

//class forming the activity of where the application starts for the first time and is also our core application
public class MainActivity extends AppCompatActivity {

    public static final String CT_1 = "CT_1";
    final String TEXT_TEST = "TEXT_TEST";
    final String BOOLEAN_OK = "BOOLEAN_OK";
    final String TEXT_COLORS = "TEXT_COLORS";
    final String TEXT_SIZES = "TEXT_SIZES";
    int layoutId = R.layout.activity_main;
    private int page = 0;
    private ImageButton mButtonAdd, mButtonFinal;
    private Swipe mGestureDetector;
    private ImageView mImageView;
    public static boolean newday = true;
    private EditText mEditText;
    private TextView mTextTest;
    private SharedPreferences mPreferences;
    private android.support.constraint.ConstraintLayout mBackground;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButtonAdd = findViewById(R.id.image_Button_2);
        mButtonFinal = findViewById(R.id.image_Button);
        mImageView = findViewById(R.id.imageView);
        mBackground = findViewById(R.id.background);
        final MediaPlayer sol = MediaPlayer.create(this, R.raw.sol);
        final MediaPlayer do2 = MediaPlayer.create(this, R.raw.do2);
        final MediaPlayer si = MediaPlayer.create(this, R.raw.si);
        final MediaPlayer re = MediaPlayer.create(this, R.raw.re);
        final MediaPlayer do1 = MediaPlayer.create(this, R.raw.do1);
        mTextTest = findViewById(R.id.text_test);
        mPreferences = getSharedPreferences(CT_1, MODE_PRIVATE);

        //new instance of the class "SwipeGestureDetector" with as options the class "MainActivity"
        mGestureDetector = new Swipe(MainActivity.this);

        if(newday == true) {
            startAlertAtParticularTime();


        }

        //than the user go click on the button "mButtonAdd"
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //I create  the AlertDialog whose going to appear and I tell it to do that in the MainActivity class
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //I take back the View layout_dialog
                View mView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
                //I tell what will be in the alertdialog (title, button cancel, and button validate)
                builder.setView(mView).setTitle("Commentaire").setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //I do a condition and ask which page we are
                        switch (IntPage()) {
                            case 0:
                                setInt(0);
                                setItem("#fff9ec4f", 1, true);
                                do2.start();
                                break;
                            case 1:
                                setInt(1);
                                setItem("#ffb8e986", 200, true);
                                si.start();
                                break;
                            case 2:
                                setInt(2);
                                setItem("#a5368ad9", 400, true);
                                sol.start();
                                break;
                            case 3:
                                setInt(3);
                                setItem("#3b3b3b", 600, true);
                                re.start();
                                break;
                            case 4:
                                setInt(4);
                                setItem("#ffde3c50", 800, true);
                                do1.start();
                                break;
                        }

                        if (mEditText.getText().toString().length() == 0) {
                            mTextTest.setText(null);
                            setComment(mTextTest);
                        } else {
                            mTextTest.setText(mEditText.getText().toString());
                            setComment(mTextTest);
                        }
                    }
                });
                //I take back the idea of mEditText
                mEditText = mView.findViewById(R.id.text_dialog);
                //I create the dialog article
                final AlertDialog dialogs = builder.create();
                //I display the alertdialog
                dialogs.show();
            }
        });

        //When the user click on the mButtonFinal
        mButtonFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //New intent of an activity having for parameter the MainActivity class and FinalHActivity class
                Intent finalH = new Intent(MainActivity.this, FinalHActivity.class);
                finalH.putExtra(TEXT_TEST, getComment(mTextTest));
                finalH.putExtra(TEXT_COLORS, getColors());
                finalH.putExtra(TEXT_SIZES, getSizes());
                //I start the activity
                startActivity(finalH);
            }
        });
    }

    //using the "swipe"
    public void onSwipe(Swipe.SwipeD direction) {
        //if "IntPage == 0 to 4" change the background, image and "setIntPage"
        if (IntPage() == 0) {
            switch (direction) {
                case TOP_TO_BOTTOM:
                    setDrawable(R.drawable.smiley_sad, R.color.faded_red, 4);
                    break;
                case BOTTOM_TO_TOP:
                    setDrawable(R.drawable.smiley_happy, R.color.light_sage, 1);
                    break;
            }
        } else if (IntPage() == 1) {
            switch (direction) {
                case TOP_TO_BOTTOM:
                    setDrawable(R.drawable.smiley_super_happy, R.color.banana_yellow, 0);
                    break;
                case BOTTOM_TO_TOP:
                    mImageView.setImageResource(R.drawable.smiley_normal);
                    mBackground.setBackgroundResource(R.color.cornflower_blue_65);
                    mImageView.setBackgroundResource(R.color.cornflower_blue_65);
                    mButtonFinal.setBackgroundResource(R.color.cornflower_blue_65);
                    mButtonAdd.setBackgroundResource(R.color.cornflower_blue_65);
                    setIntPage(2);
                    break;
            }
        } else if (IntPage() == 2) {
            switch (direction) {
                case TOP_TO_BOTTOM:
                    setDrawable(R.drawable.smiley_happy, R.color.light_sage, 1);
                    break;
                case BOTTOM_TO_TOP:
                    setDrawable(R.drawable.smiley_disappointed, R.color.warm_grey, 3);
                    break;
            }
        } else if (IntPage() == 3) {
            switch (direction) {
                case TOP_TO_BOTTOM:
                    mImageView.setImageResource(R.drawable.smiley_normal);
                    mBackground.setBackgroundResource(R.color.cornflower_blue_65);
                    mImageView.setBackgroundResource(R.color.cornflower_blue_65);
                    mButtonFinal.setBackgroundResource(R.color.cornflower_blue_65);
                    mButtonAdd.setBackgroundResource(R.color.cornflower_blue_65);
                    setIntPage(2);
                    break;
                case BOTTOM_TO_TOP:
                    setDrawable(R.drawable.smiley_sad, R.color.faded_red, 4);
                    break;
            }
        } else if (IntPage() == 4) {
            switch (direction) {
                case TOP_TO_BOTTOM:
                    setDrawable(R.drawable.smiley_disappointed, R.color.warm_grey, 3);
                    break;
                case BOTTOM_TO_TOP:
                    setDrawable(R.drawable.smiley_super_happy, R.color.banana_yellow, 0);
                    break;
            }
        }
    }

    //recovers the number a page
    public int IntPage() {
        return page;
    }

    //initializes the number a page
    public void setIntPage(int page) {
        this.page = page;
    }

    public void setDrawable(int i1, int i2, int i3) {
        mImageView.setImageResource(i1);
        mImageView.setBackgroundResource(i2);
        mBackground.setBackgroundResource(i2);
        mButtonFinal.setBackgroundResource(i2);
        mButtonAdd.setBackgroundResource(i2);
        setIntPage(i3);
    }

    //detects the movement of the user (inside the view)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public String getComment(TextView tv) {
        String rst = mPreferences.getString("comment", null);
        if (rst != null) {
            tv.setText(rst);
        }
        return rst;
    }

    //Save user comment with API SharedPreference
    public void setComment(TextView tv) {
        if (tv != null) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString("comment", tv.getText().toString());
            editor.commit();
        }
    }

    public int getInt() {
        int i = 5;
        int it = mPreferences.getInt("int", i);
        return it;
    }

    public void setInt(int i) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("int", i);
        editor.commit();
    }

    public void setItem(String color, int size, boolean val) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("SIZE", size);
        editor.putBoolean("BOOLEAN_OK", val);
        editor.putString("COLOR", color);
        editor.commit();
    }

    public String getColors() {
        String color = " ";
        String colord = mPreferences.getString("COLOR", color);
        return colord;
    }

    public int getSizes() {
        int size = 0;
        int sizes = mPreferences.getInt("SIZE", size);
        return sizes;
    }

    public void startAlertAtParticularTime() {

        Log.i("DEBUG", "startAlertAtParticularTime (ActivityMain)");
        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intentA = new Intent(this, AddItemList.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intentA, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    }

}

//class allowing for intercept a "swipe"
class Swipe extends GestureDetector {
    //I determine the "duration" of the user to do the "swipe"
    private final static int DELTA_MIN = 50;

    //The maker of the class whose going to allowed the direction capture of the user "swipe"
    Swipe(final MainActivity context) {
        super(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY) {
                float deltaY = e1.getY() - e2.getY();
                //Condition whose checking if the movement of the user, top to bottom or conversely
                //We check if the user did well with his "swipe"
                if (Math.abs(deltaY) >= DELTA_MIN) {
                    //If the "swipe" is top to bottom
                    if (deltaY < 0) {
                        //If the "swipe" is bottom to top
                        context.onSwipe(SwipeD.BOTTOM_TO_TOP);
                        return true;
                        //Otherwise if it's to top to bottom
                    } else {
                        context.onSwipe(SwipeD.TOP_TO_BOTTOM);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public enum SwipeD {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }
}