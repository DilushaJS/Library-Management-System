package com.djs.librarymanagementsystem;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    EditText memname, memaddress, mempn, pwd2, pwd;
    Button insert, update, delete, view;
    DbHandler DB;
    Button Reloginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Reloginbutton=findViewById(R.id.Reloginbutton);
        Reloginbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        memname = findViewById(R.id.txtUserName);
        memaddress = findViewById(R.id.txtAddress);
        mempn = findViewById(R.id.ContactTxtNumber);
        pwd = findViewById(R.id.txtPassword);
        pwd2 = findViewById(R.id.txtPassword2);
        insert = findViewById(R.id.Registerbutton2);
        DB = new DbHandler(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = memname.getText().toString();
                String addressTXT = memaddress.getText().toString();
                String phoneTXT =mempn.getText().toString();
                int unpaidTXT = 0;
                String pwdTXT =pwd.getText().toString();
                String pwd2TXT = pwd2.getText().toString();

                if (!pwdTXT.equals(pwd2TXT)) {
                    // Passwords don't match, show an error toast and return
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkinsertdata = DB.insertmemberdata(nameTXT, addressTXT, phoneTXT, unpaidTXT, pwdTXT);
                if(checkinsertdata==true) {
                    Toast.makeText(SignUpActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}