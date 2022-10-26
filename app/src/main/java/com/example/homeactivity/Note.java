package com.example.homeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Note extends AppCompatActivity {

    TextView name_text,info_text;
    Task<Void> database2;
    Button delbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        name_text=findViewById(R.id.name_txt);
        info_text=findViewById(R.id.info_txt);
        delbtn=findViewById(R.id.delete_button);

        String a,b,c;
        Intent intent=getIntent();
        a=intent.getStringExtra("path");
        b=intent.getStringExtra("name");

        c=a.replace(b,"");
        c=c.replace(" ","");


        name_text.setText(a);
        info_text.setText(b);

        String finalC = c;
        delbtn.setOnClickListener(view -> {



            database2= FirebaseDatabase.getInstance().getReference().child(finalC).removeValue();


            Intent intent2=new Intent(this,add.class);
            startActivity(intent2);
            finish();

        });

    }
}