package com.huameijuyyan.app.videon.ui.favourite;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import android.view.ViewGroup;

import com.huameijuyyan.app.util.CheckVideoTypeUtil;
import com.huameijuyyan.app.util.NetworkURL;
import com.huameijuyyan.app.util.adapterUtil.ProjectBaseAdapter;
import com.huameijuyyan.app.util.helper.ResoulationConverter;
import com.huameijuyyan.app.util.helper.TimeConverter;
import com.huameijuyyan.app.R;
import com.huameijuyyan.app.videon.data.local.commondatalistresponse.Datum;
import com.huameijuyyan.app.databinding.ItemSearchMoviesBinding;
import com.huameijuyyan.app.videon.ui.home.UICommunicator;

public class
FavouriteAdapter extends ProjectBaseAdapter<Datum> {
    private Context context;
    private UICommunicator uiCommunicator;

    public FavouriteAdapter(Context context, UICommunicator uiCommunicator) {
        this.context = context;
        this.uiCommunicator = uiCommunicator;
    }

    @Override
    public boolean isEqual(Datum left, Datum right) {
        return false;
    }

    @Override
    public BaseAdapterViewHolder<Datum> newViewHolder(ViewGroup parent, int viewType) {
        return new FeaturedVideoViewHolder(inflate(parent, R.layout.item_search_movies));
    }


    private class FeaturedVideoViewHolder extends BaseAdapterViewHolder<Datum> {

        private ItemSearchMoviesBinding mBinding;

        FeaturedVideoViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            mBinding = (ItemSearchMoviesBinding) viewDataBinding;
        }

        @Override
        public void bind(Datum item) {
            mBinding.textViewVideoTitle.setText("" + item.getTitle());
            mBinding.textViewCatName.setText("" + item.getCategory());
            mBinding.textViewViewsCount.setText("" + item.getViewCount());
            mBinding.textViewUploadTime.setText("" + item.getCreated());
            mBinding.textViewDuration.setText(TimeConverter.ConvertSecondsToHourMinute(Integer.parseInt(item.getDuration())));

            CheckVideoTypeUtil.checkVideoType(item);

            //image section
            String imageUrl = NetworkURL.imageEndPointURL + item.getImageName();
            ViewGroup.LayoutParams params = mBinding.roundImageViewVideo.getLayoutParams();
            Integer[] reqHeight = ResoulationConverter.ConvertResoulationHeight(context, item.getImageResolution(), (float) params.width);
            uiCommunicator.LoadImage(mBinding.roundImageViewVideo, mBinding.progressBarCircular, imageUrl, reqHeight);
        }


    }
}
