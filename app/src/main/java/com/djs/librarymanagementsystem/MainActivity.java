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

public class MainActivity extends AppCompatActivity {

    Button Registerbutton, Loginbutton, MloginButton, Forgotbutton;
    EditText txtun, txtpw;

    DbHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Loginbutton=findViewById(R.id.loginbutton);
        Forgotbutton=findViewById(R.id.Forgotbutton);
        txtun=findViewById(R.id.txtUserName);
        txtpw=findViewById(R.id.txtpwd);
        Registerbutton=findViewById(R.id.Registerbutton);
        Registerbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getApplicationContext(),BookActivity.class);
                        startActivity(i);
            }
        });

        DB = new DbHandler(this);


        Loginbutton = findViewById(R.id.loginbutton);
        Registerbutton = findViewById(R.id.Registerbutton);
        txtun = findViewById(R.id.txtUserName);
        txtpw = findViewById(R.id.txtpwd);


        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtun.getText().toString();
                String password = txtpw.getText().toString();


                if (username.equals("admin") && password.equals("admin123")) {
                    Intent i = new Intent(getApplicationContext(), AdminPanelActivity.class);
                    startActivity(i);
                } else {
                    if (DB.checkMemberCredentials(username, password)) {
                        Intent intent = new Intent(MainActivity.this, MemberMenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BookActivity when the register button is clicked
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
        Forgotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BookActivity when the register button is clicked
                Intent i = new Intent(getApplicationContext(), ForgotPWActivity.class);
                startActivity(i);
            }
        });
    }
}