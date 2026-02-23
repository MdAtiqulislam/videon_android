package com.huameijuyyan.app.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.huameijuyyan.app.util.helper.Constants;
import com.huameijuyyan.app.R;


public class UIHelper {

    /**
     * setting thumbnail url to view
     *
     * @param imageView
     * @param imageUri
     */
    @BindingAdapter("setImageUri")
    public static void setThumbImageUriInView(ImageView imageView, String imageUri) {
        String imageUrl = Constants.ServerUrl.THUMB_IMAGE_URL + imageUri;
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder))
                .into(imageView);
    }
}
