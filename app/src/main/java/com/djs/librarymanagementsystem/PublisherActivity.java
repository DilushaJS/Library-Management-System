package com.djs.librarymanagementsystem;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PublisherActivity extends AppCompatActivity {
    EditText pname, paddress, ppn;
    Button insert, update, delete, view;
    DbHandler DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
        pname = findViewById(R.id.pnametxt);
        paddress = findViewById(R.id.paddresstxt);
        ppn = findViewById(R.id.ppnumtxt);
        insert = findViewById(R.id.btnPInsert);
        update = findViewById(R.id.btnPUpdate);
        delete = findViewById(R.id.btnPDelete);
        view = findViewById(R.id.btnPView);
        DB = new DbHandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pnameTXT = pname.getText().toString();
                String paddressTXT = paddress.getText().toString();
                String ppnTXT = ppn.getText().toString();

                Boolean checkinsertdata = DB.insertpubdata(pnameTXT, paddressTXT, ppnTXT);
                if(checkinsertdata==true)
                    Toast.makeText(PublisherActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PublisherActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pnameTXT = pname.getText().toString();
                String paddressTXT = paddress.getText().toString();
                String ppnTXT = ppn.getText().toString();

                Boolean checkupdatedata = DB.updatepubdata(pnameTXT, paddressTXT, ppnTXT);
                if(checkupdatedata==true)
                    Toast.makeText(PublisherActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PublisherActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pnameTXT = pname.getText().toString();
                Boolean checkudeletedata = DB.deletepubdata(pnameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(PublisherActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PublisherActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getpubdata();
                if(res.getCount()==0){
                    Toast.makeText(PublisherActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Publisher Name :"+res.getString(0)+"\n");
                    buffer.append("Publisher Address :"+res.getString(1)+"\n");
                    buffer.append("Publisher Contact Number :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(PublisherActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }
}