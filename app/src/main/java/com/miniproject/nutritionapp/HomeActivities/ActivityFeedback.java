package com.miniproject.nutritionapp.HomeActivities;

import static com.miniproject.nutritionapp.Keys.FEEDBACK;
import static com.miniproject.nutritionapp.Keys.FEEDBACK_COLLECTION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hsalf.smilerating.SmileRating;
import com.hsalf.smileyrating.SmileyRating;
import com.miniproject.nutritionapp.R;

public class ActivityFeedback extends AppCompatActivity {

    TextView textView;
    MaterialToolbar toolbar;
    AppCompatButton mSubmitFeedback;
    TextInputEditText mFeedback;
    SmileyRating smileRating;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    String rating;
    int rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //bind all the views
        initiateWithID();

        setupToolbar();

        //initiate firebase classes
        initiateFirebaseClasses();

        String text = "Drop us Feedback!";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.primaryColor));
        ss.setSpan(foregroundColorSpan, 8, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        mSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFeedback();
            }
        });

    }

    private void initiateWithID() {
        textView = findViewById(R.id.feedback_text1);
        toolbar = findViewById(R.id.toolbar_feedback);

        mSubmitFeedback = findViewById(R.id.submit_feedback);
        mFeedback = findViewById(R.id.feedback);
        smileRating = findViewById(R.id.smile_rating);
    }

    private void setupToolbar(){
        if (getSupportActionBar()==null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initiateFirebaseClasses() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    private void submitFeedback() {

        String userId = fAuth.getCurrentUser().getUid();
        String feedback = mFeedback.getText().toString();

        if (feedback.equals("")) {
            feedback = "";
        }

        rating = smileRating.getSelectedSmiley().name();
        rate = smileRating.getSelectedSmiley().getRating();

        Toast.makeText(this, rating + rate, Toast.LENGTH_SHORT).show();


//        fStore.collection(FEEDBACK_COLLECTION)
//                .document(userId)
//                .update(FEEDBACK, FieldValue.arrayUnion(feedback))
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(ActivityFeedback.this, "Feedback Submitted", Toast.LENGTH_SHORT).show();
//                    }
//                });

    }

}