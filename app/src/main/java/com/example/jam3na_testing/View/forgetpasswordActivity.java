package com.example.jam3na_testing.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.jam3na_testing.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpasswordActivity extends AppCompatActivity {

    Button forgetPass ,back;
    TextView GoSignIn ;
    EditText EmailForget;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        forgetPass = findViewById(R.id.btnforgetpass);
        GoSignIn = findViewById(R.id.textViewSignIn);
        EmailForget =findViewById(R.id.inputForgetPass);
        back =findViewById(R.id.btnback);
        fAuth = FirebaseAuth.getInstance();


        /**
         * If the User want to back to Login activity
         * */

        GoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginController.class));
            }
        });
        /**
         * If User click to back Button ---- > open login activity
         * */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginController.class));
            }
        });
        /**
         * If User click to Next Button ---- > open new activity
         * */
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passwordForget = EmailForget.getText().toString();
                if (TextUtils.isEmpty(passwordForget)) {
                    EmailForget.setError("Email is Require");
                    return;
                }

                fAuth.sendPasswordResetEmail(passwordForget).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(forgetpasswordActivity.this , "Reset Link Sent to Your Email ",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(forgetpasswordActivity.this, "Error ! Link is Not Sent "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
            });



    }
    // Alert massage
    public class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.CheckUp)
                    .setPositiveButton(R.string.Done, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(getApplicationContext(),LoginController.class));
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(getApplicationContext(),RegisterController.class));

                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}