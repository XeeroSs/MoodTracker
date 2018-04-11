package com.app.xeross.myapplication.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.view.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XeroSs on 14/02/2018.
 */

 class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String STRING_NAME = "STRING_NAME";
    public TextView mTextView;
    public ImageButton mImageButton;
    public FrameLayout mFrameLayout;
    public SharedPreferences sharedPref;
    Context mContext;

    Interface mInterface;

    public CustomViewHolder(View itemView) {
        super(itemView);
        mImageButton = itemView.findViewById(R.id.image_button_add);
        mTextView = itemView.findViewById(R.id.textView);
        mFrameLayout = itemView.findViewById(R.id.FrameLayout_1);
        itemView.setOnClickListener(this);
    }

    public void setInterface(Interface mInterface) {
        this.mInterface = mInterface;
    }

    @Override
    public void onClick(View v) {
        mInterface.onClick(v, getAdapterPosition());
    }
}

 public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    final String TEXT_TEST = "TEXT_TEST";
    public SharedPreferences sharedPref;
    public ImageButton mImageButton;
    List<Item> mItemList;
    Context mContext;
    int row_index = -1;
    private List<Item> items;

    public CustomAdapter(List<Item> itemList, Context context) {
        mItemList = itemList;
        mContext = context;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View mItemView = inflater.inflate(R.layout.rowlayout, parent, false);
        return new CustomViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        FrameLayout.MarginLayoutParams params = (FrameLayout.MarginLayoutParams) holder.mFrameLayout.getLayoutParams();

        holder.mTextView.setText(mItemList.get(position).getName());
        holder.mFrameLayout.setBackgroundColor(Color.parseColor(mItemList.get(position).getColorFrameLayout()));
        params.setMargins(1, 1, mItemList.get(position).getInt(), 1);
        holder.mFrameLayout.setLayoutParams(params);
        //String ssd = mItemList.get(position).getSt();

        /*if (ssd.toString().length() != 0) {
            //I active "mImageButton"
            mImageButton.setVisibility(View.VISIBLE);
        } else {
            //I disable "mImageButton"
            mImageButton.setVisibility(View.GONE);
        }*/

        //When the user click on "mImageButton", I call the "onClick" method
        holder.mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String ssd = mItemList.get(position).getSt();
                //Condition to check if the text "TEXT_TEST" to a length that exceeds 0
                //if (ssd.toString().length() != 0) {
                Toast.makeText(mContext, mItemList.get(position).getSt(), Toast.LENGTH_LONG).show();
                //}
            }
        });

        holder.setInterface(new Interface() {
            @Override
            public void onClick(View v, int position) {
                row_index = position;
                Common.currentItem = mItemList.get(position);
                notifyDataSetChanged();
            }
        });

        if (row_index == position) {
            holder.mTextView.setTextColor(Color.parseColor("#c5c5c7"));
        } else {
            holder.mTextView.setTextColor(Color.parseColor("#000000"));
        }


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
