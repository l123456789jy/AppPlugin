package com.plugin.longingmodel;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.plugin.common.BaseActivity;
import com.plugin.common.utiles.ToastUtils;
import com.plugin.common.manger.UserStateManager;
import com.plugin.common.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组件登录
 *
 * @author ljy
 */
public class LongingActivity extends BaseActivity implements View.OnClickListener {
  private EditText editText;
  private String callId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   /* setContentView(R.layout.activity_debug);
    String url = CCUtil.getNavigateParam(this, "haha", null);
    if (!TextUtils.isEmpty(url)) {
      ((TextView)findViewById(R.id.tv)).setText(url);
    }

    findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        ToastUtils.showToast("公共common库调用成功",LongingActivity.this);
      }
    });*/
    callId = CCUtil.getNavigateCallId(this);
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setPadding(20, 20, 20, 20);
    LinearLayout.LayoutParams params =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    editText = new EditText(this);
    editText.setText("Lazy");
    layout.addView(editText, params);
    Button button = new Button(this);
    button.setOnClickListener(this);
    layout.addView(button, params);
    setContentView(layout);
  }

  @Override
  public void onClick(View v) {
    String username = editText.getText().toString().trim();
    if (TextUtils.isEmpty(username)) {
      //仅业务提示，登录操作并未结束
      ToastUtils.showToast("请输入昵称", LongingActivity.this);
    } else {
      UserStateManager.setLoginUser(new User(1, username));
      //返回登录结果
      sendLoginResult();
      finish();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    //为确保一定会调用CC.sendCCResult，在onDestroy中再次确认是否已返回登录结果
    sendLoginResult();
  }



  private void sendLoginResult() {
    //判断是否为CC调用打开本页面
    if (callId != null) {
      CCResult result;
      if (UserStateManager.getLoginUser() == null) {
        result = CCResult.error("login canceled");
      } else {
        //User登录成功返回用户登录数据
        result = CCResult.success(UserStateManager.KEY_USER, UserStateManager.getLoginUser());
      }
      //为确保不管登录成功与否都会调用CC.sendCCResult，在onDestroy方法中调用
      CC.sendCCResult(callId, result);
    }
  }
}
