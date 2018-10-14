package com.ivlup.profse.fragment.useful;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.ivlup.profse.R;
import com.ivlup.profse.fragment.FragmentCategoryCards;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.databinding.BindableItem;
import com.ivlup.profse.databinding.CategoryItemBinding;
import java.util.ArrayList;

public class CategoryItem extends BindableItem<CategoryItemBinding> {
private AssetCategory category;

    public CategoryItem(AssetCategory category) {
        this.category = category;
    }

    @Override
    public void bind(@NonNull final CategoryItemBinding viewBinding, int position) {
        viewBinding.categoryName.setText(category.getName());
        Picasso.get().load(category.getPhoto()).into(viewBinding.categoryAvatar);
        viewBinding.cardCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext() ,FragmentCategoryCards.globalParentId+""+FragmentCategoryCards.newId,Toast.LENGTH_SHORT).show();
                FragmentCategoryCards.globalParentId = category.getId();
                FragmentCategoryCards.adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.category_item;
    }

}
