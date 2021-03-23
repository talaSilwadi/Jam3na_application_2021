package com.example.jam3na_testing.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.jam3na_testing.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();


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

}