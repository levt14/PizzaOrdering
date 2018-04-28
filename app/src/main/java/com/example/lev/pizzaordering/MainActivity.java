package com.example.lev.pizzaordering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText phoneNumber;
    EditText email;
    Button continue_btn;



    public  boolean isValidEmailAddress() {

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email.getText().toString());

        if(matcher.find() == false) {
            Toast.makeText(MainActivity.this, "Enter a correct Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public boolean areFieldsNotEmpty() {

        if(name.getText().toString().isEmpty() ||
                phoneNumber.getText().toString().isEmpty()
                || email.getText().toString().isEmpty())
        {
            Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isValidPhoneNumber() {

        if(phoneNumber.getText().toString().length() != 10) {
            Toast.makeText(MainActivity.this, "Enter a correct phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void next(View view) {

        if(!(areFieldsNotEmpty()) || !(isValidPhoneNumber()) || !(isValidEmailAddress())) {
            return;
        }

        Intent intent = new Intent(this, OrderingActivity.class);

        Client myClient = new Client();

        myClient.setName(name.getText().toString());
        myClient.setPhone(phoneNumber.getText().toString());
        myClient.setEmail(email.getText().toString());

        intent.putExtra("MyClass", myClient);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        phoneNumber = (EditText)findViewById(R.id.phone_number);
        email = (EditText)findViewById(R.id.email);
        continue_btn = (Button) findViewById(R.id.continue_btn);
    }
}
