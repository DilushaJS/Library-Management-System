package com.djs.librarymanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MemberActivity extends AppCompatActivity {

    Button insertmemberbutton;
    EditText memname, memaddress, mempn, unpaid, pwd;
    Button insert, update, delete, view;
    DbHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        insertmemberbutton = findViewById(R.id.btnMInsert);
        insertmemberbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InsertMemberActivity.class);
                startActivity(i);
            }
        });
        memname = findViewById(R.id.mnametxt);
        memaddress = findViewById(R.id.maddresstxt);
        mempn = findViewById(R.id.mpnumtxt);
        unpaid   = findViewById(R.id.unpaidtxt);
        pwd = findViewById(R.id.pwtxt);
        DB = new DbHandler(this);
        update = findViewById(R.id.btnMUpdate); // Initialize the update button
        delete = findViewById(R.id.btnMDelete); // Initialize the delete button
        view = findViewById(R.id.btnMView); // Initialize the view button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = memname.getText().toString();
                String addressTXT = memaddress.getText().toString();
                String phoneTXT =mempn.getText().toString();
                String unpaidTXT = String.valueOf(Integer.parseInt(unpaid.getText().toString()));
                String pwdTXT =pwd.getText().toString();

                Boolean checkupdatedata = DB.updatememberdata(nameTXT, addressTXT, phoneTXT, unpaidTXT, pwdTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MemberActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MemberActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = memname.getText().toString();
                Boolean checkudeletedata = DB.deletememberdata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MemberActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MemberActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getmemberdata();
                if(res.getCount()==0){
                    Toast.makeText(MemberActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Card Number: LM"+res.getString(0)+"\n");
                    buffer.append("Member Name :"+res.getString(1)+"\n");
                    buffer.append("Member Address :"+res.getString(2)+"\n");
                    buffer.append("Member Contact Number :"+res.getString(3)+"\n");
                    buffer.append("Member's Unpaid Due:"+res.getString(4)+"\n");
                    buffer.append("Password:"+res.getString(5)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
