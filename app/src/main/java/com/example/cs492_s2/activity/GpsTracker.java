package com.example.cs492_s2.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cs492_s2.R;

//public class GpsTracker extends Service implements LocationListener {
//    @Override
//    public void onLocationChanged(Location location) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s) {
//
//    }

    public class GpsTracker extends AppCompatActivity implements LocationListener{
        // 클래스 변수 사용 위해(static)
        GeoVariable geovariable = new GeoVariable();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location);


            // 권한 물어서 권한안되어있으면 권한 셋팅해주기
            int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
            if (permissionCheck1 == PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);

            int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (permissionCheck2 == PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

            int permissionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionCheck3 == PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            // LocationManager 객체를 얻어온다
            final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록
            try {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                        100, // 통지사이의 최소 시간간격 (miliSecond)
                        1, // 통지사이의 최소 변경거리 (m)
                        mLocationListener);
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                        100, // 통지사이의 최소 시간간격 (miliSecond)
                        1, // 통지사이의 최소 변경거리 (m)
                        mLocationListener);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }


        private final LocationListener mLocationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                //여기서 위치값이 갱신되면 이벤트가 발생한다.
                //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

                Log.d("test", "onLocationChanged, location:" + location);
                double longitude = location.getLongitude(); //경도
                double latitude = location.getLatitude();   //위도
                geovariable.setLatitude(latitude); // 클래스 변수에 위도 대입
                geovariable.setLongitude(longitude);  // 클래스 변수에 경도 대입
            }

            public void onProviderDisabled(String provider) {
                // Disabled시
                Log.d("test", "onProviderDisabled, provider:" + provider);
            }

            public void onProviderEnabled(String provider) {
                // Enabled시
                Log.d("test", "onProviderEnabled, provider:" + provider);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                // 변경시
                Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
            }
        };
    public IBinder onBind(Intent arg0) {
        return null;
    }

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }




//    GeoVariable geovariable = new GeoVariable();
//    private final Context mContext;
//    Location location;
//    double latitude;
//    double longitude;
//
//    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
//    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
//    protected LocationManager locationManager;
//
//
//    public GpsTracker(Context context) {
//        this.mContext = context;
//        getLocation();
//    }
//
//    public Location getLocation() {
//        try {
//            locationManager = (LocationManager)
//                    mContext.getSystemService(LOCATION_SERVICE);
//            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//            if (!isGPSEnabled && !isNetworkEnabled) {
//
//            } else {
//                int hasFineLocationPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
//                int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);
//
//                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
//                        hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
//
//                } else
//                    return null;
//
//                if (isNetworkEnabled) {
//                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                            MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                    if (locationManager != null) {
//                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//                        if (location != null) {
//                            latitude = location.getLatitude();
//                            longitude = location.getLongitude();
//                        }
//                    }
//                }
//
//                if (isGPSEnabled) {
//                    if (location == null) {
//                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                        if (locationManager != null) {
//                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                            if (location != null) {
//                                latitude = location.getLatitude();
//                                longitude = location.getLongitude();
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.d("@@@", ""+e.toString());
//        }
//        return location;
//    }
//
//    public double getLatitude() {
//        if(location != null) {
//            latitude = location.getLatitude();
//        }
//        return latitude;
//    }
//
//    public double getLongitude() {
//        if(location != null) {
//            longitude = location.getLongitude();
//        }
//        return longitude;
//    }
//
//    private final LocationListener mLocationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            //여기서 위치값이 갱신되면 이벤트가 발생한다.
//            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.
//
//            Log.d("test", "onLocationChanged, location:" + location);
//            double longitude = location.getLongitude(); //경도
//            double latitude = location.getLatitude();   //위도
//            geovariable.setLatitude(latitude); // 클래스 변수에 위도 대입
//            geovariable.setLongitube(longitude);  // 클래스 변수에 경도 대입
//
//        }
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//    };
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//    public void stopUsingGPS() {
//        if(locationManager != null) {
//            locationManager.removeUpdates(GpsTracker.this);
//        }
//    }


