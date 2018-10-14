package com.ivlup.profse.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivlup.profse.R;
import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.backend.Data;
import com.ivlup.profse.fragment.useful.AssetCategory;
import com.ivlup.profse.fragment.useful.CategoryItem;
import com.ivlup.profse.fragment.useful.ObservableInteger;
import com.ivlup.profse.fragment.useful.OnIntegerChangeListener;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;
import java.util.List;

public class FragmentCategoryCards extends Fragment {
    RecyclerView rvMain;

    ArrayList <ArrayList <String> > allCategories = new ArrayList<>();
    ArrayList <AssetCategory> currentCategory = new ArrayList<>();
    public static GroupAdapter adapter = new GroupAdapter();

    public static int globalParentId = 0;
    public static int newId = 0;


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
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        super.onViewCreated(view, savedInstanceState);

        if (allCategories.isEmpty()) fetchCategories();
        if (allCategories.isEmpty()) Toast.makeText(getContext(), "empty!",Toast.LENGTH_SHORT).show();


        for (int i = 0; i < allCategories.size(); i++) {
            if (Integer.valueOf(allCategories.get(i).get(2)) == globalParentId) {

                AssetCategory ass = new AssetCategory(
                        Integer.valueOf(
                                allCategories.get(i).get(1)),
                        allCategories.get(i).get(0),
                        allCategories.get(i).get(3),
                        Integer.valueOf(allCategories.get(i).get(2)),
                        Integer.valueOf(allCategories.get(i).get(4))
                );

                currentCategory.add(ass);
            }
        }
        rvMain = (RecyclerView) view.findViewById(R.id.rvMain);

        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new GridLayoutManager (getContext(), 2));
        adapter.clear();

        ArrayList<Item> items = new ArrayList<>();

        for (AssetCategory as : currentCategory) {
            items.add(new CategoryItem(as));
        }

        adapter.addAll(items);


    }

    private void fetchCategories () {

        Log.i("my log" ,"JA, TUT!");
        for (int i = 0; i < Data.getCategories().length; i++) {

            ArrayList<String> cur = new ArrayList<>();

            cur.add(Data.getCategories()[i].name);                          //0
            cur.add(String.valueOf(Data.getCategories()[i].id));            //1
            cur.add(String.valueOf(Data.getCategories()[i].parent_id));     //2
            cur.add(Data.getCategories()[i].photo);                         //3
            cur.add(String.valueOf(Data.getCategories()[i].type));          //4

            allCategories.add(cur);
        }
    }
}
