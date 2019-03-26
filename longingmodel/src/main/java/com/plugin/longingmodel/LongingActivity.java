package com.plugin.longingmodel;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.billy.cc.core.component.CCUtil;
import com.plugin.common.BaseActivity;
import com.plugin.common.utiles.ToastUtils;

/**
 * 组件登录
 * @author ljy
 */
public class LongingActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_debug);
    String url = CCUtil.getNavigateParam(this, "haha", null);
    if (!TextUtils.isEmpty(url)) {
      ((TextView)findViewById(R.id.tv)).setText(url);
    }

    findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        ToastUtils.showToast("公共common库调用成功",LongingActivity.this);
      }
    });

  }
}
