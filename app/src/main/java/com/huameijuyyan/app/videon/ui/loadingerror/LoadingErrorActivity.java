package com.huameijuyyan.app.videon.ui.loadingerror;


import com.huameijuyyan.app.R;
import com.huameijuyyan.app.databinding.ActivityLoadingErrorBinding;
import com.w3engineers.ext.strom.application.ui.base.BaseActivity;

public class LoadingErrorActivity extends BaseActivity {
    private ActivityLoadingErrorBinding mBinding;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading_error;
    }

    @Override
    protected void startUI() {
        mBinding=(ActivityLoadingErrorBinding)getViewDataBinding();
    }
}
