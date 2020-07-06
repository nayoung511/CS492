//package com.example.cs492_s2.activity;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cs492_s2.R;
//
//import java.util.ArrayList;
//
//class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
//    private ArrayList<ProfileData> mDataset;
//
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
//    public ProfileAdapter(ArrayList<ProfileData> myDataset){
//        mDataset = myDataset;
//    }
//
//    @Override
//    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
//                                                        int viewType) {
//        // create a new view
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_ideallist, parent, false);
//        // set the view's size, margins, paddings and layout parameters
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
//        holder.profileImage.setImageResource(mDataset.get(position).img);
//        holder.user_name.setText(mDataset.get(position).user_name);
//        holder.user_age.setText(mDataset.get(position).user_age);
//        holder.user_job.setText(mDataset.get(position).user_job);
//        holder.user_location.setText(mDataset.get(position).user_location);
//        holder.hashbox1.setText(mDataset.get(position).hashbox1);
//        holder.hashbox2.setText(mDataset.get(position).hashbox2);
//    }
//    @Override
//    public int getItemCount() {
//        return mDataset.size();
//    }
//}
//
////class ProfileData {
////    public String user_name;
////    public String user_age;
////    public String user_job;
////    public String user_location;
////    public String hashbox1;
////    public String hashbox2;
////    public int img;
////
////
////    public ProfileData(int img, String user_name, String user_age, String user_job, String user_location, String hashbox1, String hashbox2){
////        this.img = img;
////        this.user_name = user_name;
////        this.user_age = user_age;
////        this.user_job = user_job;
////        this.user_location = user_location;
////        this.hashbox1 = hashbox1;
////        this.hashbox2 = hashbox2;
////
////    }
//}