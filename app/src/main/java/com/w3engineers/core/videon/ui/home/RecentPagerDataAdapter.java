package com.w3engineers.core.videon.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.w3engineers.core.util.NetworkURL;
import com.w3engineers.core.util.helper.AppConstants;
import com.w3engineers.core.util.helper.ResoulationConverter;
import com.w3engineers.core.videon.R;
import com.w3engineers.core.videon.data.local.commondatalistresponse.Datum;
import com.w3engineers.core.videon.databinding.ItemMostRecentVideoBinding;


public class RecentPagerDataAdapter extends PagedListAdapter<Datum, RecentPagerDataAdapter.RecentViewHolder> {
    private DataItemClickListener mItemClickListener;
    private Context mContext;

    public RecentPagerDataAdapter(DataItemClickListener listener, Context context) {
        super(Datum.callback);
        this.mItemClickListener = listener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemMostRecentVideoBinding itemMerchantRowBinding = DataBindingUtil.inflate(inflater, R.layout.item_most_recent_video, viewGroup, false);
        return new RecentViewHolder(itemMerchantRowBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder recentViewHolder, int i) {
        recentViewHolder.bindTo(getItem(i));
    }

    class RecentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemMostRecentVideoBinding mItemRecentBinding;

        public RecentViewHolder(ItemMostRecentVideoBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mItemRecentBinding = viewDataBinding;
        }

        void bindTo(Datum item) {

            mItemRecentBinding.constraintLayoutLiveTv.setOnClickListener(this);

            mItemRecentBinding.textViewMostRecentVideoTitle.setText("" + item.getTitle());
            mItemRecentBinding.progressBarCircular.setProgress(AppConstants.progress);
            mItemRecentBinding.textViewCatName.setText("" + item.getCategory());
            String imageUrl = NetworkURL.imageEndPointURL + item.getImageName();
            ViewGroup.LayoutParams params = mItemRecentBinding.roundImageViewLiveTv.getLayoutParams();
            Integer[] reqHeight = ResoulationConverter.ConvertResoulationWidth(mContext, item.getImageResolution(), (float) params.width);
            LoadImage(mItemRecentBinding.roundImageViewLiveTv, mItemRecentBinding.progressBarCircular, imageUrl, reqHeight);


            //  Glider.showWithPlaceholder(itemMerchantRowBinding.imageThumb.getContext(), Constants.Remote.BASE_URL + item.getMerchantImage(), itemMerchantRowBinding.imageThumb, R.drawable.ic_placeholder_gallery);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onDataItemClick(v, getItem(getAdapterPosition()));
        }
    }

    /**
     * load image
     *
     * @param imageView   imageView
     * @param progressBar progressBar
     * @param imageLink   imageLink
     * @param heightWidth heightWidth
     */
    public void LoadImage(ImageView imageView, ProgressBar progressBar, String imageLink, Integer[] heightWidth) {

        try {
            RequestOptions requestOptions = new RequestOptions();

            requestOptions = requestOptions.transforms(new FitCenter(), new RoundedCorners(5)).override(heightWidth[0], heightWidth[1]).error(R.drawable.default_img)
                    .placeholder(R.drawable.default_img); // resizes the image to these dimensions (in pixel)

            Glide.with(mContext)
                    .load(imageLink)

                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .apply(requestOptions)
                    .into(imageView);
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
