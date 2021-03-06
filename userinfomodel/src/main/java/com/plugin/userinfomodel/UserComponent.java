package com.plugin.userinfomodel;

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
 *
 * @author Administrator
 */
public class UserComponent implements IComponent {
  @Override
  public String getName() {
    //指定组件的名称
    return "UserComponent";
  }

  @Override
  public boolean onCall(CC cc) {
    String actionName = cc.getActionName();
    switch (actionName) {
      //响应actionName为"showActivity"的组件调用
      case "showUserActivity":
        //跳转到用户信息界面
        CCUtil.navigateTo(cc, UserActivity.class);
        CC.sendCCResult(cc.getCallId(), CCResult.success());
        //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
        return false;
      default:
        //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
        CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
        return false;
    }
  }
}

