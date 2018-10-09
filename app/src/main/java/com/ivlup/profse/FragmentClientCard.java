package com.ivlup.profse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ivlup.profse.tools.Client;
import com.ivlup.profse.tools.ClientItem;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;

public class FragmentClientCard extends Fragment {

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Поставщик");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_card, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        String name = MainActivity.chosenName;
        String category = MainActivity.chosenClient;

        TextView cName = view.findViewById(R.id.client_name);
        cName.setText(name);
        TextView cCategory = view.findViewById(R.id.client_category);
        cCategory.setText(category);

        TextView cPhone = view.findViewById(R.id.client_phone);
        cPhone.setText(MainActivity.mapClients.get(name).getPhone());

        TextView cAddress = view.findViewById(R.id.client_address);
        cAddress.setText(MainActivity.mapClients.get(name).getAddress());

        TextView cSite = view.findViewById(R.id.client_site);
        cSite.setText(MainActivity.mapClients.get(name).getSite());

        TextView cVk = view.findViewById(R.id.client_vk);
        cVk.setText(MainActivity.mapClients.get(name).getVk());

        TextView cTwitter = view.findViewById(R.id.client_twitter);
        cTwitter.setText(MainActivity.mapClients.get(name).getTwitter());

        TextView cFacebook = view.findViewById(R.id.client_facebook);
        cFacebook.setText(MainActivity.mapClients.get(name).getFacebook());

        TextView cInstagram = view.findViewById(R.id.client_instagram);
        cInstagram.setText(MainActivity.mapClients.get(name).getInstagram());


        LinearLayout phone_layout = (LinearLayout) view.findViewById(R.id.number_layout);
        if (MainActivity.mapClients.get(name).getPhone().isEmpty()) phone_layout.setVisibility(View.INVISIBLE);

        LinearLayout adress_layout = (LinearLayout) view.findViewById(R.id.address_layout);
        if (MainActivity.mapClients.get(name).getAddress().isEmpty()) adress_layout.setVisibility(View.INVISIBLE);

        LinearLayout site_layout = (LinearLayout) view.findViewById(R.id.site_layout);
        if (MainActivity.mapClients.get(name).getSite().isEmpty()) site_layout.setVisibility(View.INVISIBLE);

        LinearLayout vk_layout = (LinearLayout) view.findViewById(R.id.vk_layout);
        if (MainActivity.mapClients.get(name).getVk().isEmpty()) vk_layout.setVisibility(View.INVISIBLE);

        LinearLayout twitter_layout = (LinearLayout) view.findViewById(R.id.twitter_layout);
        if (MainActivity.mapClients.get(name).getTwitter().isEmpty()) twitter_layout.setVisibility(View.INVISIBLE);

        LinearLayout facebook_layout = (LinearLayout) view.findViewById(R.id.facebook_layout);
        if (MainActivity.mapClients.get(name).getFacebook().isEmpty()) facebook_layout.setVisibility(View.INVISIBLE);

        LinearLayout instagram_layout = (LinearLayout) view.findViewById(R.id.instagram_layout);
        if (MainActivity.mapClients.get(name).getInstagram().isEmpty()) instagram_layout.setVisibility(View.INVISIBLE);



    }
}
