//package com.example.cs492_s2.activity;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import com.example.cs492_s2.R;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Locale;
//
//public class CurrentLocationActivity extends AppCompatActivity {
//
//    //원래 1 이었
//    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
//    private GpsTracker gpsTracker;
//    private static final int PERMISSIONS_REQUEST_CODE = 100;
//    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
//    Geocoder geocoder;  //역지오코
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if(!checkLocationServicesStatus()){
//            showDialogForLocationServiceSetting();
//        }else{
//            checkRunTimePermission();
//        }
//
//        final EditText editText = (EditText)findViewById(R.id.search_hold);
//        //setContentView(R.layout.activity_current_location);
//        gpsTracker = new GpsTracker(CurrentLocationActivity.this);
//        double latitude = gpsTracker.getLatitude();
//        // 위도
//        double longitude = gpsTracker.getLongitude();
//        //경도 //필요시
//        String address = getCurrentAddress(latitude, longitude);
//        //대한민국 서울시 종로구 ~~
//        Toast.makeText(CurrentLocationActivity.this, "현재위치 \n위도" + latitude + "\n경도" + longitude, Toast.LENGTH_LONG).show();
//    }
//
//    public void onRequestPermissionResult(int requestCode,
//                                          @NonNull String[] permissions,
//                                          @NonNull int[] grandResults){
//        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length){
//            boolean check_result = true;
//
//            for(int result:grandResults){
//                if(result != PackageManager.PERMISSION_GRANTED){
//                    check_result = false;
//                    break;
//                }
//            }
//            if(check_result){
//                ;
//            }else{
//                if(ActivityCompat.shouldShowRequestPermissionRationale(this,REQUIRED_PERMISSIONS[0])
//                || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])){
//                    Toast.makeText(CurrentLocationActivity.this, "권한이 없어요. 다시 실행해서 권한을 허용해 주세요.", Toast.LENGTH_LONG).show();
//                    finish();
//                }else{
//                    Toast.makeText(CurrentLocationActivity.this, "권한이 거부되었어요. 설정에서 권한을 허용해 주세요.", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//    }
//
//    void checkRunTimePermission(){
//        int hasFineLocationPermission = ContextCompat.checkSelfPermission(CurrentLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(CurrentLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
//
//        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
//        hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){
//            //위치 값을 가져올 수 있음
//        }else{
//            if(ActivityCompat.shouldShowRequestPermissionRationale(CurrentLocationActivity.this, REQUIRED_PERMISSIONS[0])){
//                Toast.makeText(CurrentLocationActivity.this, "현재 위치를 실행하려면 위치 접근 권한이 필요합니다.",Toast.LENGTH_LONG).show();
//                ActivityCompat.requestPermissions(CurrentLocationActivity.this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
//            }else{
//                ActivityCompat.requestPermissions(CurrentLocationActivity.this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
//            }
//        }
//    }
//
//    public String getCurrentAddress(double latitude, double longitude) {
//        //지오코더... GPS를 주소로 변환
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//        List<Address> addresses;
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 7);
//        } catch (IOException ioException) {
//            //네트워크 문제
//            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
//
//            return "지오코더 서비스 사용불가";
//        } catch (IllegalArgumentException illegalArgumentException) {
//            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
//
//            return "잘못된 GPS 좌표";
//        }
//        if (addresses == null || addresses.size() == 0) {
//            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
//            return "주소 미발견";
//        }
//
//        Address address = addresses.get(0);
//        return address.getAddressLine(0).toString()+"\n";
//    }
//        //여기부터는 GPS 활성화를 위한 메소드들
//
//    private void showDialogForLocationServiceSetting() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentLocationActivity.this);
//        builder.setTitle("위치 서비스 비활성화");
//        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
//                + "위치 설정을 수정하실래요?");
//        builder.setCancelable(true);
//        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int id) {
//                Intent callGPSSettingIntent =
//                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
//            }
//        });
//        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//        builder.create().show();
//    }
//
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            switch (requestCode) {
//                case GPS_ENABLE_REQUEST_CODE:
//                    //사용자가 GPS 활성 시켰는지 검사
//                    if (checkLocationServicesStatus()) {
//                        if (checkLocationServicesStatus()) {
//                            Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
//                            checkRunTimePermission();
//                            return;
//                        }
//                    }
//                    break;
//            }
//    }
//
//
//    private boolean checkLocationServicesStatus() {
//        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    }
//
//
//
//}
