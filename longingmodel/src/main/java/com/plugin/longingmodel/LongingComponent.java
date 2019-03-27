package com.plugin.longingmodel;

import android.util.Log;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.billy.cc.core.component.IComponent;
import com.billy.cc.core.component.IComponentCallback;
import com.plugin.common.manger.UserStateManager;
import com.plugin.common.model.User;
import com.plugin.common.utiles.ToastUtils;

/**
 * 项目名称：v4.2.0
 * 类描述： 提供当前组件与主App的交互
 * 创建人：Administrator
 * 创建时间：2019/3/7 13:30
 * 修改人：Administrator
 * 修改时间：2019/3/7 13:30
 * 修改备注：
 * 联系方式：906514731@qq.com
 * @author Administrator
 */
public class LongingComponent implements IComponent {
  @Override
  public String getName() {
    //指定组件的名称
    return "LongingComponent";
  }

  @Override
  public boolean onCall(CC cc) {
    String actionName = cc.getActionName();
    switch (actionName) {
      case "openLonginActivity" :
        //跳转到已经登录界面
        CCUtil.navigateTo(cc, LongingActivity.class);
        //不立即调用CC.sendCCResult,返回true,等待登录界面返回数据
        return true;
      //响应actionName为"showActivity"的组件调用
      case "showActivity":
        //检查是否已经登录，如果已经登录跳转到已经登录界面
        if (UserStateManager.getLoginUser() != null) {
          //跳转到已经登录界面
          CCUtil.navigateTo(cc, UserActivity.class);
          //返回用户信息
          CCResult result =
              CCResult.success(UserStateManager.KEY_USER, UserStateManager.getLoginUser());
          CC.sendCCResult(cc.getCallId(), result);

          //未登录跳转到登录界面
        } else {
          openLongingActivity(cc);
        }
        //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
        return false;
      default:
        //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
        CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
        return false;
    }
  }

  /**
   * 跳转到登录界面登录
   * @param cc
   */
  private void openLongingActivity(final CC cc) {
    CC.obtainBuilder("LongingComponent")
        .setActionName("openLonginActivity")
        .build()
        .callAsyncCallbackOnMainThread(new IComponentCallback() {
          @Override
          public void onResult(CC loginCC, CCResult result) {
            CCResult ccResult;
            //登录成功
            if (result.isSuccess()) {
              ccResult = CCResult.success();
            //登录取消
            } else {
              ccResult = result;
              ToastUtils.showToast(result.getErrorMessage(),loginCC.getContext());
            }
            Log.e("返回的数据", result.toString() );
            User user = result.getDataItem(UserStateManager.KEY_USER);
            Log.e("登录用户名字", user.getUserName());
            CC.sendCCResult(cc.getCallId(), ccResult);
          }
        });
  }


}

