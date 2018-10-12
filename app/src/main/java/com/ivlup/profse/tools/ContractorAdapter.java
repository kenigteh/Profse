package com.ivlup.profse.tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivlup.profse.MainActivity;
import com.ivlup.profse.R;

import java.util.ArrayList;

public class ContractorAdapter extends RecyclerView.Adapter<ContractorAdapter.MyViewHolder> {


    private ArrayList clientDataSet;
    public Context mContext;
    private int lastPosition = -1;

    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView textName;
        ImageView imageClient;

        public MyViewHolder(View itemView){
            super (itemView);
            this.textName = itemView.findViewById(R.id.contractor_name);
            this.imageClient = itemView.findViewById(R.id.avatar);
        }

    }

    public ContractorAdapter(Context context, ArrayList clients){
        this.clientDataSet = clients;
        mContext=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_clients, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        final TextView textViewName = holder.textName;
        //ImageView imageViewHero = holder.imageHero;

        textViewName.setText((CharSequence) MainActivity.mapContractors.get(MainActivity.chosenClient));

        //textViewUniverse.setText(clientDataSet.get(listPosition).getUniverse());

        //String src = clientDataSet.get(listPosition).getImage();
        /*Picasso.with(mContext)
                .load("file:///android_asset/images/"+src+".jpg")
                .resize(300, 300)
                .into(imageViewHero);*/
    }


    @Override
    public int getItemCount() {
        return clientDataSet.size();
    }
}
