package com.example.jam3na_testing.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jam3na_testing.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateGroupController extends AppCompatActivity {


    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Button InsBtn,createBtn ;
    EditText GroupName,GroupDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        InsBtn=findViewById(R.id.ins_pic);
        createBtn=findViewById(R.id.createBtn);
        GroupDesc=findViewById(R.id.groupDescText);
        GroupName=findViewById(R.id.groupNameText);




        createBtn.setOnClickListener(e->{
            create_group_method();
        });




    }

    private void create_group_method() {
         String TAG = null;
    String Group_desc=GroupDesc.getText().toString();
    String Group_name=GroupName.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), profileController.class));
                return true;
            case R.id.Settings:
                Toast.makeText(this , " settings not ready yet !",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.LogOut:
                fAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginController.class));
                return true;
            case R.id.CreateGroup:
                startActivity(new Intent(getApplicationContext(),CreateGroupController.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}