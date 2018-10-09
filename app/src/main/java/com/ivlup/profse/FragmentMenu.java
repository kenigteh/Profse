package com.ivlup.profse;

import android.annotation.TargetApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.ivlup.profse.fragment.FragmentJurService;
import com.ivlup.profse.fragment.FragmentPresents;
import com.ivlup.profse.fragment.FragmentRules;
import com.ivlup.profse.fragment.FragmentShipping;
import com.ivlup.profse.fragment.all_service.FragmentAllServices;
import com.ivlup.profse.fragment.jur_ad_service.FragmentAdJur;

public class FragmentMenu extends Fragment {

    private FragmentMenu fragment;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private GridLayout mainGrid;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Profse");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.main_menu, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
       mainGrid = view.findViewById(R.id.menu_grid);

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
                            ft.replace(R.id.fragment_container, new FragmentAllServices());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 1 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentJurService());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 2 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentAdJur());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 3 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentShipping());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 4 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentPresents());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                        case 5 :
                            fm = getFragmentManager();
                            ft = fm.beginTransaction();
                            ft.replace(R.id.fragment_container, new FragmentRules());
                            ft.commit();
                            ft.addToBackStack(null);
                            break;
                    }
                }
            });
        }
    }


}
