package com.example.cs492_s2.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class LocationActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    EditText editText;
    ImageView imageView;

    GeoVariable geovariable = new GeoVariable();  // 클래스 변수 사용 위해
    Geocoder geocoder = new Geocoder(this); // 역지오코딩 하기 위해

    double latitude, longitude; // 위도, 경도 전역변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        imageView = (ImageView) findViewById(R.id.btn_gps) ;
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setAutofillHints(View.AUTOFILL_HINT_POSTAL_ADDRESS);
        listView = (ListView) findViewById(R.id.search_result);
//        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.search_hold);


        //current palce intialize

        //initialize places
        Places.initialize(getApplicationContext(),"AIzaSyCaARwCHalrDoxNNpP976mWgbMamFg7Xkw");

        //Set searchView non focusable
        editText.setFocusable(false);


        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG, Place.Field.NAME);
                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fieldList).build(LocationActivity.this);

                //start activity result
                startActivityForResult(intent, 100);

            }
        });

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch(v.getId()) {
                    case R.id.btn_gps:
                        //check for the gps
                        chkGpsService();
                        getCurrentLocation();
                       // mystartActivity(reverseGeocoding.class);
                }
            }
        });


        list = new ArrayList<>();
//        list.add("내 위치");
//        list.add("서울");
//        list.add("대전");


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), IdealListActivity.class);
                startActivity(intent);
                //putExtra에 넘길
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(LocationActivity.this, " 검색 결과가 없습니다", Toast.LENGTH_LONG);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

    }

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            //when success
            //initialize place
            Place place  = Autocomplete.getPlaceFromIntent(data);
            //Set address on searchView
            String address = (place.getAddress());
            editText.setText("  " + place.getAddress());
            //set locality name
            String locality = (String.format("지역 이름: %s", place.getName()));
            //editText.setText(locality);
        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){
            //error -> initalize the status
            Status status = Autocomplete.getStatusFromIntent(data);
            //Display toast
            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //GPS 설정 체크
    private boolean chkGpsService() {

        String gps = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        Log.d(gps, "aaaa");

        if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {
            // GPS OFF 일때 Dialog 표시
            AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
            gsDialog.setTitle("위치 서비스 설정");
            gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
            gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create().show();
            return false;

        } else {
            return true;
        }
    }


//    public void reverseCoding(){ // 위도 경도 넣어가지구 역지오코딩 주소값 뽑아낸다
//        List<Address> list = null;
//        getCurrentLocation();
//        try {
//            list = geocoder.getFromLocation(latitude, longitude, 10); // 위도, 경도, 얻어올 값의 개수
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
//        }
//        if (list != null) {
//            if (list.size()==0) {
//                //editText.setText("위도: " + latitude + "경도: " + longitude);
//                //editText.setText("해당되는 주소 정보는 없습니다");
//            } else {
//                // onWhere.setText(list.get(0).toString()); 원래 통으로 나오는 주소값 문자열
//
//                // 문자열을 자르자!
//                String cut[] = list.get(0).toString().split(" ");
//                for(int i=0; i<cut.length; i++){
//                    System.out.println("cut["+i+"] : " + cut[i]);
//                } // cut[0] : Address[addressLines=[0:"대한민국
//                // cut[1] : 서울특별시  cut[2] : 송파구  cut[3] : 오금동
//                // cut[4] : cut[4] : 41-26"],feature=41-26,admin=null ~~~~
//                String a = cut[1] + " " + cut[2] + " " + cut[3];
//                editText.setText(a); // 내가 원하는 구의 값을 뽑아내 출력
//            }
//        }
//    }

    private void getCurrentLocation() {

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(300);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(LocationActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){

                    @Override
                    public void onLocationResult(LocationResult locationResult){
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(LocationActivity.this)
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

                            List<Address> list = null;
                            try {
                                list = geocoder.getFromLocation(latitude, longitude, 10); // 위도, 경도, 얻어올 값의 개수
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                            }
                            if (list != null) {
                                if (list.size()==0) {
                                    //editText.setText("위도: " + latitude + "경도: " + longitude);
                                    editText.setText("해당되는 주소 정보는 없습니다");
                                } else {
                                    // onWhere.setText(list.get(0).toString()); 원래 통으로 나오는 주소값 문자열

                                    // 문자열을 자르자!
                                    String cut[] = list.get(0).toString().split(" ");
                                    for(int i=0; i<cut.length; i++){
                                        System.out.println("cut["+i+"] : " + cut[i]);
                                    } // cut[0] : Address[addressLines=[0:"대한민국
                                    // cut[1] : 서울특별시  cut[2] : 송파구  cut[3] : 오금동
                                    // cut[4] : cut[4] : 41-26"],feature=41-26,admin=null ~~~~
                                    String a = "  " + cut[1] + " " + cut[2] + " " + cut[3];
                                    editText.setText(a); // 내가 원하는 구의 값을 뽑아내 출력
                                }
                            }




                        }
                    }
                }, Looper.getMainLooper());
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //go to c activity
    private void mystartActivity(Class c){
        Intent intent = new Intent(this,c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
