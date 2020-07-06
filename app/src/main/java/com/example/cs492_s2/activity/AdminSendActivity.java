package com.example.cs492_s2.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

public class AdminSendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_adminsend);


        String templateId = "31032";
        Map<String, String> templateArgs = new HashMap<>();
        templateArgs.put("template_arg1", "value1");
        templateArgs.put("template_arg2", "value2");


        // 커스텀 템플릿으로 카카오링크 보내기
        KakaoLinkService.getInstance()
                .sendCustom(this, templateId, templateArgs, null, new ResponseCallback<KakaoLinkResponse>() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "카카오링크 보내기 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(KakaoLinkResponse result) {
                        Log.i("KAKAO_API", "카카오링크 보내기 성공");

                        // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                        Log.w("KAKAO_API", "warning messages: " + result.getWarningMsg());
                        Log.w("KAKAO_API", "argument messages: " + result.getArgumentMsg());
                    }
                });

        //콜백 파라미터 설정

        // 링크 콜백에서 함께 수신할 커스텀 파라미터를 설정합니다.
        Map<String, String> serverCallbackArgs = new HashMap<>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        // 보내기 메소드 호출 시 serverCallbackArgs 파라미터를 추가합니다.
        KakaoLinkService.getInstance()
                .sendCustom(this, "12345", null, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "카카오링크 보내기 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(KakaoLinkResponse result) {
                        Log.i("KAKAO_API", "카카오링크 보내기 성공");
                    }
                });
    }



}
