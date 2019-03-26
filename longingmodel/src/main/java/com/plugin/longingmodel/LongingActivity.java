package com.plugin.longingmodel;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.billy.cc.core.component.CCUtil;

/**
 * 组件登录
 * @author ljy
 */
public class LongingActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_debug);
    String url = CCUtil.getNavigateParam(this, "haha", null);
    if (!TextUtils.isEmpty(url)) {
      ((TextView)findViewById(R.id.tv)).setText(url);
    }

  }
}
