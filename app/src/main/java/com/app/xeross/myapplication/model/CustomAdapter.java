package com.app.xeross.myapplication.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.xeross.controller.R;
import com.app.xeross.myapplication.view.Item;

import java.util.List;

class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTextView;
    public ImageButton mImageButton;
    public FrameLayout mFrameLayout;

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

    List<Item> mItemList;
    Context mContext;
    int row_index = -1;

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
        //params.setMargins(1, 1, mItemList.get(position).getInt(), 1);

        holder.mFrameLayout.setLayoutParams(params);

        if (mItemList.get(position).getSt().equals("")) {
            holder.mImageButton.setVisibility(View.GONE);
        }
        layoutResizer(holder, mItemList.get(position));

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

    public void layoutResizer(CustomViewHolder holder, Item ci) {
        //Display metric can catch the width or/and height of the device
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //This variable take the width of the device and with the index value and a calculation
        //the layout width size is adjusting
        int deviceWidth, deviceHeight;
        deviceWidth = (displaymetrics.widthPixels * 17 * (ci.getInt() + 1)) / 100;
//        deviceHeight = (displaymetrics.heightPixels / mListMoodItems.size()-1);
        //Here we initiate the new width to each item layout
        holder.mFrameLayout.getLayoutParams().width = deviceWidth;
//        holder.mRelativeLayout.getLayoutParams().height = deviceHeight;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}