package com.example.homeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends AppCompatActivity {
    Button add;//Кнопка добавления новой заявки
    DatabaseReference database;
    EditText head,info;//информация


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        add=findViewById(R.id.new_note);
        head=findViewById(R.id.header);
        info=findViewById(R.id.message);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance().getReference();
                String a,b;
                a=head.getText().toString();
                b=info.getText().toString();
                database.child(a).setValue(b);

                Intent intent=new Intent(com.example.homeactivity.add.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    public void goBack(View view) {
    }
}
