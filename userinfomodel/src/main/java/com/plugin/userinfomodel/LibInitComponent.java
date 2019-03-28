package com.plugin.userinfomodel;

import android.util.Log;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.billy.cc.core.component.IMainThread;
import com.plugin.common.utiles.ToastUtils;

/**
 * 项目名称：v4.2.0
 * 类描述： 提供初始化三方lib,在主APP和debugApplication中都会调用
 * 创建人：Administrator
 * 创建时间：2019/3/7 13:30
 * 修改人：Administrator
 * 修改时间：2019/3/7 13:30
 * 修改备注：
 * 联系方式：906514731@qq.com
 *
 * @author Administrator
 */
public class LibInitComponent implements IComponent , IMainThread {
  @Override
  public String getName() {
    //指定组件的名称
    return "LibInitComponent";
  }

  @Override
  public boolean onCall(CC cc) {
    ToastUtils.showToast("lib初始化完毕", cc.getContext());
    CC.sendCCResult(cc.getCallId(), CCResult.success());
    Log.e("LibInitComponent", "lib初始化完毕");
    //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
    return false;
  }

  /**
   * 标记改回调在主线程
   * @param actionName
   * @param cc
   * @return
   */
  @Override public Boolean shouldActionRunOnMainThread(String actionName, CC cc) {
    return true;
  }
}

