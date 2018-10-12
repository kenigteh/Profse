package com.ivlup.profse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView cName = view.findViewById(R.id.contractor_name);
        cName.setText(name);
        TextView cCategory = view.findViewById(R.id.contractor_category);
        cCategory.setText(category);


        TextView cPhone = view.findViewById(R.id.contractor_phone);
        cPhone.setText(MainActivity.mapContractors.get(name).getPhone());

        TextView cAddress = view.findViewById(R.id.contractor_address);
        cAddress.setText(MainActivity.mapContractors.get(name).getAddress());

        TextView cSite = view.findViewById(R.id.contractor_site);
        cSite.setText(MainActivity.mapContractors.get(name).getSite());

        TextView cVk = view.findViewById(R.id.contractor_vk);
        cVk.setText(MainActivity.mapContractors.get(name).getVk());

        TextView cTwitter = view.findViewById(R.id.contractor_twitter);
        cTwitter.setText(MainActivity.mapContractors.get(name).getTwitter());

        TextView cFacebook = view.findViewById(R.id.contractor_facebook);
        cFacebook.setText(MainActivity.mapContractors.get(name).getFacebook());

        TextView cInstagram = view.findViewById(R.id.contractor_instagram);
        cInstagram.setText(MainActivity.mapContractors.get(name).getInstagram());


        LinearLayout phone_layout = (LinearLayout) view.findViewById(R.id.number_layout);
        if (String.valueOf(cPhone.getText()).isEmpty()) phone_layout.setVisibility(View.GONE);

        LinearLayout adress_layout = (LinearLayout) view.findViewById(R.id.address_layout);
        if (String.valueOf(cAddress.getText()).isEmpty()) adress_layout.setVisibility(View.GONE);

        LinearLayout site_layout = (LinearLayout) view.findViewById(R.id.site_layout);
        if (String.valueOf(cSite.getText()).isEmpty()) site_layout.setVisibility(View.GONE);

        LinearLayout vk_layout = (LinearLayout) view.findViewById(R.id.vk_layout);
        if (String.valueOf(cVk.getText()).isEmpty()) vk_layout.setVisibility(View.GONE);

        LinearLayout twitter_layout = (LinearLayout) view.findViewById(R.id.twitter_layout);
        if (String.valueOf(cTwitter.getText()).isEmpty()) twitter_layout.setVisibility(View.GONE);

        LinearLayout facebook_layout = (LinearLayout) view.findViewById(R.id.facebook_layout);
        if (String.valueOf(cFacebook.getText()).isEmpty()) facebook_layout.setVisibility(View.GONE);

        LinearLayout instagram_layout = (LinearLayout) view.findViewById(R.id.instagram_layout);
        if (String.valueOf(cInstagram.getText()).isEmpty()) instagram_layout.setVisibility(View.GONE);

        AppCompatButton discountBt = (AppCompatButton) view.findViewById(R.id.get_discount);
        String discountTxt = String.valueOf(MainActivity.mapContractors.get(name).getDiscount());
        discountBt.setVisibility(View.GONE);
        if (discountTxt.equals("Согласен всем")) {
            discountBt.setVisibility(View.VISIBLE);
            discountBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Скидка получена! Перейдите в профиль, чтобы её увидеть" , Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
