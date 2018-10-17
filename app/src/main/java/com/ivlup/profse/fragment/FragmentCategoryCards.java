package com.ivlup.profse.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivlup.profse.R;
import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.backend.Category;
import com.ivlup.profse.backend.Data;

import com.ivlup.profse.fragment.useful.CategoryItem;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;

public class FragmentCategoryCards extends Fragment {
    RecyclerView rvMain;

    public static ArrayList <Category> currentCategories = new ArrayList<>();
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

        if (currentCategories.isEmpty()) fetchCategories(currentCategories);
        if (currentCategories.isEmpty()) Toast.makeText(getContext(), "empty!",Toast.LENGTH_SHORT).show();

        rvMain = (RecyclerView) view.findViewById(R.id.rvMain);

        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new GridLayoutManager (getContext(), 2));
        adapter.clear();

        ArrayList<Item> items = new ArrayList<>();

        for (Category as : currentCategories) {
            items.add(new CategoryItem(as));
        }

        adapter.addAll(items);


    }

    public static ArrayList<Category> fetchCategories (ArrayList<Category> categories) {

        if (!categories.isEmpty()) categories.clear();
        Log.i("my log" ,"JA, TUT!");
        for (int i = 0; i < Data.getCategories().size(); i++) {
            if (Data.getCategories().get(i).getParent_id() == globalParentId) {
                Category cat = new Category(
                        Data.getCategories().get(i).getId(),
                        Data.getCategories().get(i).getName(),
                        Data.getCategories().get(i).getPhoto(),
                        Data.getCategories().get(i).getParent_id(),
                        Data.getCategories().get(i).getType()
                );
                categories.add(cat);
            }
        }
        return categories;
    }
}
