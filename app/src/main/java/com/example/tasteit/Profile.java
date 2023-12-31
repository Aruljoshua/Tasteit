package com.example.tasteit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tasteit.Adapters.Insert_Data;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.tasteit.Loading_Animation.NoInternetDiaload;
import com.example.tasteit.LoginOrSignup.RigesterUser;

import java.util.Calendar;

import io.github.muddz.styleabletoast.StyleableToast;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_opener_image;
    LinearLayout contentView;
    TextView TextView_FullName_Main, TextView_UserName_Main;
    Button Profile_Update_Button;
    TextInputEditText EditInputEditText_DOB, EditInputEditText_Fullname, EditInputEditText_Gender, EditInputEditText_Password, EditInputEditText_Email, EditInputEditText_Age, EditInputEditText_Height, EditInputEditText_Weight;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String UserID;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (!checkInternet()) {
            NoInternetDiaload noInternetDialoag = new NoInternetDiaload(getApplicationContext());
            noInternetDialoag.setCancelable(false);
            noInternetDialoag.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            noInternetDialoag.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            noInternetDialoag.show();
        }
        findViws();
        navigationView();
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show();
            Intent intent3 = new Intent(getApplicationContext(), Splash_Login.class);
            startActivity(intent3);
        }else{
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            UserID = user.getUid();
            reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!checkInternet()) {
                        NoInternetDiaload noInternetDialoag = new NoInternetDiaload(getApplicationContext());
                        noInternetDialoag.setCancelable(false);
                        noInternetDialoag.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                        noInternetDialoag.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                        noInternetDialoag.show();
                    }
                    RigesterUser UserProfile = snapshot.getValue(RigesterUser.class);
                    if (UserProfile != null) {

                        String FullName = UserProfile._fullname;
                        String Email = UserProfile._email;
                        String Password = UserProfile._password;
                        String Gender = UserProfile._gender;
                        String Date = UserProfile._date;
                        int CureentYear = Calendar.getInstance().get(Calendar.YEAR);
//                    String Age = String.valueOf(UserProfile._birthyear);
                        int _age = CureentYear - UserProfile._birthyear;
                        String Age =String.valueOf(_age);


                        String Username = UserProfile._username;
                        TextView_FullName_Main.setText(FullName);
                        TextView_UserName_Main.setText(Username);
                        EditInputEditText_Fullname.setText(FullName);
                        EditInputEditText_Email.setText(Email);
                        EditInputEditText_Age.setText(Age);
                        EditInputEditText_Password.setText(Password);
                        EditInputEditText_Gender.setText(Gender);
                        EditInputEditText_DOB.setText(Date);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void findViws() {
        menu_opener_image = findViewById(R.id.menu_opener_image);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.content);
        TextView_FullName_Main = findViewById(R.id.TextView_FullName_Main);
        TextView_UserName_Main = findViewById(R.id.TextView_UserName_Main);
        EditInputEditText_Fullname = findViewById(R.id.EditInputEditText_Fullname);
        EditInputEditText_Email = findViewById(R.id.EditInputEditText_Email);
        EditInputEditText_Password = findViewById(R.id.EditInputEditText_Password);
        EditInputEditText_Age = findViewById(R.id.EditInputEditText_Age);
        Profile_Update_Button = findViewById(R.id.Profile_Update_Button);
        EditInputEditText_Gender = findViewById(R.id.EditInputEditText_Gender);
        EditInputEditText_DOB = findViewById(R.id.EditInputEditText_DOB);
        EditInputEditText_Height = findViewById(R.id.EditInputEditText_Height);
        EditInputEditText_Weight = findViewById(R.id.EditInputEditText_Weight);
    }

    //        Navigation Drawer Setting Start
    private void navigationView() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.Navigation_bar_item_Profile);
        menu_opener_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();


    }


    private void animateNavigationDrawer() {
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(getResources().getColor(R.color.dark_red));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Navigation_bar_item_Home:
                Intent intent11 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent11);
                break;
            case R.id.Navigation_bar_item_Meal:
                Intent intent = new Intent(getApplicationContext(), meal_planner.class);
                startActivity(intent);
                break;
            case R.id.Navigation_bar_item_Tips:
                Intent intent1 = new Intent(getApplicationContext(), Tips.class);
                startActivity(intent1);
                break;
            case R.id.Navigation_bar_item_login:
                StyleableToast.makeText(getApplicationContext(), "You Are Already Logged in", Toast.LENGTH_SHORT, R.style.OnActivity).show();

                break;
            case R.id.Navigation_bar_item_logout:
                mAuth = FirebaseAuth.getInstance();
                FirebaseAuth.getInstance().signOut();
                StyleableToast.makeText(getApplicationContext(), "You Successfully Logged out", Toast.LENGTH_SHORT, R.style.OnActivity).show();

                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.Navigation_bar_item_Profile:
                StyleableToast.makeText(getApplicationContext(), "You Are Already On this Activity", Toast.LENGTH_SHORT, R.style.OnActivity).show();

                break;

            case R.id.Navigation_bar_item_Insert_Data_Screen:
                Intent intent8 = new Intent(getApplicationContext(), Insert_Data.class);
                startActivity(intent8);
                break;


        }
        return true;
    }
    //        Navigation Drawer Setting End
    private boolean checkInternet() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}