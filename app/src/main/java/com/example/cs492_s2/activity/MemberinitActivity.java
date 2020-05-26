package com.example.cs492_s2.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.MemberInfo;
import com.example.cs492_s2.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MemberinitActivity extends AppCompatActivity {
    private static final String TAG = "MemberinitActivity";
    private ImageView profileImageView;
    private String profilePath;
    FirebaseUser user;

    private FirebaseAuth mAthu;
    EditText et_birthdate, et_phone, et_name, et_location ;
    Button btn_check;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout content view
        setContentView(R.layout.activity_member_init);

        profileImageView = findViewById(R.id.view_profileImage);
        profileImageView.setOnClickListener(onClickListener);
        mAthu = FirebaseAuth.getInstance();

        findViewById(R.id.btn_check).setOnClickListener(onClickListener);
        findViewById(R.id.view_profileImage).setOnClickListener(onClickListener);
        findViewById(R.id.btn_picture).setOnClickListener(onClickListener);
        findViewById(R.id.btn_gallery).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0: {
                if(resultCode == Activity.RESULT_OK){
                    profilePath = data.getStringExtra("profilePath");
                    Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                    profileImageView.setImageBitmap(bmp);

                }
                break;
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_check:
                    profileUpdate();
                    break;
                case R.id.view_profileImage:
                    DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mystartActivity(CameraActivity.class);
                        }
                    };
                    DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mystartActivity(GalleryActivity.class);
                        }
                    };
                    DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    };

                    new AlertDialog.Builder(MemberinitActivity.this)
                            .setTitle("업로드할 이미지 선택")
                            .setPositiveButton("사진촬영", cameraListener)
                            .setNeutralButton("앨범선택", albumListener)
                            .setNegativeButton("취소", cancelListener)
                            .show();


//                    CardView cardView = findViewById(R.id.buttonsCardView);
//                    if(cardView.getVisibility() == View.VISIBLE){
//                        cardView.setVisibility(View.GONE);
//                    }else{
//                        cardView.setVisibility(View.VISIBLE);
//                    }
//                    break;



//                case R.id.btn_picture:
//                    mystartActivity(CameraActivity.class);
//                    break;
//                case R.id.btn_gallery:
//
//                    //권한이 없을 때
//                    if (ContextCompat.checkSelfPermission(MemberinitActivity.this,
//                            Manifest.permission.READ_EXTERNAL_STORAGE)
//                            != PackageManager.PERMISSION_GRANTED) {
//
//                        // Permission is not granted
//                        // Should we show an explanation?
//                        if (ActivityCompat.shouldShowRequestPermissionRationale(MemberinitActivity.this,
//                                Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                        } else {
//                           startToast("권한을 허용해 주세요");
//                        }
//                    }else{
//                        mystartActivity(GalleryActivity.class);
//                    }
//                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mystartActivity(GalleryActivity.class);
                } else {
                    startToast("권한을 허용해 주세요");
                }
            }
        }
    }

    private void profileUpdate(){
        final String name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        final String phone = ((EditText)findViewById(R.id.et_phoneNum)).getText().toString();
        final String birthdate = ((EditText)findViewById(R.id.et_birthdate)).getText().toString();
        final String location = ((EditText)findViewById(R.id.et_location)).getText().toString();

        if(name.length() > 0 && phone.length()>9 && birthdate.length() > 5 && location.length()>0){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final StorageReference profileImagesRef = storageRef.child("images/"+user.getUid()+"/profilePic.jpg");


            if(profilePath == null){
                MemberInfo memberinfo = new MemberInfo(name, phone, birthdate, location);
                uploader(memberinfo);
            }else {
                try {
                    InputStream stream = new FileInputStream(new File(profilePath));
                    UploadTask uploadTask = profileImagesRef.putStream(stream);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            // Continue with the task to get the download URL
                            return profileImagesRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();

                                //유저 정보
                                MemberInfo memberInfo = new MemberInfo(name, phone, birthdate, location, downloadUri.toString());
                                uploader(memberInfo);
                            } else {
                                startToast("회원정보를 보내는데 실패했어요");
                            }
                        }
                    });
                } catch (FileNotFoundException e) {
                    Log.e("로그", "에러 " + e.toString());
                }
            }
        }else{
            startToast("회원 정보를 입력해주세요");
        }
    }

    private void uploader(MemberInfo memberInfo){
        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").document(user.getUid()).set(memberInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void avoid) {
                            startToast("회원정보 등록을 성공했어요");
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startToast("회원정보 등록 실패했어요");
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //go to c activity
    private void mystartActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivityForResult(intent, 0);
    }

}
