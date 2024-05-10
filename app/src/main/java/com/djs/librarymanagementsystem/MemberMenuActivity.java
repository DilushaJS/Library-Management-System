package com.djs.librarymanagementsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MemberMenuActivity extends AppCompatActivity {

    Button bookview, branchview, pubview;
    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bookview = findViewById(R.id.showbookbtn);
        branchview = findViewById(R.id.showbranchbtn);
        pubview = findViewById(R.id.showpubbtn);
        DB = new DbHandler(this);

        bookview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getbookdata();
                if(res.getCount()==0){
                    Toast.makeText(MemberMenuActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Book ID :"+res.getString(0)+"\n");
                    buffer.append("Book Title :"+res.getString(1)+"\n");
                    buffer.append("Publisher Name :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MemberMenuActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Books We Have");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        branchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getbranchdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MemberMenuActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Branch ID :" + res.getString(0) + "\n");
                    buffer.append("Branch Name :" + res.getString(1) + "\n");
                    buffer.append("Address of Branch :" + res.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MemberMenuActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Branches We Have");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        pubview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getpubdata();
                if(res.getCount()==0){
                    Toast.makeText(MemberMenuActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Publisher Name :"+res.getString(0)+"\n");
                    buffer.append("Publisher Address :"+res.getString(1)+"\n");
                    buffer.append("Publisher Contact Number :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MemberMenuActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Details of Our Publishers");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}