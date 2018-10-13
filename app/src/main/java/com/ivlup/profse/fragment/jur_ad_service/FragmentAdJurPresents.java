package com.ivlup.profse.fragment.jur_ad_service;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.R;
import com.ivlup.profse.fragment.useful.FragmentChoose;

public class FragmentAdJurPresents extends Fragment {
    private GridLayout mainGrid;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Презенты для клиентов");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ad_jur_presents, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        mainGrid = view.findViewById(R.id.ad_jurist_presents_grid);

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
                                MainActivity.chosenClient = "От 30 до 100 руб";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 1:
                                MainActivity.chosenClient = "От 100 до 300 руб";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 2:
                                MainActivity.chosenClient = "От 300 до 500 руб";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;
                            case 3:
                                MainActivity.chosenClient = "От 500 руб";
                                MainActivity.fm = getFragmentManager();
                                MainActivity.ft = MainActivity.fm.beginTransaction();
                                MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                                MainActivity.ft.commit();
                                MainActivity.ft.addToBackStack(null);
                                break;

                        }
                    }
                });
            }
        }

}
