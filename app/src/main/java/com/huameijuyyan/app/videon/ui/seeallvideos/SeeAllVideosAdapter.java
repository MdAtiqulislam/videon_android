package com.huameijuyyan.app.videon.ui.seeallvideos;

import android.content.Context;
import androidx.databinding.ViewDataBinding;

import android.view.ViewGroup;

import com.huameijuyyan.app.util.CheckVideoTypeUtil;
import com.huameijuyyan.app.util.NetworkURL;
import com.huameijuyyan.app.util.adapterUtil.ProjectBaseAdapter;
import com.huameijuyyan.app.util.helper.AppConstants;
import com.huameijuyyan.app.util.helper.ResoulationConverter;
import com.huameijuyyan.app.util.helper.TimeConverter;
import com.huameijuyyan.app.R;
import com.huameijuyyan.app.videon.data.local.commondatalistresponse.Datum;
import com.huameijuyyan.app.databinding.ItemSeeAllVideosBinding;
import com.huameijuyyan.app.videon.ui.home.UICommunicator;

public class SeeAllVideosAdapter extends ProjectBaseAdapter<Datum> {

    private Context mContext;
    private UICommunicator uiCommunicator;

    SeeAllVideosAdapter(Context context, UICommunicator uiCommunicator) {
        mContext = context;
        this.uiCommunicator = uiCommunicator;
    }


    @Override
    public boolean isEqual(Datum left, Datum right) {
        return false;
    }

    @Override
    public BaseAdapterViewHolder<Datum> newViewHolder(ViewGroup parent, int viewType) {
        return new SeeAllViewHolder(inflate(parent, R.layout.item_see_all_videos));
    }

    class SeeAllViewHolder extends BaseAdapterViewHolder<Datum> {


        private ItemSeeAllVideosBinding mItemSeeAllVideosBinding;

        SeeAllViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            mItemSeeAllVideosBinding = (ItemSeeAllVideosBinding) viewDataBinding;
        }

        @Override
        public void bind(Datum item) {
            mItemSeeAllVideosBinding.progressBarCircular.setProgress(AppConstants.progress);

            mItemSeeAllVideosBinding.textViewViewsCount.setText("" + item.getViewCount());
            mItemSeeAllVideosBinding.textViewUploadTime.setText(""+ item.getCreated());
            mItemSeeAllVideosBinding.textViewVideoTitle.setText(item.getTitle());
            if (item.getCategory()!=null){
                mItemSeeAllVideosBinding.textViewCatName.setText(""+item.getCategory());
            }
            if (item.getDuration() !=null){
                mItemSeeAllVideosBinding.textViewDuration.setText(TimeConverter.ConvertSecondsToHourMinute(Integer.parseInt(item.getDuration())));
            }

            CheckVideoTypeUtil.checkVideoType(item);

            String imageUrl = NetworkURL.imageEndPointURL + item.getImageName();
            ViewGroup.LayoutParams params = mItemSeeAllVideosBinding.roundImageViewVideo.getLayoutParams();
            Integer[] reqHeight = ResoulationConverter.ConvertResoulationHeight(mContext, item.getImageResolution(), (float) params.width);
            uiCommunicator.LoadImage(mItemSeeAllVideosBinding.roundImageViewVideo, mItemSeeAllVideosBinding.progressBarCircular, imageUrl, reqHeight);


        }

    }
}
