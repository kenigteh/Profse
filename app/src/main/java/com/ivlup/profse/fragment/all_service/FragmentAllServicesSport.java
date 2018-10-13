package com.ivlup.profse.fragment.all_service;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.fragment.useful.MyListFragment;
import com.ivlup.profse.R;
import com.ivlup.profse.contractor.FragmentListContractors;

public class FragmentAllServicesSport extends Fragment {

    private GridLayout mainGrid;
    private FragmentTransaction ft;
    private FragmentManager fm;


    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Спорт");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.all_rest_and_sport, container, false);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        mainGrid = view.findViewById(R.id.rest_and_sport_grid);

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
                            MainActivity.chosen = "SPORT_FITNESS";
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new MyListFragment());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 1 :
                            MainActivity.chosenClient = "Танцы";
                            MainActivity.fm = getFragmentManager();
                            MainActivity.ft = MainActivity.fm.beginTransaction();
                            MainActivity.ft.replace(R.id.fragment_container, new FragmentListContractors());
                            MainActivity.ft.commit();
                            MainActivity.ft.addToBackStack(null);
                            break;
                        case 2 :
                            MainActivity.chosen = "SPORT_SECTIONS";
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new MyListFragment());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 3 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesRest());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 4 :

                            break;
                        case 5 :

                            break;
                    }
                }
            });
        }
    }
}
