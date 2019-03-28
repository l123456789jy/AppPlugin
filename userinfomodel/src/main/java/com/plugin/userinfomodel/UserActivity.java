package com.plugin.userinfomodel;

import android.app.Activity;
import android.os.Bundle;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.plugin.common.BaseActivity;
import com.plugin.common.manger.UserStateManager;
import com.plugin.common.utiles.ToastUtils;

public class UserActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);
    String callId = CC.obtainBuilder("getLoginUser")
        .build()
        .callAsyncCallbackOnMainThread(new IComponentCallback() {
          @Override
          public void onResult(CC cc, CCResult result) {
            if (result.isSuccess()) {
              ToastUtils.showToast(
                  "我收到结果" + result.toString(),
                  cc.getContext());
            }else {
              ToastUtils.showToast(
                  "我收到结果" +result.getErrorMessage(),
                  cc.getContext());
            }
          }
        });
  }
}
