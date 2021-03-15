package com.example.jam3na_testing.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jam3na_testing.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.sql.DriverManager.println;

public class CreateGroupController extends AppCompatActivity {


    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Button InsBtn,createBtn ;
    EditText GroupName,GroupDesc;
    Spinner groupCategory;
    int GroupID=0;
    int select_photo=1;
    Uri uri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        imageView=findViewById(R.id.imageView);
        InsBtn=findViewById(R.id.ins_pic);
        createBtn=findViewById(R.id.createBtn);
        GroupDesc=findViewById(R.id.groupDescText);
        GroupName=findViewById(R.id.groupNameText);
        groupCategory=findViewById(R.id.groupCategory);
        ArrayList<String> GroupCategoryList=new ArrayList<>();
        GroupCategoryList.add("Football Sports");
        GroupCategoryList.add("Cycling Sports");
        GroupCategoryList.add("Kayaking");
        GroupCategoryList.add("Canoeing");
        GroupCategoryList.add("Skiing");
        GroupCategoryList.add("Aerobics");
        GroupCategoryList.add("Boxing");
        GroupCategoryList.add("Running");
        GroupCategoryList.add("Martial Arts");
        GroupCategoryList.add("Powerlifting");
        GroupCategoryList.add("Basketball");
        GroupCategoryList.add("Tennis");
        GroupCategoryList.add("Badminton");
        GroupCategoryList.add("Bowling");
        GroupCategoryList.add("Handball");
        GroupCategoryList.add("Squash");
        GroupCategoryList.add("Table Tennis");
        GroupCategoryList.add("Volleyball");
        GroupCategoryList.add("Skateboarding");
        ArrayAdapter<String> catadapter =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,GroupCategoryList);
        groupCategory.setAdapter(catadapter);

        createBtn.setOnClickListener(e->{
            create_group_method();
        });

    InsBtn.setOnClickListener(e->{
        insert_group_picture();
    });
    }

    private void insert_group_picture() {
        Intent intent=new Intent(Intent.ACTION_PACKAGE_ADDED);
        intent.setType("image/*");
        startActivityForResult(intent,select_photo);
    }
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==select_photo && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
    imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void create_group_method() {
         String TAG = null;

    String Group_desc=GroupDesc.getText().toString();
    String Group_name=GroupName.getText().toString();
        String Group_category=groupCategory.getSelectedItem().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> groupHash = new HashMap<>();
        groupHash.put("Group Id", GroupID++);
        groupHash.put("Group Name", Group_name);
        groupHash.put("Group Description", Group_desc);
        groupHash.put("Group Category", Group_category);
        groupHash.put("Group Admin", "murra");

        // Add a new document with a generated ID
        db.collection("GroupsTable")
                .add(groupHash)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(CreateGroupController.this, "Group Created successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(CreateGroupController.this, "Group Not Created successfully.", Toast.LENGTH_SHORT).show();

                    }
                });

    }

}