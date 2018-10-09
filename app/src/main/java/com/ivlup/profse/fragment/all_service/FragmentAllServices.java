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

import com.ivlup.profse.MainActivity;
import com.ivlup.profse.R;

public class FragmentAllServices extends Fragment {

    private GridLayout mainGrid;
    private FragmentTransaction ft = MainActivity.ft;
    private FragmentManager fm = MainActivity.fm;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Услуги для всех");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.all_services, container, false);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        mainGrid = view.findViewById(R.id.all_grid);

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
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesBeauty());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 1 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesHealth());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 2 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesSport());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 3 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesRepair());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 4 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesEducation());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 5 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesAuto());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 6 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesCelebration());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 7 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAllServicesOther());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                    }
                }
            });
        }
    }
}
