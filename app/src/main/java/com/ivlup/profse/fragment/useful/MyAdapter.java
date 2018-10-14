package com.ivlup.profse.fragment.useful;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivlup.profse.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList companyList;
    ArrayList logoList;

    public MyAdapter(ArrayList companyList, ArrayList logoList) {
        this.companyList = companyList;
        this.logoList = logoList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clients, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //holder.itemAvatar.setImageBitmap(logoList[position]);
        holder.itemAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityMain.this, "This is: " + companyList[position], Toast.LENGTH_SHORT).show();
            }
        });
       // holder.itemCategory.setText(companyList[position]);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }
}
