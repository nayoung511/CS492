package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;

public class IdealListActivity extends AppCompatActivity {
    TextView location_result;

    String chat_name;

    ImageView profileImage;
    ImageView profileImage_2;
    ImageView profileImage_3;

    //    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    //ideal list를 담을 holder
    //private ArrayList<ProfileData> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("아이디얼", "왜이래용");

        setContentView(R.layout.activity_ideallist);

        location_result = findViewById(R.id.location_result);

        //상단 검색 결과로 텍스트 대치
        Intent intent = getIntent();
        String location = intent.getExtras().getString("location");
        location_result.setText(location + " 검색 결과");



        profileImage = findViewById(R.id.profileImage);
        profileImage_2 = findViewById(R.id.profileImage_2);
        profileImage_3 = findViewById(R.id.profileImage_3);


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView user_name = findViewById(R.id.user_name);
                chat_name = user_name.getText().toString();

                Intent intent_2 = new Intent(getApplicationContext(), ChatActivity.class);
                intent_2.putExtra("user_name", chat_name);
                startActivity(intent_2);
            }
        });


        profileImage_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView user_name = findViewById(R.id.user_name_2);
                chat_name = user_name.getText().toString();

                Intent intent_2 = new Intent(getApplicationContext(), ChatActivity.class);
                intent_2.putExtra("user_name", chat_name);
                startActivity(intent_2);
            }
        });


        profileImage_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView user_name = findViewById(R.id.user_name_3);
                chat_name = user_name.getText().toString();

                Intent intent_2 = new Intent(getApplicationContext(), ChatActivity.class);
                intent_2.putExtra("user_name", chat_name);
                startActivity(intent_2);
            }
        });


        /*
        * for demo only
        *
        * holder.profileImage.setImageResource(mDataset.get(position).img);
        holder.user_name.setText(mDataset.get(position).text);
        holder.user_age.setText(mDataset.get(position).text);
        holder.user_job.setText(mDataset.get(position).text);
        holder.user_location.setText(mDataset.get(position).text);
        holder.hashbox1.setText(mDataset.get(position).text);
        holder.hashbox2.setText(mDataset.get(position).text);
        *
        * int img, String user_name, String user_age, String user_job, String user_location, String hashbox1, String hashbox2
        *
        * */


        /*
        myDataset = new ArrayList<>();


        myDataset.add(new ProfileData(R.drawable.profile_image, "김사랑", "23세", "대학생", "1.5KM", "#활발한", "#운동좋아"));
        myDataset.add(new ProfileData(R.drawable.profile_image, "이하민", "27세", "교사", "2.0KM", "#차분한", "#영화좋아"));
        myDataset.add(new ProfileData(R.drawable.profile_image, "박태오", "31세", "요리사", "3.1KM", "#다정한", "#여행좋아"));
        myDataset.add(new ProfileData(R.drawable.profile_image, "이지담", "26세", "엔지니어", "4.5KM", "#밝은", "#게임좋아"));
        myDataset.add(new ProfileData(R.drawable.profile_image, "최준희", "41세", "건축가", "0.8KM", "#친절한", "#요리좋아"));
        myDataset.add(new ProfileData(R.drawable.profile_image, "이찬형", "50세", "경찰", "1.1KM", "#재미있는", "#음악좋아"));
        myDataset.add(new ProfileData(R.drawable.profile_image, "유서현", "25세", "운동선수", "2.1KM", "#발랄한", "#의욕적인"));


        Log.e("아이디얼", "after adding to the list");

        ProfileAdapter profileAdapter = new ProfileAdapter(
                getApplicationContext(),
                R.layout.ideal_recycle,
                myDataset);

        Log.e("아이디얼", "connecting with recycler");


        ListView lv = findViewById(R.id.list_ideal_box);
        lv.setAdapter(profileAdapter);

        Log.e("아이디얼", "done connecting with recycler");


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        IdealDetailActivity.class);


                Log.e("아이디얼", "start intent activity");


                intent.putExtra("img", myDataset.get(i).img);
                intent.putExtra("name", myDataset.get(i).user_name);
                intent.putExtra("age", myDataset.get(i).user_age);
                intent.putExtra("job", myDataset.get(i).user_job);
                intent.putExtra("location", myDataset.get(i).user_location);
                intent.putExtra("hashbox1", myDataset.get(i).hashbox1);
                intent.putExtra("hashbox2", myDataset.get(i).hashbox2);

                startActivity(intent);
            }

        });
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


//        //recycler view 설정
//        mRecyclerView = findViewById(R.id.ideal_recycler_view);
//
//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        //connect with the adapter
//        myDataset = new ArrayList<>();
//        mAdapter = new ProfileAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);
//
//



class ProfileAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ProfileData> myDataset;
    LayoutInflater inf;

    public ProfileAdapter(Context context, int layout, ArrayList<ProfileData> myDataset) {
        this.context = context;
        this.layout = layout;
        this.myDataset = myDataset;
        inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataset.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataset.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.img_profile);
        TextView user_name = (TextView) convertView.findViewById(R.id.user_name);
        TextView user_age = (TextView) convertView.findViewById(R.id.user_age);
        TextView user_job = (TextView) convertView.findViewById(R.id.user_job);
        TextView user_location = (TextView) convertView.findViewById(R.id.user_location);
        TextView hashbox1 = (TextView) convertView.findViewById(R.id.user_hash_box);
        TextView hashbox2 = (TextView) convertView.findViewById(R.id.user_hash_box_2);

        ProfileData m = myDataset.get(i);
        img.setImageResource(m.img);
        user_name.setText(m.user_name);
        user_age.setText(m.user_age);
        user_job.setText(m.user_job);
        user_location.setText(m.user_location);
        hashbox1.setText(m.hashbox1);
        hashbox2.setText(m.hashbox2);

        return convertView;

    }
}

class ProfileData {
        public String user_name;
        public String user_age;
        public String user_job;
        public String user_location;
        public String hashbox1;
        public String hashbox2;
        public int img;


        public ProfileData(int img, String user_name, String user_age, String user_job, String user_location, String hashbox1, String hashbox2) {
            this.img = img;
            this.user_name = user_name;
            this.user_age = user_age;
            this.user_job = user_job;
            this.user_location = user_location;
            this.hashbox1 = hashbox1;
            this.hashbox2 = hashbox2;
        }

        public ProfileData(){}
}

//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView location_result;
//        public EditText hashbox1, hashbox2;
//        public ImageView profileImage;
//        public TextView user_name, user_age, user_location, user_job;
//
//        public ViewHolder(View view){
//            super(view);
//            location_result = view.findViewById(R.id.location_result);
//            profileImage = view.findViewById(R.id.profileImage);
//            user_name = view.findViewById(R.id.user_name);
//            user_age = view.findViewById(R.id.user_age);
//            user_location = view.findViewById(R.id.user_location);
//            user_job = view.findViewById(R.id.user_job);
//            hashbox1 = view.findViewById(R.id.user_hash_box);
//            hashbox2 = view.findViewById(R.id.user_hash_box_2);
//        }
//    }
//
//        public ProfileAdapter(ArrayList<ProfileData> myDataset){
//            mDataset = myDataset;
//        }
//
//        @Override
//        public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
//                                                            int viewType) {
//            // create a new view
//            View v = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.activity_ideallist, parent, false);
//            // set the view's size, margins, paddings and layout parameters
//
//            ViewHolder vh = new ViewHolder(v);
//            return vh;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
//            holder.profileImage.setImageResource(mDataset.get(position).img);
//            holder.user_name.setText(mDataset.get(position).user_name);
//            holder.user_age.setText(mDataset.get(position).user_age);
//            holder.user_job.setText(mDataset.get(position).user_job);
//            holder.user_location.setText(mDataset.get(position).user_location);
//            holder.hashbox1.setText(mDataset.get(position).hashbox1);
//            holder.hashbox2.setText(mDataset.get(position).hashbox2);
//        }
//        @Override
//        public int getItemCount() {
//            return mDataset.size();
//        }
//    }

*/
    }
}