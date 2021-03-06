package com.example.cs492_s2.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;

public class MemberinitActivity extends AppCompatActivity {
    private static final String TAG = "MemberinitActivity";
    private static final int GALLERY_VIDEO = 1;
    private static final int GALLERY_IMAGE = 0;


    private ImageView profileImageView;
    private String profilePath;
    private File tempFile;
    private Uri mImageCaptureUri;

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
    };


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_check:
                    //startToast("확인을 누르셨습니다 ");
                    //profileUpdate();
                    //메인 서치 화면으로 이동
                    Log.e("멤버이닛", "이동");
                    mystartActivity(ProfileActivity.class);
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
                            doTakeAlbumAction();

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

                    break;
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
                    //mystartActivity(GalleryActivity.class);
                    doTakeAlbumAction();
                } else {
                    startToast("권한을 허용해 주세요");
                }
            }
        }
    }

    private void doTakeAlbumAction() {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 1: {
                mImageCaptureUri = data.getData();
                setImage();
                break;
            }
        }
    }
    public void setImage(){
        profileImageView.setImageURI(mImageCaptureUri);
    }

    private void profileUpdate(){
        final String name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        final String phone = ((EditText)findViewById(R.id.et_phoneNum)).getText().toString();
        final String birthdate = ((EditText)findViewById(R.id.et_birthdate)).getText().toString();
        final String location = ((EditText)findViewById(R.id.et_location)).getText().toString();

        if(name.length() > 0 && phone.length()>9 && birthdate.length() > 5 && location.length()>0){
            Log.e("로그", "으으으으으응ㅁ.... ");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            //StorageReference storageRef = storage.getReference();
            //final StorageReference profileImagesRef = storageRef.child("images/"+user.getUid()+"/profilePic.jpg");

            MemberInfo memberInfo = new MemberInfo(name, phone, birthdate, location);
           if(user != null){
               db.collection("users").document(user.getUid()).set(memberInfo)
                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               startToast("회원정보 등록을 성공했어요.");
                               //mystartActivity(ProfileActivity.class);
                               finish();
                               
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               startToast("회원정보 등록에 실패했어요. ");
                           }
                       });

           }
//            uploader(memberInfo);
//            startToast("회원정보를 보내는데 성공했어요");
//
//            startToast("회원등록 성공했어요 ");
//            mystartActivity(LocationActivity.class);

//            if(profilePath == null){
//                startToast("회원정보가 비었네요");
//                MemberInfo memberinfo = new MemberInfo(name, phone, birthdate, location);
//                uploader(memberinfo);
//            }else {
//                try {
//                    startToast("업로드 시도 ");
//                    MemberInfo memberInfo = new MemberInfo(name, phone, birthdate, location);
//                    uploader(memberInfo);
//                    startToast("회원정보를 보내는데 성공했어요");
//
//                    //메인 서치 화면으로 이동
//                    mystartActivity(LocationActivity.class);
//
//
//                    InputStream stream = new FileInputStream(new File(profilePath));
//                    UploadTask uploadTask = profileImagesRef.putStream(stream);
//                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                        @Override
//                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                            if (!task.isSuccessful()) {
//                                throw task.getException();
//                            }
//                            // Continue with the task to get the download URL
//                            return profileImagesRef.getDownloadUrl();
//                        }
//                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//                            if (task.isSuccessful()) {
//                                //Uri downloadUri = task.getResult();
//                                //유저 정보
//                               // MemberInfo memberInfo = new MemberInfo(name, phone, birthdate, location, downloadUri.toString());
//                                MemberInfo memberInfo = new MemberInfo(name, phone, birthdate, location);
//                                uploader(memberInfo);
//                                startToast("회원정보를 보내는데 성공했어요");
//
//                            } else {
//                                startToast("회원정보를 보내는데 실패했어요");
//                            }
//                        }
//                    });
//                } catch (FileNotFoundException e) {
//                    Log.e("로그", "에러 " + e.toString());
//                }
//            }
        }else{
            startToast("회원 정보를 입력해주세요");
        }
    }

    private void uploader(MemberInfo memberInfo){
        // Access a Cloud Firestore instance from your Activity
        Log.e("로그", user.getUid());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(user.getUid())
                .set(memberInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void avoid) {
                            Log.e("로그", "흐흠.... ");
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

