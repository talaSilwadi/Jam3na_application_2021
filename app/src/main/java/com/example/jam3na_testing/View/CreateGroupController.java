package com.example.jam3na_testing.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jam3na_testing.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateGroupController extends AppCompatActivity {


    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Button InsBtn,createBtn ;
    EditText GroupName,GroupDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_controller);
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
    String Group_desc=GroupDesc.getText().toString();
    String Group_name=GroupName.getText().toString();


    }

}