package com.ivlup.profse.fragment.useful;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivlup.profse.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView itemAvatar;
    public TextView  itemCategory;

    public MyViewHolder(View itemView) {
        super(itemView);
        itemAvatar = (ImageView)itemView.findViewById(R.id.ivAvatar);
        itemCategory = (TextView)itemView.findViewById(R.id.tvCategory);
    }
}
