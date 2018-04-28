package com.example.lev.pizzaordering;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class OrderingActivity extends AppCompatActivity {

    TextView hello;
    Client myClient;
    Pizza myPizza;
    static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    String message;


    protected void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", myClient.getEmail(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Details of your pizza order");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(emailIntent, ""));
    }



    protected void sendSMS() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != 1) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();

                    smsManager.sendTextMessage(myClient.getPhone(), null, message, null, null);
                    Toast.makeText(OrderingActivity.this, "Thank you for your order!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }


    public void setPrice(double price) {

        TextView priceView = (TextView) findViewById(R.id.price);

        priceView.setText(Double.toString(price) + " ILS");

    }

    public void priceCalculation(int someId, int price) {

        if(someId == R.id.small || someId == R.id.medium || someId == R.id.large) {
            RadioButton pizzaSize = (RadioButton) findViewById(someId);
            myPizza.priceSizeCalculation(price, pizzaSize);
        }
        else {
            CheckBox extra = (CheckBox) findViewById(someId);
            myPizza.priceExtraCalculation(price, extra);
        }
    }

    public void priceCalculation(View view) {

        switch(view.getId()) {
            case R.id.small: priceCalculation(R.id.small, 25); break;
            case R.id.medium: priceCalculation(R.id.medium, 40); break;
            case R.id.large: priceCalculation(R.id.large, 50); break;
            case R.id.olives: priceCalculation(R.id.olives, 2); break;
            case R.id.onion:  priceCalculation(R.id.onion, 2); break;
            case R.id.mushrooms: priceCalculation(R.id.mushrooms, 2); break;
            case R.id.tomatoes: priceCalculation(R.id.tomatoes, 2); break;
            case R.id.corn: priceCalculation(R.id.corn, 2); break;
            default:
        }



        setPrice(myPizza.sizeAndExtrasCalculation());
    }

    public void pay(View view) {


        if(myPizza.getPriceOfSize() == 0) {
            Toast.makeText(OrderingActivity.this, "Choose your pizza.", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println("Hi " + myClient.getName() + "!");
        System.out.println("Your pizza's order is:");
        System.out.println(myPizza.getSize());

        message = "Hi " + myClient.getName() + "!\n";
        message = message + "Your pizza's order is:\n" + myPizza.getSize() + "\n";

        if (!(myPizza.getExtras().isEmpty())) {
            System.out.println("with extras:");
            message = message + "with extras:\n";
            for (String ext : myPizza.getExtras()) {
                System.out.println(ext);
                message = message + ext + "\n";
            }
        }
        else {
            System.out.println("with no extras");
            message = message + "with no extras";
        }

        System.out.println("\nYou paid " + myPizza.getTotalPrice() + " ILS.");
        message = message + "\nYou paid " + myPizza.getTotalPrice() + " ILS.";


        sendSMS();

       sendEmail();


        initialization();
    }

    public void initialization() {
        setContentView(R.layout.activity_ordering);

        myClient = new Client();
        myPizza = new Pizza();


        hello = (TextView)findViewById(R.id.hello);

        Intent i = getIntent();
        myClient = (Client)i.getSerializableExtra("MyClass");

        hello.setText("Hello " + myClient.getName() + "!");

        myPizza.clearExtras();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
    }
}
