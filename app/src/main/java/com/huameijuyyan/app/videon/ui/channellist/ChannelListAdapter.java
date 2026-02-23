package com.huameijuyyan.app.videon.ui.channellist;

import android.content.Context;
import androidx.databinding.ViewDataBinding;
import android.view.ViewGroup;
import com.huameijuyyan.app.util.NetworkURL;
import com.huameijuyyan.app.util.adapterUtil.ProjectBaseAdapter;
import com.huameijuyyan.app.util.helper.ResoulationConverter;
import com.huameijuyyan.app.R;
import com.huameijuyyan.app.videon.data.local.commondatalistresponse.Datum;
import com.huameijuyyan.app.databinding.ItemChannelListBinding;
import com.huameijuyyan.app.videon.ui.home.UICommunicator;


public class ChannelListAdapter  extends ProjectBaseAdapter<Datum> {

    private Context mContext;
    private UICommunicator uiCommunicator;
    ChannelListAdapter(Context context, UICommunicator uiCommunicator){
        mContext=context;
        this.uiCommunicator=uiCommunicator;
    }

    @Override
    public boolean isEqual(Datum left, Datum right) {
        return false;
    }

    @Override
    public BaseAdapterViewHolder<Datum> newViewHolder(ViewGroup parent, int viewType) {
        return new ChannelListAdapter.ChannelListViewHolder(inflate(parent, R.layout.item_channel_list));
    }


    private class ChannelListViewHolder extends BaseAdapterViewHolder<Datum>{

        private ItemChannelListBinding mBinding;
        ChannelListViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            mBinding = (ItemChannelListBinding) viewDataBinding;
        }

        @Override
        public void bind(Datum item) {
            mBinding.textViewChannelName.setText(item.getTitle());
            String imageUrl= NetworkURL.imageEndPointURL+item.getImageName();
            ViewGroup.LayoutParams params=mBinding.roundImageViewChannel.getLayoutParams();
            Integer[] reqHeight= ResoulationConverter.ConvertResoulationHeight(mContext,item.getImageResolution(),(float)params.width);
            uiCommunicator.LoadImage(mBinding.roundImageViewChannel,mBinding.progressBarCircular,imageUrl,reqHeight);
        }
    }
}
