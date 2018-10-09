package com.ivlup.profse.fragment.jur_ad_service;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ivlup.profse.MainActivity;
import com.ivlup.profse.R;
import com.ivlup.profse.tools.ClientListFragment;

public class FragmentAdJurVip extends Fragment {
    private GridLayout mainGrid;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Вип-подарки");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ad_jur_vip, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        mainGrid = view.findViewById(R.id.ad_jurist_vip_grid);

        setSingleEvent(mainGrid);

    }
        private void setSingleEvent (GridLayout mainGrid){


            for (int i = 0; i < mainGrid.getChildCount(); i++) {

                CardView cardView = (CardView) mainGrid.getChildAt(i);
                final int finalI = i;
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        switch (finalI) {

                            case 0:
                                MainActivity.chosenClient = "Ручная работа";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new ClientListFragment());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 1:
                                MainActivity.chosenClient = "Многотиражная продукция";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new ClientListFragment());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;

                        }
                    }
                });
            }
        }

}
