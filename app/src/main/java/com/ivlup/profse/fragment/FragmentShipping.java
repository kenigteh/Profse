package com.ivlup.profse.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ivlup.profse.MainActivity;
import com.ivlup.profse.R;
import com.ivlup.profse.tools.FragmentChoose;

public class FragmentShipping extends Fragment {


    private FragmentTransaction ft;
    private FragmentManager fm;
    private GridLayout mainGrid;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Услуги по доставке");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_shipping, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        mainGrid = view.findViewById(R.id.shipping_grid);

        setSingleEvent(mainGrid);

    }


   private void setSingleEvent(GridLayout mainGrid) {

        for (int i = 0; i < mainGrid.getChildCount(); i++)
        {

            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (finalI) {

                        case 0 :
                            MainActivity.chosenClient = "Курьер";
                            MainActivity.fm = getFragmentManager();
                            MainActivity.ft = MainActivity.fm.beginTransaction();
                            MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                            MainActivity.ft.commit();
                            MainActivity.ft.addToBackStack(null);
                            break;
                        case 1 :
                            MainActivity.chosenClient = "Грузоперевозки";
                            MainActivity.fm = getFragmentManager();
                            MainActivity.ft = MainActivity.fm.beginTransaction();
                            MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                            MainActivity.ft.commit();
                            MainActivity.ft.addToBackStack(null);
                            break;
                        case 2 :
                            MainActivity.chosenClient = "Доставка ценных грузов";
                            MainActivity.fm = getFragmentManager();
                            MainActivity.ft = MainActivity.fm.beginTransaction();
                            MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                            MainActivity.ft.commit();
                            MainActivity.ft.addToBackStack(null);
                            break;
                        case 3 :
                            MainActivity.chosenClient = "Доставка цветов, шаров, подарков";
                            MainActivity.fm = getFragmentManager();
                            MainActivity.ft = MainActivity.fm.beginTransaction();
                            MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                            MainActivity.ft.commit();
                            MainActivity.ft.addToBackStack(null);
                            break;
                        case 4 :
                            MainActivity.chosenClient = "Доставка угля, дров";
                            MainActivity.fm = getFragmentManager();
                            MainActivity.ft = MainActivity.fm.beginTransaction();
                            MainActivity.ft.replace(R.id.fragment_container, new FragmentChoose());
                            MainActivity.ft.commit();
                            MainActivity.ft.addToBackStack(null);
                            break;
                        case 5 :
                            MainActivity.chosenClient = "Доставка песка, грунта и пр";
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
