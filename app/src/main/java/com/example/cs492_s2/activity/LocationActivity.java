package com.example.cs492_s2.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class LocationActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    List<String> list;
    //ArrayAdapter<String> adapter;
    EditText editText;
    ImageView imageView;
    ImageView category;
    Button go_idealList;
    Button location_db;
    private LocationAdapter adapter;
    private ArrayList<String> arraylist;

    GeoVariable geovariable = new GeoVariable();  // 클래스 변수 사용 위해
    Geocoder geocoder = new Geocoder(this); // 역지오코딩 하기 위해

    double latitude, longitude; // 위도, 경도 전역변수

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        imageView = (ImageView) findViewById(R.id.btn_gps);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setAutofillHints(View.AUTOFILL_HINT_POSTAL_ADDRESS);
        listView = (ListView) findViewById(R.id.search_result);
        go_idealList = (Button) findViewById(R.id.go_idealList);
        editText = (EditText) findViewById(R.id.search_hold);
        category = (ImageView) findViewById(R.id.category);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_menu);


        //카테고리 이동
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mystartActivity(CategoryActivity.class);
            }
        });


        //current palce intialize
        //initialize places
        Places.initialize(getApplicationContext(), "AIzaSyCaARwCHalrDoxNNpP976mWgbMamFg7Xkw");


        /*
        * Requirements
        * Searchbar 에서 자동완성 기능 구현하기
        * */

        String[] location_db = getResources().getStringArray(R.array.location_db);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, location_db);
        AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.search_hold);
        editText.setAdapter(adapter);


        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Log.i("SELECTED TEXT WAS------->", (String) arg0.getItemAtPosition(arg2));
                String[] cut= arg0.getItemAtPosition(arg2).toString().split(" ");
                result = cut[1] + " " + cut[2];
            }
        });

        //Set searchView non focusable
        //editText.setFocusable(false);


//        list = new ArrayList<String>();
//
//        try {
//            settingList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        arraylist = new ArrayList<String>();
//        arraylist.addAll(list);
//
//        adapter  = new LocationAdapter(list, this);
//
//        listView.setAdapter(adapter);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String text = editText.getText().toString();
//                search(text);
//            }
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mystartActivity(LocationParsing.class);
            }
        });

                //google api
//                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
//                //Create intent
//                //options: we only want the address (setTypeFilter)
//                Intent intent = new Autocomplete.IntentBuilder(
//                        AutocompleteActivityMode.OVERLAY, fieldList)
//                        .setTypeFilter(TypeFilter.REGIONS)
//                        .build(LocationActivity.this);
//
//                //start activity result
//                startActivityForResult(intent, 100);

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

        //ideal list로 이동
        go_idealList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IdealListActivity.class);
                intent.putExtra("location", result);
                startActivity(intent);
            }
        });


        //click category
        category.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e("채팅", "채팅액티비티");
                mystartActivity(ChatActivity.class);
                Log.e("채팅", "애플");


            }
        });


//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);



        //go to idealListActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), IdealListActivity.class);
                startActivity(intent);
                //putExtra에 넘길
            }
        });

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(list.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(LocationActivity.this, " 검색 결과가 없습니다", Toast.LENGTH_LONG);
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                return false;
//            }
//        });

    }

//    FindAutocompletePredictionsRequest


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            //when success
            //initialize place
            Place place  = Autocomplete.getPlaceFromIntent(data);
            //Set address on searchView
            String address = (place.getAddress());


            //split string into array

            String[] cut = address.split(" ");
            //건물 이름, 빌딩 이름 같은거
            String address_name = place.getName();

            //xx시, xx구,
            result = cut[1] + " " +cut[2];
            editText.setText("  " + result);
            //set locality name

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
            gsDialog.setMessage("더 나은 사용 환경을 위해 GPS 기능을 사용해 사용 설정 해주세요. \n위치 서비스 기능을 설정하시겠습니까?");
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
                                    result = cut[1] + " " + cut[2] + " " + cut[3];
                                    editText.setText("  " + result); // 내가 원하는 구의 값을 뽑아내 출력
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

    //category bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;

    }




    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    private void settingList() throws Exception {
        list.add("안녕 ");
        list.add(readFromAssets("location_data.txt"));
    }

    private String readFromAssets(String filename) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("location_data.txt")));

        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while(line != null){
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }




}
