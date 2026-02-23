package com.huameijuyyan.app.videon.ui.aboutus;

import android.content.Context;
import android.content.Intent;
import androidx.core.text.HtmlCompat;

import com.huameijuyyan.app.R;
import com.huameijuyyan.app.databinding.ActivityAboutUsBinding;
import com.w3engineers.ext.strom.application.ui.base.BaseActivity;


public class AboutUsActivity extends BaseActivity {

    private ActivityAboutUsBinding mBinding;

    /*
    * Run activity about us
    * */
    public static void runActivity(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        runCurrentActivity(context, intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void startUI() {
        mBinding=(ActivityAboutUsBinding)getViewDataBinding();
        setSupportActionBar(mBinding.toolbarHome);
        String string=getResources().getString(R.string.text_dummy_about_us);
        mBinding.aboutUs.setText(HtmlCompat.fromHtml(string, 0));

    }
}
