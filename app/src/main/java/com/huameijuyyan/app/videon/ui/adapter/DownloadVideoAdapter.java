package com.huameijuyyan.app.videon.ui.adapter;


import androidx.databinding.ViewDataBinding;

import android.view.View;
import android.view.ViewGroup;

import com.huameijuyyan.app.util.adapterUtil.ProjectBaseAdapter;

import com.huameijuyyan.app.R;
import com.huameijuyyan.app.videon.data.local.adaptermodel.ItemVideoDownload;
import com.huameijuyyan.app.databinding.ItemDownloadVideoBinding;


/**
 * purpose: Display downloadable video items with available format
 */

public class DownloadVideoAdapter extends ProjectBaseAdapter<ItemVideoDownload> {

    public OnDownloadClick downloadClick;
    @Override
    public boolean isEqual(ItemVideoDownload left, ItemVideoDownload right) {
        return false;
    }

    @Override
    public BaseAdapterViewHolder<ItemVideoDownload> newViewHolder(ViewGroup parent, int viewType) {

            return new CommoniewHolder(inflate(parent, R.layout.item_download_video));


    }


    private class CommoniewHolder extends BaseAdapterViewHolder<ItemVideoDownload>{

        private ItemDownloadVideoBinding mItemDownloadVideoBinding;

        CommoniewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding);

            mItemDownloadVideoBinding = (ItemDownloadVideoBinding) viewDataBinding;
        }

        @Override
        public void bind(ItemVideoDownload item) {
                mItemDownloadVideoBinding.textVideoTitle.setText(""+item.getTitle());
                mItemDownloadVideoBinding.textVideoSize.setText(""+item.getFormat());
                mItemDownloadVideoBinding.btnDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(downloadClick!=null)
                        {
                            downloadClick.onItemDownloadClick(item);
                        }
                    }
                });
            }



    }

    public void setDownloadClick(OnDownloadClick downloadClick) {
        this.downloadClick = downloadClick;
    }

    public interface OnDownloadClick
    {
        public void onItemDownloadClick(ItemVideoDownload item);
    }

}
