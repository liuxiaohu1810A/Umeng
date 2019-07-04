package com.example.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class baseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5d1c57864ca3575bcc001504"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        //appkey
        PlatformConfig.setSinaWeibo("999941927", "34dcc0acda258e95375f255943f8eb05","http://sns.whalecloud.com");

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

}
