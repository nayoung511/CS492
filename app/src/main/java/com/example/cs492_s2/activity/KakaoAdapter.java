//package com.example.cs492_s2.activity;
//
//import android.app.Application;
//import android.content.Context;
//
//import com.kakao.auth.IApplicationConfig;
//import com.kakao.auth.KakaoSDK;
//
//public class KakaoAdapter extends Application {
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // SDK 초기화
//        KakaoSDK.init(new KakaoAdapter() {
//
//            @Override
//            public IApplicationConfig getApplicationConfig() {
//                return new IApplicationConfig() {
//                    @Override
//                    public Context getApplicationContext() {
//                        return KakaoAdapter.this;
//                    }
//                };
//            }
//        });
//    }
//
//}