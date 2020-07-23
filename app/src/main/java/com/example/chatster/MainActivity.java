package com.example.chatster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

//    EditText userText = findViewById(R.id.e1);
//    EditText passText = findViewById(R.id.e2);
    private Button but;
    private EditText userText;
    private EditText passText;
    private Button but1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = findViewById(R.id.b2);
        userText = findViewById(R.id.e1);
        passText = findViewById(R.id.e2);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAct2();
            }
        });
        but1 = findViewById(R.id.b1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginCheck();
            }
        });
    }


    public void openAct2() {
        Intent intent = new Intent(this, Act2.class);

        startActivity(intent);
    }
    public void loginCheck() {
        Query query = ref.child("DatabaseElements").orderByChild("userName").equalTo(userText.getText().toString().trim());
//        Query query = ref.child("DatabaseElements").orderByChild("userName").equalTo("dope");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot user: dataSnapshot.getChildren()) {
                        DatabaseElements db = user.getValue(DatabaseElements.class);
                        if(db.getPassWord().equals(passText.getText().toString().trim())) {
                            Intent intent = new Intent(MainActivity.this, LoggedIn.class);
                            intent.putExtra("un", userText.getText().toString().trim());
                            userText.setText("");
                            passText.setText("");
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Wrong Password Entered!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
//                    String Q = query.toString();
                    Toast.makeText(getApplicationContext(), "Wrong Username Entered!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
