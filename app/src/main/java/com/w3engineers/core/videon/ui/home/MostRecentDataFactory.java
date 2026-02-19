package com.w3engineers.core.videon.ui.home;


import androidx.paging.DataSource;

import com.w3engineers.core.videon.data.remote.home.RemoteVideoApiInterface;

public class MostRecentDataFactory extends DataSource.Factory  {
    private RemoteVideoApiInterface getDataService;

    public MostRecentDataFactory(RemoteVideoApiInterface getDataService) {
        this.getDataService = getDataService;
    }

    @Override
    public DataSource create() {
        MostRecentDataSource  merchantDataSource = new MostRecentDataSource(getDataService);
        return merchantDataSource;
    }
}
