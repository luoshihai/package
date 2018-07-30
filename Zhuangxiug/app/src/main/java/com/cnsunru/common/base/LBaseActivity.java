package com.cnsunru.common.base;

/**
 * @时间: 2017/5/19
 * @功能描述:
 */

import com.cnsunru.common.CommonIntent;
import com.cnsunru.common.intent.IntentPoxy;
import com.cnsunru.common.intent.StartIntent;
import com.cnsunru.common.quest.Config;

import org.greenrobot.eventbus.EventBus;

/**
 * 预留后面会增加的空间
 */

public class LBaseActivity extends TranslucentActivity {
   protected StartIntent startIntent= IntentPoxy.getProxyInstance();
    /**
     * 启用eventBus,不用手动关闭
     */
    public void initEventBus(){
        EventBus.getDefault().register(this);
    }

    @Override
    protected boolean isStatusContentDark() {
        return true;
    }

    @Override
    protected boolean isTranslucent() {
        return true;
    }

    @Override
    protected void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
