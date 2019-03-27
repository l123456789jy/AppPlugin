package com.plugin.longingmodel.processor;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.CCUtil;
import com.plugin.longingmodel.LongingActivity;
import com.plugin.longingmodel.LongingComponent;
import com.plugin.common.manger.UserStateManager;

/**
 * 如果某个组件提供的服务比较多
 * 会导致onCall方法中写太多的actionName判断(if-else / switch-case)
 * 而且组件类中的代码会比较膨胀
 *
 * 定义一个接口，每个实现类提供一个actionName对应的组件服务
 * 利用cc-register的自动注册功能，将这个接口的实现类自动注册到组件类中
 * 当组件被调用时，根据actionName调用对应的IActionProcessor实现类对象
 * 这个不是CC主动调用需要我们自己在CC中注册，主要是为了减少Commponent的逻辑
 * @see LongingComponent   进行注册和调用
 *
 */
public class CheckAndLoginProcessor implements IActionProcessor {

    @Override
    public String getActionName() {
        return "checkAndLogin";
    }

    @Override
    public boolean onActionCall(CC cc) {
        if (UserStateManager.getLoginUser() != null) {
            //already login, return username
            CCResult result = CCResult.success(UserStateManager.KEY_USER, UserStateManager.getLoginUser());
            CC.sendCCResult(cc.getCallId(), result);
            return false;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CC.getApplication(), "please login first!", Toast.LENGTH_SHORT).show();
            }
        });
        CCUtil.navigateTo(cc, LongingActivity.class);
        //不立即调用CC.sendCCResult,返回true
        return true;
    }
}
