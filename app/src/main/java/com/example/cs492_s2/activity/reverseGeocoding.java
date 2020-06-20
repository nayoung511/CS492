package com.example.cs492_s2.activity;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

public class reverseGeocoding extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    EditText editText;

    GeoVariable geovariable = new GeoVariable();  // 클래스 변수 사용 위해
    Geocoder geocoder = new Geocoder(this); // 역지오코딩 하기 위해
    EditText onWhere; // 현재위치 출력위해

    double latitude, longitude; // 위도, 경도 전역변수


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        latitude = geovariable.getLatitude(); // 위도 경도 클래스변수에서 가져옴
        longitude = geovariable.getLongitude();

        geocoder = new Geocoder(this);

        editText = findViewById(R.id.search_hold);
        getCurrentLocation();

//        findViewById(R.id.btn_gps).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(ContextCompat.checkSelfPermission(
//                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
//                )!= PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(
//                            reverseGeocoding.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                            REQUEST_CODE_LOCATION_PERMISSION
//                    );
//                }else{
//                    getCurrentLocation();
//                    reverseCoding();
//                }
//            }
//        });
    }


    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            } else {
                startToast("Permission denied");
            }
        }
    }

    private void getCurrentLocation() {

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(reverseGeocoding.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){

                    @Override
                    public void onLocationResult(LocationResult locationResult){
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(reverseGeocoding.this)
                                .removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0){
                            int lastestLocationIndex = locationResult.getLocations().size() -1;
                            latitude =
                                    locationResult.getLocations().get(lastestLocationIndex).getLatitude();
                            longitude =
                                    locationResult.getLocations().get(lastestLocationIndex).getLongitude();

//                            editText.setText(
//                                    String.format(
//                                            "Latitude: %s\nLongitude: %s",latitude, longitude
//                                    )
//                            );
                        }
                    }
                }, Looper.getMainLooper());
    }




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location);
//
//
//        latitude = geovariable.getLatitude(); // 위도 경도 클래스변수에서 가져옴
//        longitude = geovariable.getLongitude();
//
//        geocoder = new Geocoder(this);  // 역지오코딩 하기 위해
//        onWhere = (EditText) findViewById(R.id.search_hold);
//
//        reverseCoding(); // 역지오코딩 주소값 onWhere 텍스트뷰에 대입
//
//    }


    public void reverseCoding(){ // 위도 경도 넣어가지구 역지오코딩 주소값 뽑아낸다
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(latitude, longitude, 10); // 위도, 경도, 얻어올 값의 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size()==0) {
                onWhere.setText("해당되는 주소 정보는 없습니다");
            } else {
                // onWhere.setText(list.get(0).toString()); 원래 통으로 나오는 주소값 문자열

                // 문자열을 자르자!
                String cut[] = list.get(0).toString().split(" ");
                for(int i=0; i<cut.length; i++){
                    System.out.println("cut["+i+"] : " + cut[i]);
                } // cut[0] : Address[addressLines=[0:"대한민국
                // cut[1] : 서울특별시  cut[2] : 송파구  cut[3] : 오금동
                // cut[4] : cut[4] : 41-26"],feature=41-26,admin=null ~~~~
                String a = cut[1] + " " + cut[2] + " " + cut[3];
                onWhere.setText(a); // 내가 원하는 구의 값을 뽑아내 출력
            }
        }
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}