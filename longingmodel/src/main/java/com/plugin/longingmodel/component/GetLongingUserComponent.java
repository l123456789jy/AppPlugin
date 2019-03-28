package com.plugin.longingmodel.component;

import android.util.Log;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.plugin.common.manger.UserStateManager;

/**
 * 项目名称：v4.2.0
 * 类描述：提供各个组件获取用户登录的状态信息
 * 创建人：Administrator
 * 创建时间：2019/3/7 13:30
 * 修改人：Administrator
 * 修改时间：2019/3/7 13:30
 * 修改备注：
 * 联系方式：906514731@qq.com
 *
 * @author Administrator
 */
public class GetLongingUserComponent implements IComponent {
  @Override
  public String getName() {
    //指定组件的名称
    return "getLoginUser";
  }

  @Override
  public boolean onCall(CC cc) {
    Log.e("GetLongingUserComponent", "onCall: ");
    if (UserStateManager.getLoginUser() != null) {
      //already login, return username
      CCResult result =
          CCResult.success(UserStateManager.KEY_USER, UserStateManager.getLoginUser());
      CC.sendCCResult(cc.getCallId(), result);
    } else {
      CC.sendCCResult(cc.getCallId(), CCResult.error("no login user"));
    }
    return false;
  }
}

