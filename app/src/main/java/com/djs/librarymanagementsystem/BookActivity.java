package com.djs.librarymanagementsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BookActivity extends AppCompatActivity {

    EditText bookid, title, Pname;
    Button insert, update, delete, view;
    DbHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bookid = findViewById(R.id.bookid);
        title = findViewById(R.id.bookname);
        Pname = findViewById(R.id.pname);
        insert = findViewById(R.id.btnBInsert);
        update = findViewById(R.id.btnBUpdate);
        delete = findViewById(R.id.btnBDelete);
        view = findViewById(R.id.btnBView);
        DB = new DbHandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookidTXT = bookid.getText().toString();
                String titleTXT = title.getText().toString();
                String pnameTXT = Pname.getText().toString();

                Boolean checkinsertdata = DB.insertbookdata(bookidTXT, titleTXT, pnameTXT);
                if(checkinsertdata==true)
                    Toast.makeText(BookActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BookActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookidTXT = bookid.getText().toString();
                String titleTXT = title.getText().toString();
                String pnameTXT = Pname.getText().toString();

                Boolean checkupdatedata = DB.updatebookdata(bookidTXT, titleTXT, pnameTXT);
                if(checkupdatedata==true)
                    Toast.makeText(BookActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BookActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = bookid.getText().toString();
                Boolean checkudeletedata = DB.deletebookdata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(BookActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BookActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getbookdata();
                if(res.getCount()==0){
                    Toast.makeText(BookActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Book ID :"+res.getString(0)+"\n");
                    buffer.append("Book Title :"+res.getString(1)+"\n");
                    buffer.append("Publisher Name :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }
}