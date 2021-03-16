package com.example.jam3na_testing.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jam3na_testing.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
public class profileController extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText profileFirstName,profileEmail,profilePhone,profileLastName;
    ImageView profileImageView;
    Button saveBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String visitorID ;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        profileFirstName = findViewById(R.id.profileFName);
        profileLastName = findViewById(R.id.profileLName);
        profileEmail = findViewById(R.id.profileEmailAddress);
        profilePhone = findViewById(R.id.profilePhoneNo);
        profileImageView = findViewById(R.id.profileImageView);
        saveBtn = findViewById(R.id.saveProfileInfo);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        visitorID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Visitor").document(visitorID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                profileFirstName.setText(value.getString("fname"));
                profileLastName.setText(value.getString("lname"));
                profileEmail.setText(value.getString("Uemail"));
                profilePhone.setText(value.getString("Uphone"));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu , menu);
        return true ;
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
                startActivity(new Intent(getApplicationContext(), LoginController.class));
                return true;
            case R.id.CreateGroup:
                startActivity(new Intent(getApplicationContext(),CreateGroupController.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }}
}