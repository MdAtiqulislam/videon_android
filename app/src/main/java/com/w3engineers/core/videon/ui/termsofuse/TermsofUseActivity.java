package com.w3engineers.core.videon.ui.termsofuse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.core.text.HtmlCompat;

import com.w3engineers.core.util.helper.PreferenceKey;
import com.w3engineers.core.util.helper.SharedPref;
import com.w3engineers.core.videon.R;

import com.w3engineers.core.videon.data.local.Enums;
import com.w3engineers.core.videon.databinding.ActivityTermsUseBinding;
import com.w3engineers.core.videon.ui.home.HomeActivity;
import com.w3engineers.ext.strom.application.ui.base.BaseActivity;

public class TermsofUseActivity extends BaseActivity {

    private ActivityTermsUseBinding mBinding;
    private SharedPreferences preferences;

    public static void runActivity(Context context) {
        Intent intent = new Intent(context, TermsofUseActivity.class);
        runCurrentActivity(context, intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_terms_use;
    }

    @Override
    protected void startUI() {
        SharedPref.init(getBaseContext());
        mBinding=(ActivityTermsUseBinding) getViewDataBinding();
        setSupportActionBar(mBinding.toolbarHome);
        String string=getResources().getString(R.string.privacy_policy_desc);
        mBinding.contentText.setText(HtmlCompat.fromHtml(string, 0));
        if (SharedPref.readBoolean(PreferenceKey.CHECKED_TERMS)) {
            gotoHomePage();
        }
        mBinding.buttonNext.setOnClickListener(this);

        mBinding.checkBoxTermsOfUse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mBinding.buttonNext.setEnabled(true);
                mBinding.buttonNext.setBackgroundResource(R.drawable.ractangular_gradient);
                mBinding.buttonNext.setTextColor(getResources().getColor(R.color.white));
            } else {
                mBinding.buttonNext.setEnabled(false);
                mBinding.buttonNext.setBackgroundResource(R.drawable.ractangular_white);
                mBinding.buttonNext.setTextColor(getResources().getColor(R.color.new_user_button_color));
            }
        });
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        finish();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.button_next) {
            SharedPref.write(PreferenceKey.CHECKED_TERMS, true);
            gotoHomePage();
        }
    }

    private void gotoHomePage() {
        startActivity(new Intent(TermsofUseActivity.this, HomeActivity.class));
        finish();
    }


}
