package com.djs.librarymanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertMemberActivity extends AppCompatActivity {

    EditText memname, memaddress, mempn, unpaid, pwd;
    Button insert, update, delete, view;
    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_member);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        memname = findViewById(R.id.mnametxt);
        memaddress = findViewById(R.id.maddresstxt);
        mempn = findViewById(R.id.mpnumtxt);
        unpaid   = findViewById(R.id.unpaidtxt);
        pwd = findViewById(R.id.pwtxt);
        insert = findViewById(R.id.btnMInsert);
        DB = new DbHandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = memname.getText().toString();
                String addressTXT = memaddress.getText().toString();
                String phoneTXT =mempn.getText().toString();
                int unpaidTXT = Integer.parseInt(unpaid.getText().toString());
                String pwdTXT =pwd.getText().toString();

                Boolean checkinsertdata = DB.insertmemberdata(nameTXT, addressTXT, phoneTXT, unpaidTXT, pwdTXT);
                if(checkinsertdata==true) {
                    Toast.makeText(InsertMemberActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(InsertMemberActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}