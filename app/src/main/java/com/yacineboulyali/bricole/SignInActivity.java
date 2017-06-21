package com.yacineboulyali.bricole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    ArrayList<User> myUsers;
    public Button myButton;
    public EditText login;
    public EditText password;
    public Boolean userIn = false;
    public User newUser;
    String strLogin;
    String strPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        /*------------ArrayList small database----------*/
        myUsers = new ArrayList<>();


        myUsers.add(0, new User("Zakaria", "pZakaria"));
        myUsers.add(1, new User("Tarik", "pTarik"));
        myUsers.add(2, new User("Moad", "pMoad"));
        myUsers.add(3, new User("Hicham", "pHicham"));
        myUsers.add(4, new User("Khalid", "pKhalid"));
        myUsers.add(5, new User("Ahmed", "pAhmed"));
        myUsers.add(6, new User("Youssef", "pYoussef"));
        myUsers.add(7, new User("Ayoub", "pAyoub"));
        myUsers.add(8, new User("Amine", "pAmine"));
        myUsers.add(9, new User("z", "z"));

        /*--------------------------------------------*/

        myButton = (Button) findViewById(R.id.button);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.pass);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login.setText("");

            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password.setText("");

            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                strLogin = login.getText().toString();
                strPassword = password.getText().toString();
                newUser = new User(strLogin, strPassword);
                for (int j = 0; j < myUsers.size(); j++) {

                    if (myUsers.contains(newUser)) {
                        Intent i = new Intent(SignInActivity.this, ProfileActivity.class);
                        /*String str = login.getText().toString();
                        i.putExtra("Nom:", str);*/
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"bravo",Toast.LENGTH_LONG).show();
                        break;
                    }
                }


            }
        });


    }

}
