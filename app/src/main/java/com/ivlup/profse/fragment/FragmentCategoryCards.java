package com.ivlup.profse.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivlup.profse.R;
import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.fragment.useful.MyAdapter;

import java.util.ArrayList;

public class FragmentCategoryCards extends Fragment {
    RecyclerView rvMain;
    ArrayList<String> logos;


    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity())
                .setActionBarTitle("Услуги для всех");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.category_cards, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        rvMain = (RecyclerView) view.findViewById(R.id.rvMain);


        MyAdapter adapter = new MyAdapter(MainActivity.contractorCategories, logos);
        rvMain.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvMain.setAdapter(adapter);

    }
}
