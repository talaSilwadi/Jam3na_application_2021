package com.example.jam3na_testing.View;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jam3na_testing.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.Model.Group.CreateGroup;
import java.util.HashMap;
import java.util.Map;

public class CreateGroupController extends AppCompatActivity {


    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Button InsBtn, createBtn;
    EditText GroupName, GroupDesc;
    Spinner GroupCate;
    TextView admin_Id;
    int SELECT_PICTURE = 200;
    ImageView GroupPic;
    StorageReference mStorgeRef;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        fAuth = FirebaseAuth.getInstance();
        mStorgeRef=FirebaseStorage.getInstance().getReference("Images");
        progressBar = findViewById(R.id.progressBar2);
        InsBtn = findViewById(R.id.ins_pic);
        createBtn = findViewById(R.id.createBtn);
        GroupDesc = findViewById(R.id.groupDescText);
        GroupName = findViewById(R.id.groupNameText);
        admin_Id = findViewById(R.id.adminId);
        GroupCate = findViewById(R.id.groupCategroy);
        GroupPic=findViewById(R.id.imageView);
      //  admin_Id.setText("mohammad");


        createBtn.setOnClickListener(e -> {
            create_group_method();
            upload_group_image();
        });
        InsBtn.setOnClickListener(e->{
            imageChooser();
        });


    }
    private String getExtension(Uri uri) {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimetypemap=MimeTypeMap.getSingleton();
        return mimetypemap.getExtensionFromMimeType(cr.getType(uri));

    }

    private void upload_group_image() {
        StorageReference Ref = mStorgeRef.child(System.currentTimeMillis() + "." + getExtension(selectedImageUri));
        Ref.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(CreateGroupController.this, "Image Uploaded successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateGroupController.this, "Image Uploaded failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void create_group_method() {
        String TAG = null;
        String Group_desc = GroupDesc.getText().toString();
        String Group_name = GroupName.getText().toString();
        String Group_adminid = admin_Id.getText().toString();
        String GroupCategory = GroupCate.getSelectedItem().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> group = new HashMap<>();
        group.put("Group Name", Group_name);
        group.put("Group Desciption", Group_desc);
        group.put("Admin ID","mohammad");
        group.put("Group Category", GroupCategory);



// Add a new document with a generated ID
        db.collection("Groups")
                .add(group)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {



                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(CreateGroupController.this, "added successfully.", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(CreateGroupController.this, "not added successfully.", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                 selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    GroupPic.setImageURI(selectedImageUri);
                }
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), profileController.class));
                return true;
            case R.id.Settings:
                Toast.makeText(this, " settings not ready yet !", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.LogOut:
                fAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginController.class));
                return true;
            case R.id.CreateGroup:
                startActivity(new Intent(getApplicationContext(), CreateGroupController.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


}