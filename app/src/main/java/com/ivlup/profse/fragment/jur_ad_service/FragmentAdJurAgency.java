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

public class FragmentAdJurAgency extends Fragment {
    private GridLayout mainGrid;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Рекламные агенства");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ad_jur_agency, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        mainGrid = view.findViewById(R.id.ad_jurist_agency_grid);

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
                                //MainActivity.chosen = "Ателье, текстиль с вышивкой";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentAdJurEvents());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 1:
                               // MainActivity.chosen = "Ателье, шейные платки";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentAdJurPresents());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 2:
                               // MainActivity.chosen = "Ателье, шевроны";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentAdJurVip());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 3:
                                MainActivity.chosenClient = "Имидж сотрудников";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new ClientListFragment());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 4:
                                MainActivity.chosenClient = "Раздаточная сувенирка";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new ClientListFragment());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                            case 5:
                                MainActivity.chosenClient = "Подарки для детей";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new ClientListFragment());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                        }
                    }
                });
            }
        }

}
