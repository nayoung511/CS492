//package com.example.cs492_s2.activity.chatfiles;
//
//import android.content.Context;
//import android.os.Message;
//import android.text.format.DateFormat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.request.RequestOptions;
//import com.example.cs492_s2.R;
//import com.google.firebase.storage.StorageReference;
//
//public class ChatAdapter extends FirestoreRecyclerAdapter<ChatData, ChatAdapter.ChatHolder>{
//    private final String TAG = "ChatAdapter";
//    Context context;
//    String userId;
//    StorageReference storageReference;
//    private RequestOptions requestOptions = new RequestOptions();
//    private final int MESSAGE_IN_VIEW_TYPE  = 1;
//    private final int MESSAGE_OUT_VIEW_TYPE = 2;
//
//    @Override
//    public int getItemViewType(int position) {
//        //if message userId matches current userid, set view type 1 else set view type 2
//        if(getItem(position).getMessageUserId().equals(userId)){
//            return MESSAGE_OUT_VIEW_TYPE;
//        }
//        return MESSAGE_IN_VIEW_TYPE;
//    }
//
//    @Override
//    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        /*
//        We're using two different layouts. One for messages from others and the other for user's messages
//         */
//        View view = null;
//        if(viewType==MESSAGE_IN_VIEW_TYPE){
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.activity_chat, parent, false);
//        }
//        else{
//            view = LayoutInflater.from(parent.getContext())
//                    //원래 activity_chatout이여야되는데 왜이러는거
//                    .inflate(R.layout.activity_chat, parent, false);
//        }
//        return new ChatHolder(view);
//    }
//
//
//    @Override
//    protected void onBindViewHolder(@NonNull ChatHolder holder, int position, @NonNull Message model) {
//        //Bind values from Message to the viewHolder
//
//        final TextView mText = holder.mText;
//        final TextView mUsername = holder.mUsername;
//        final TextView mTime = holder.mTime;
//        final TextView mLikesCount = holder.mLikesCount;
////        final CircleImageView imgProfile = holder.imgProfile;
//        final ImageView imgDropdown = holder.imgDropdown;
//        final ImageView imgLikes = holder.imgLikes;
//
//        mUsername.setText(model.getMessageUser());
//        mText.setText(model.getMessageText());
//        mTime.setText(DateFormat.format("dd MMM  (h:mm a)", model.getMessageTime()));
//        mLikesCount.setText(String.valueOf(model.getMessageLikesCount()));
//        if (model.getMessageLikes() != null) {
//            if (model.getMessageLikes().containsValue(userId)) {
//                imgLikes.setImageResource(R.drawable.ic_favorite_red_24dp);
//            } else {
//                imgLikes.setImageResource(R.drawable.ic_favorite_black_24dp);
//            }
//        }
//    }
//        //프로필 화면 띄우기
////        GlideApp.with(context)
////                .setDefaultRequestOptions(requestOptions)
////                .load(storageReference.child(model.getMessageUserId()))
////                .into(imgProfile);
//
//        @Override
//        public int getItemViewType(int position) {
//            if(getItem(position).getMessageUserId().equals(userId)){
//                return MESSAGE_OUT_VIEW_TYPE;
//            }
//            return MESSAGE_IN_VIEW_TYPE;
//        }
//
//        @Override
//        public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        /*
//        We're using two custom layouts. One for message in and other for message out
//         */
//            View view = null;
//            if(viewType==MESSAGE_IN_VIEW_TYPE){
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.activity_chat, parent, false);
//            }
//            else{
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.activity_chatout, parent, false);
//            }
//            return new ChatHolder(view);
//        }
//
//        public class ChatHolder extends RecyclerView.ViewHolder {
//            TextView mText;
//            TextView mUsername;
//            TextView mTime;
//            TextView mLikesCount;
////            CircleImageView imgProfile;
//            ImageView imgDropdown, imgLikes;
//            public MessageHolder(View itemView) {
//                super(itemView);
//                mText = itemView.findViewById(R.id.message_text);
//                mUsername = itemView.findViewById(R.id.message_user);
//                mTime = itemView.findViewById(R.id.message_time);
//                mLikesCount = itemView.findViewById(R.id.message_Likes);
//               // imgProfile = itemView.findViewById(R.id.imgDps);
//                imgLikes = itemView.findViewById(R.id.imgLikes);
//                imgDropdown = itemView.findViewById(R.id.imgDropdwon);
//            }
//        }
//    }