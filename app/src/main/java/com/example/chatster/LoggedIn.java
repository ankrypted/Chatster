package com.example.chatster;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoggedIn extends AppCompatActivity {
    private TextView textVal, msg, showName, showVal, myVal;
    private String S1;
    private ImageButton ib;
    private EditText user;
    private Button B;
    private TextView textVis;
    private EditText message;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    Boolean val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        Toast.makeText(getApplicationContext(), "Logged In Successfully!!" ,Toast.LENGTH_LONG).show();
        val = true;
        textVis = findViewById(R.id.t2);
        showName = findViewById(R.id.talkingTo);
        textVal = findViewById(R.id.t0);
        myVal = findViewById(R.id.messageBox1);
        user = findViewById(R.id.e1);
        message = findViewById(R.id.e2);
        ib = findViewById(R.id.imageButton1);
        S1 = getIntent().getStringExtra("un");
        showVal = findViewById(R.id.vis);
        textVal.setText(S1);
        B = findViewById(R.id.done);
        final Handler mHandler = new Handler();

        Runnable mToastRunnable = new Runnable() {
            @Override
            public void run() {
                Query query = ref.child("DatabaseElements").orderByChild("userName").equalTo(S1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot user: dataSnapshot.getChildren()) {
                            String X = (String)user.child("message").getValue();
                            msg = findViewById(R.id.messageBox);
                            msg.setText(X);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mHandler.postDelayed(this, 100);
            }
        };


        mToastRunnable.run();
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query1 = ref.child("DatabaseElements").orderByChild("userName").equalTo(user.getText().toString().trim());
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()) {
                            Toast.makeText(getApplicationContext(), "Please Enter a Valid Receiver Username" ,Toast.LENGTH_LONG).show();
                        }
                        else {
                            user.setVisibility(View.INVISIBLE);
                            B.setVisibility(View.INVISIBLE);
                            textVis.setVisibility(View.INVISIBLE);
                            showName.setVisibility(View.VISIBLE);
                            showName.setText(user.getText().toString().trim());
                            showVal.setVisibility(View.VISIBLE);
                            ib.setVisibility(View.VISIBLE);
                            message.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Username Found!!" ,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query1 = ref.child("DatabaseElements").orderByChild("userName").equalTo(user.getText().toString().trim());
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if(dataSnapshot.exists()) {
                            ref.child("DatabaseElements").child(user.getText().toString()).child("message").setValue("From: " + S1 + " -> "+ message.getText().toString().trim());
                            myVal.setText(message.getText().toString());
                            myVal.setVisibility(View.VISIBLE);
                            message.setText("");
                            Toast.makeText(getApplicationContext(), "Message Delivered!!" ,Toast.LENGTH_LONG).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Please Enter a Valid Receiver Username" ,Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
