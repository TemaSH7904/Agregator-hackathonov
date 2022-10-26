package com.example.homeactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<String> adapter;
    private List<String> listData;
    private  Button new_info;//Кнопка создания нового мероприятия
    public ArrayList<String> arr=new ArrayList<>();//Список куда добавляются записи из бд
    DatabaseReference database;
    String path,name,path2;//Для добавления мероприятия
    FirebaseAuth mAuth;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        listView=findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(adapter);

        database=FirebaseDatabase.getInstance().getReference();

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                database=FirebaseDatabase.getInstance().getReference();



                //Получение
                path=snapshot.getKey();
                String path2;

                path2=snapshot.getValue().toString();

                database=FirebaseDatabase.getInstance().getReference(path);
                name=snapshot.getValue().toString();



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        path=arr.get(i);


                        Intent intent3=new Intent(MainActivity.this,Note.class);
                                intent3.putExtra("path",path);
                                intent3.putExtra("name",name);
                                startActivity(intent3);

                    }
                });


                Intent intent=new Intent(MainActivity.this,Note.class);
                        intent.putExtra("path",path);
                intent.putExtra("name",name);
                arr.add(path+" "+name);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }

    public void addNewActivity (View v) {
        Intent intent = new Intent(this, add.class);
        startActivity(intent);

    }
    private void init() {
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
    }
}
