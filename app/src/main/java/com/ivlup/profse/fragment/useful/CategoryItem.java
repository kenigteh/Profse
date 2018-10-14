package com.ivlup.profse.fragment.useful;

import android.support.annotation.NonNull;

import com.ivlup.profse.R;
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
    public void bind(@NonNull CategoryItemBinding viewBinding, int position) {
        viewBinding.categoryName.setText(category.getName());
        Picasso.get().load(category.getPhoto()).into(viewBinding.categoryAvatar);

    }

    @Override
    public int getLayout() {
        return R.layout.category_item;
    }

}
