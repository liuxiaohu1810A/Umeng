package com.example.umeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks, View.OnClickListener {

    private int REQUEST_FILE_CODE = 99;
    /**
     * 带面板分享
     */
    private Button mBt;
    /**
     * 不带面板分享
     */
    private Button mBt2;
    /**
     * 第三方登录
     */
    private Button mBt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        String[] permissions = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
        // 判断有没有这些权限
        if (EasyPermissions.hasPermissions(this, permissions)) {
            initView();
        } else {

            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, REQUEST_FILE_CODE, permissions)
                            .setRationale("请确认相关权限！！")
                            .setPositiveButtonText("ok")
                            .setNegativeButtonText("cancal")
//                            .setTheme(R.style.my_fancy_style)
                            .build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private static final String TAG = "MainActivity";

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        initView();
        Log.d(TAG, "onPermissionsGranted: ");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied: ");
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.d(TAG, "onRationaleAccepted: ");
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        Log.d(TAG, "onRationaleDenied: ");
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
        mBt3 = (Button) findViewById(R.id.bt3);
        mBt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                fengxiang1();
                break;
            case R.id.bt2:
                fengxiang2();
                break;
            case R.id.bt3:
                login();
                break;
        }
    }
    //第三方登录
    private void login() {
        UMShareAPI.get(this).getPlatformInfo( this, SHARE_MEDIA.SINA, new UMAuthListener(){

            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                for (Map.Entry<String,String> entry: map.entrySet()){

                    Log.d(TAG, "onComplete: "+entry.getKey()+"--value="+entry.getValue());

                }

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                Log.d(TAG, "onError: ");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

                Log.d(TAG, "onCancel: ");
            }
        });
    }

    //不带面板分享
    private void fengxiang2() {
        new ShareAction(MainActivity.this)
                .setPlatform(SHARE_MEDIA.SINA)//传入平台
                .withText("33333333333")//分享内容
                .setCallback(new MyUMShareListener())//回调监听器
                .share();
    }
    //带面板分享
    private void fengxiang1() {
        UMImage image = new UMImage(this, "https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg");//网络图片


        new ShareAction(MainActivity.this)
                .withText("hello")
                .withMedia(image)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(new MyUMShareListener()).open();
    }

    class MyUMShareListener implements UMShareListener {

        @Override
        public void onStart(SHARE_MEDIA share_media) {

            Log.d(TAG, "onStart: ");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {

            Log.d(TAG, "onResult: ");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

            Log.d(TAG, "onError: ");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

            Log.d(TAG, "onCancel: ");
        }
    }
}
