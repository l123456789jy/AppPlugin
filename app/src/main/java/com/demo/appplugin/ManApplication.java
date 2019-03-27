package com.demo.appplugin;

import android.app.Application;
import android.util.Log;
import com.billy.cc.core.component.CC;

/**
 * 项目名称：v4.2.0
 * 类描述： 组件的Application,注意这个Application只会在是单独一个APP运行的时候才会执行
 * 创建人：Administrator
 * 创建时间：2019/3/7 11:27
 * 修改人：Administrator
 * 修改时间：2019/3/7 11:27
 * 修改备注：
 * 联系方式：906514731@qq.com
 */
public class ManApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    CC.enableDebug(BuildConfig.DEBUG);
    CC.enableVerboseLog(BuildConfig.DEBUG);
    CC.enableRemoteCC(BuildConfig.DEBUG);
    Log.e("LongingApplication", "onCreate:LongingApplication ");
  }
}
