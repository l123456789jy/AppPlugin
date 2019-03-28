package com.demo.appplugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.plugin.common.BaseActivity;
import com.plugin.common.manger.UserStateManager;
import com.plugin.common.utiles.ToastUtils;

/**
 * 主APP
 *
 * @author ljy
 */
public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.bt_open).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        openLogin();
      }
    });


    findViewById(R.id.bt_openuser).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        openUser();
      }
    });
  }

  /**
   * 打开用户信息界面
   */
  private void openUser() {
    String callId = CC.obtainBuilder("UserComponent")
        .setActionName("showUserActivity")
        .addParam("haha", "我是宿主传递过来的")
        .build()
        .callAsyncCallbackOnMainThread(new IComponentCallback() {
          @Override
          public void onResult(CC cc, CCResult result) {
            if (result.isSuccess()) {
              ToastUtils.showToast("我收到结果" +result.toString(),cc.getContext());
            }
          }
        });
  }

  /**
   * 打开登录界面
   */
  private void openLogin() {
    //异步得到结果跳转到指定的界面,这个LongingComponent就是登录组件暴露出来的通信的接口
    String callId = CC.obtainBuilder("LongingComponent")
        .setActionName("showActivity")
        .addParam("haha", "我是宿主传递过来的")
        .build()
        .callAsyncCallbackOnMainThread(new IComponentCallback() {
          @Override
          public void onResult(CC cc, CCResult result) {
            if (result.isSuccess()) {
              ToastUtils.showToast(
                  "我收到结果" + result.getDataItem(UserStateManager.KEY_USER).toString(),
                  MainActivity.this);
            }
          }
        });
  }
}
