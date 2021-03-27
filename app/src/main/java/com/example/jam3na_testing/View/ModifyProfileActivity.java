package com.example.jam3na_testing.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jam3na_testing.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;

public class ModifyProfileActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    RadioGroup radioGroup ;
    RadioButton gender;
    EditText profileFirstName,profilePhone,profileLastName , UserAge , UserHeight , UserWeight;
    ImageView profileImageView;
    TextView profileEmail ;
    Button saveBtn;
    FirebaseAuth fAuth ;
    DatabaseReference reff;
    FirebaseFirestore fStore;
    String visitorID ;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile);

        profileFirstName = findViewById(R.id.profileFirstUsername);
        profileLastName = findViewById(R.id.profileLastUsername);
        profileEmail = findViewById(R.id.EmailProfile);
        profilePhone = findViewById(R.id.profilePhoneNumber);
        profileImageView = findViewById(R.id.profileImageView);
        saveBtn = findViewById(R.id.SaveData);
        radioGroup=findViewById(R.id.gender);
        UserAge = findViewById(R.id.UserAge);
        UserHeight= findViewById(R.id.UserHeight);
        UserWeight = findViewById(R.id.UserWeight);

        int redioId = radioGroup.getCheckedRadioButtonId() ;
        gender = findViewById(redioId);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        visitorID = fAuth.getCurrentUser().getUid();


        /**
         * Connection member Model to firebase datastore "Visitor" table to collect the data saved in sign up activity
         * */

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




    public void checkBoxGender(View view) {

        int redioId = radioGroup.getCheckedRadioButtonId() ;
        gender = findViewById(redioId);

        Toast.makeText(this, "Select Gender : ", Toast.LENGTH_SHORT).show();

    }
}