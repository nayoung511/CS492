package com.example.cs492_s2.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LocationParsing extends AppCompatActivity {
    private List<String> list;
    private ListView search_result;
    private EditText search_hold;
    private LocationAdapter adapter;
    private ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_location);

        search_hold = (EditText) findViewById(R.id.search_hold);
        //search_result = (ListView) findViewById(R.id.search_result);

        list = new ArrayList<String>();


//        try {
//            settingList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ReadTextFile();


        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new LocationAdapter(list, this);

        search_result.setAdapter(adapter);

        String text = search_hold.getText().toString();
        search(text);

//        search_hold.addTextChangedListener(new TextWatcher() {
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
//                String text = search_hold.getText().toString();
//                search(text);
//            }
//        });

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
            for(int i = 0; i < arraylist.size(); i++)
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
        list.add("힘내!");
        String[] cut = ReadTextFile().split(",");
        list.add(cut[0] + " " + cut[1] + " " + cut[2]);
    }

     public String ReadTextFile(){
        StringBuffer stringBuffer = new StringBuffer();

        //Load from text File
        InputStream input;
        String text = "";

        AssetManager assetManager = getAssets();

        try{
            input = assetManager.open("location_data");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line="";
            while((line = reader.readLine())!=null){
                stringBuffer.append(line +"\n");
                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();
                String[] cut = new String(buffer).split(",");
                list.add(cut[0] + " " + cut[1] + " " + cut[2]);
            }

            return text;

        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

}
//    private final FirebaseFirestore db;
//    LocationParsing(FirebaseFirestore db){
//        this.db = db;
//    }
//
//    public void setup(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(true)
//                .build();
//        db.setFirestoreSettings(settings);
//    }
//
//    public void addLocationData(){
//        //시 (서울시)
//        //구 (서대문구)
//        //동 (..동)
//
//        Map<String, Object> location_data = new HashMap<>();
//
//        //Create a new document
//        db.collection("location")
//                .document("geographic")
//                .add.addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                    }
//                })
//                .addOnFailureListener()
//    }
//
//    TextView location_do;
//    TextView location_gu;
//    TextView location_dong;
//    ArrayList<String> list;
//    ListView search_result_db;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location_parsing);
//
//        location_do = findViewById(R.id.location_do);
//        location_gu = findViewById(R.id.location_gu);
//        location_dong = findViewById(R.id.location_dong);
//        search_result_db = findViewById(R.id.search_result_db);
//
//        list = new ArrayList<>();
//
//
//        for(int i = 0; i<18706; i++){
//            String[] cut = ReadTextFile().split(",");
//        location_do.setText(cut[0]);
//        location_gu.setText(cut[1]);
//        location_dong.setText(cut[2]);
//
//
//
//            list.add(cut[0] + " " + cut[1] + " " + cut[2]);
//
//        }
////        String[] cut = ReadTextFile().split(",");
////        location_do.setText(cut[0]);
////        location_gu.setText(cut[1]);
////        location_dong.setText(cut[2]);
//
////        list.add(cut[0] + " " + cut[1] + " " + cut[2]);
////        list.add("서울");
////        list.add("대전");
//    }
//
//    public String ReadTextFile(){
//        //Load from text File
//        InputStream input;
//        String text = "";
//
//        AssetManager assetManager = getAssets();
//
//        try{
//            input = assetManager.open("location_data");
//
//            int size = input.available();
//            byte[] buffer = new byte[size];
//            input.read(buffer);
//            input.close();
//
//            text = new String(buffer);
//
//            return text;
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return text;
//    }
//
