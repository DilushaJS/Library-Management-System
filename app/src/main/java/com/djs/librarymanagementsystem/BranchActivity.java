package com.djs.librarymanagementsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BranchActivity extends AppCompatActivity {
    EditText branchid, branchname, braddress;
    Button insert, update, delete, view;
    DbHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        branchid = findViewById(R.id.branchid);
        branchname = findViewById(R.id.branchname);
        braddress = findViewById(R.id.braddress);
        insert = findViewById(R.id.btnBrInsert);
        update = findViewById(R.id.btnBrUpdate);
        delete = findViewById(R.id.btnBrDelete);
        view = findViewById(R.id.btnBrView);
        DB = new DbHandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String branchidTXT = branchid.getText().toString();
                String branchnameTXT = branchname.getText().toString();
                String braddressTXT = braddress.getText().toString();

                Boolean checkinsertdata = DB.insertbranchdata(branchidTXT, branchnameTXT, braddressTXT);
                if (checkinsertdata == true)
                    Toast.makeText(BranchActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BranchActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String branchidTXT = branchid.getText().toString();
                String branchnameTXT = branchname.getText().toString();
                String braddressTXT = braddress.getText().toString();

                Boolean checkupdatedata = DB.updatebranchdata(branchidTXT, branchnameTXT, braddressTXT);
                if (checkupdatedata == true)
                    Toast.makeText(BranchActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BranchActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = branchid.getText().toString();
                Boolean checkudeletedata = DB.deletebranchdata(nameTXT);
                if (checkudeletedata == true)
                    Toast.makeText(BranchActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BranchActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getbranchdata();
                if (res.getCount() == 0) {
                    Toast.makeText(BranchActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Branch ID :" + res.getString(0) + "\n");
                    buffer.append("Branch Name :" + res.getString(1) + "\n");
                    buffer.append("Address of Branch :" + res.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(BranchActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}