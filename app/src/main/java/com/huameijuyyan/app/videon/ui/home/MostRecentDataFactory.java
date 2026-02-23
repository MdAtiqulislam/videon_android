package com.huameijuyyan.app.videon.ui.home;


import androidx.paging.DataSource;

import com.huameijuyyan.app.videon.data.remote.home.RemoteVideoApiInterface;

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
