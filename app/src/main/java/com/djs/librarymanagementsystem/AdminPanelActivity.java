package com.djs.librarymanagementsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminPanelActivity extends AppCompatActivity {

    Button bookbutton, branchbutton, pubbutton,memberbutton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_panel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bookbutton=findViewById(R.id.bookbutton);
        bookbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),BookActivity.class);
                startActivity(i);
            }
        });
        branchbutton=findViewById(R.id.branchbutton);
        branchbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),BranchActivity.class);
                startActivity(i);
            }
        });
        pubbutton=findViewById(R.id.pubbutton);
        pubbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),PublisherActivity.class);
                startActivity(i);
            }
        });

        memberbutton=findViewById(R.id.memberbutton);
        memberbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),MemberActivity.class);
                startActivity(i);
            }
        });
    }
}