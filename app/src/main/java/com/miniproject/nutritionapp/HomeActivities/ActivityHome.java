package com.miniproject.nutritionapp.HomeActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miniproject.nutritionapp.AdminActivities.ActivityAdminChat;
import com.miniproject.nutritionapp.ActivityChat;
import com.miniproject.nutritionapp.Keys;
import com.miniproject.nutritionapp.R;

public class ActivityHome extends AppCompatActivity implements Keys {

    String userId, chatroomId;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    CardView profile, bmi, diet_plan, feedback;
    TextView mWelcome;

    FloatingActionButton mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //bind all the views
        initiateWithID();

        //initiate firebase classes
        initiateFirebaseClasses();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityHome.this, "Profile", Toast.LENGTH_SHORT).show();
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityHome.this, "BMI", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityHome.this, BMICalculate.class));
            }
        });

        diet_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityHome.this, "Diet Plan", Toast.LENGTH_SHORT).show();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityHome.this, "Feedback", Toast.LENGTH_SHORT).show();
            }
        });

        mChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityChat.class);
                intent.putExtra(USER_CHATROOMID,chatroomId);
                intent.putExtra(MESSAGE_RUSERID,ADMIN_ID);
                startActivity(intent);
            }
        });

    }

    private void initiateWithID() {
        profile = findViewById(R.id.profile_card);
        bmi = findViewById(R.id.bmi_card);
        diet_plan = findViewById(R.id.diet_plan_card);
        feedback = findViewById(R.id.feedback_card);

        mWelcome = findViewById(R.id.welcome);

        mChat = findViewById(R.id.fab_chat);
    }

    private void initiateFirebaseClasses() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        fStore.collection(Keys.USER_COLLECTION)
                .document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String name = documentSnapshot.getString(Keys.USER_NAME);
                        chatroomId = documentSnapshot.getString(USER_CHATROOMID);
                        mWelcome.setText("Welcome " + name);
                    }
                });
    }

}