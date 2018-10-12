package com.ivlup.profse.tools;

import android.support.annotation.NonNull;
import android.view.View;

import com.ivlup.profse.OnRecyclerViewItemClickListener;
import com.xwray.groupie.databinding.BindableItem;
import com.ivlup.profse.databinding.ItemClientsBinding;

import com.ivlup.profse.R;

public class ContractorCard extends BindableItem<ItemClientsBinding> {

    private Contractor contractor;

    public ContractorCard(Contractor contractor) {
        this.contractor = contractor;
    }

    @Override
    public void bind(@NonNull final ItemClientsBinding viewBinding, int position) {
        // Populate the data into the template view using the data object
        viewBinding.contractorName.setText(contractor.getName());
        viewBinding.cardClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Picasso.with(viewBinding.avatar.getContext()).load(arrayUrl.get(position)).into(viewBinding.avatar);

    }

    @Override
    public int getLayout() {
        return R.layout.item_clients;
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
