package com.example.umeng.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.umeng.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WxEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_entry);
    }
}
