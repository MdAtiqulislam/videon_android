package com.huameijuyyan.app.videon.ui.privacypolicy;

import android.content.Context;
import android.content.Intent;
import androidx.core.text.HtmlCompat;
import android.view.MenuItem;

import com.huameijuyyan.app.R;
import com.huameijuyyan.app.databinding.ActivityPrivacyPolicyBinding;
import com.w3engineers.ext.strom.application.ui.base.BaseActivity;



public class PrivacyPolicyActivity extends BaseActivity {

    private ActivityPrivacyPolicyBinding mBinding;

    public static void runActivity(Context context) {
        Intent intent = new Intent(context, PrivacyPolicyActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privacy_policy;
    }

    @Override
    protected void startUI() {
       mBinding=(ActivityPrivacyPolicyBinding) getViewDataBinding();
       setSupportActionBar(mBinding.toolbarHome);
       String string=getResources().getString(R.string.privacy_policy_desc);
       mBinding.contentText.setText(HtmlCompat.fromHtml(string, 0));
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                finish();
                break;

        }
        return true;
    }

}
