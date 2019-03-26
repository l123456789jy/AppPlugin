package com.demo.appplugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.bt_open).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //或 异步调用，在子线程执行回调
        String callId = CC.obtainBuilder("ComponentA")
            .setActionName("showActivity")
            .addParam("haha", "我是宿主传递过来的")
            .build()
            .callAsync(new IComponentCallback() {
              @Override
              public void onResult(CC cc, CCResult result) {
                //此onResult在子线程中运行
              }
            });
      }
    });
  }
}
