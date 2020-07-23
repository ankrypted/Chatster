package com.example.chatster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Act2 extends AppCompatActivity {
    private EditText nameVal;
    private EditText ph;
    private EditText userName;
    private EditText passWord;
    private Button regBut;
    DatabaseElements db;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("DatabaseElements");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
        nameVal = (EditText)findViewById(R.id.er1);
        ph = (EditText)findViewById(R.id.er2);
        userName = (EditText)findViewById(R.id.er3);
        passWord = (EditText)findViewById(R.id.er4);
        regBut = (Button)findViewById(R.id.br1);
        db = new DatabaseElements();
//        ref = FirebaseDatabase.getInstance().getReference("DatabaseElements");
        regBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naam = nameVal.getText().toString();
                Long mob = Long.parseLong(ph.getText().toString());
                String useNaam = userName.getText().toString();
                String pass = passWord.getText().toString();
                if(ph.getText().toString().length() < 10)
                    //ph number should be atleast 10 digits long
                    Toast.makeText(Act2.this, "Please make sure that you enter your mobile number correctly!!", Toast.LENGTH_LONG).show();
                //else if() {
                    //check if username already exists
                  //  Toast.makeText(Act2.this, "Username already Exists!!", Toast.LENGTH_LONG).show();
                //}
                else {
                    //Successful Registration
                    db.setNameVal(naam);
                    db.setPh(mob);
                    db.setUserName(useNaam);
                    db.setPassWord(pass);
                    db.setMessage();
                    ref.child(useNaam).setValue(db);
                    Toast.makeText(Act2.this, "You Have Been Registered", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
