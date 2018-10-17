package com.ivlup.profse.fragment.useful;

import android.app.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.widget.Toast;

import com.ivlup.profse.R;
import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.backend.Category;
import com.ivlup.profse.fragment.FragmentCategoryCards;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.Item;
import com.xwray.groupie.databinding.BindableItem;
import com.ivlup.profse.databinding.CategoryItemBinding;
import java.util.ArrayList;



public class CategoryItem extends BindableItem<CategoryItemBinding> {
private Category category;
private Context context;

    public CategoryItem(Category category) {
        this.category = category;
    }

    @Override
    public void bind(@NonNull final CategoryItemBinding viewBinding, int position) {
        viewBinding.categoryName.setText(category.getName());
        Picasso.get().load(category.getPhoto()).into(viewBinding.categoryAvatar);


        viewBinding.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentCategoryCards.globalParentId = category.getId();
                Toast.makeText(v.getContext() ,FragmentCategoryCards.globalParentId+""+FragmentCategoryCards.newId,Toast.LENGTH_SHORT).show();

                ArrayList<Item> items = new ArrayList<>();
                FragmentCategoryCards. currentCategories = FragmentCategoryCards.fetchCategories(FragmentCategoryCards.currentCategories);
                for ( int i = 0; i <FragmentCategoryCards.currentCategories.size(); i++) {

                    Category as = FragmentCategoryCards.currentCategories.get(i);

                        items.add(new CategoryItem(as));
                }

                FragmentCategoryCards.adapter.update(items);
                //viewBinding.categoryName.setText(category.getName());
                //Picasso.get().load(category.getPhoto()).into(viewBinding.categoryAvatar);


            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.category_item;
    }

}
