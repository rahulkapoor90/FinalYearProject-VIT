package com.example.rahulkapoor90.garden;

import android.app.PendingIntent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.*;
import java.net.*;
import java.lang.*;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    String msg = "";
    EditText temp, humidity, moisture, gas, number;
    Button btn;
    SmsManager smsManager = SmsManager.getDefault();





    String tempstr, humiditystr, moisturestr, gasstr, numberstr;
    int tempflag, humidityflag, moistureflag, gasflag, numberflag;
    float result;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        temp = (EditText) findViewById(R.id.editText5);
        humidity = (EditText) findViewById(R.id.editText6);
        moisture = (EditText) findViewById(R.id.editText7);
        gas = (EditText) findViewById(R.id.editText8);
        number = (EditText) findViewById(R.id.editText9);
        btn = (Button) findViewById(R.id.button2);




        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
     //           smsManager.sendTextMessage(numberstr, null, msg, null, null);
//                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

                tempstr=temp.getText().toString();
                humiditystr=humidity.getText().toString();
                moisturestr=moisture.getText().toString();
                gasstr=gas.getText().toString();
                numberstr=number.getText().toString();

                msg = "Temp: " + tempstr +
                        "\nHumidity: " +
                        humiditystr +
                        "\nMoisture: " + moisturestr +
                        "\nGas: " + gasstr +"\n";


                tempflag=0; humidityflag=0; moistureflag=0; gasflag=0; numberflag=0; result=0;



                if(Integer.parseInt(tempstr) > 35 )
                    msg = msg +"High temperature\n";
                else if(Integer.parseInt(tempstr) < 15 )
                    msg = msg +"Low temperature\n";
                else tempflag=1;

                if(Integer.parseInt(gasstr) > 400 )
                    msg = msg +"Harmful gases\n";
                else gasflag=1;

                if(Integer.parseInt(moisturestr) < 300 )
                    msg = msg +"Low Soil Moisture\n";
                else moistureflag=1;

                if(Integer.parseInt(humiditystr) < 200 )
                    msg = msg +"Low humidity\n";
                else humidityflag=1;

                result = tempflag * 4 + moistureflag*3 + humidityflag*1 + gasflag*2;

                if(result >=7 ) msg = msg + "Good time to plant";
                else msg = msg + "Not a good time to plant";

//                if(result>=7) tv1.setText("Good time to plant");
//                else tv1.setText("Not a good time to plant");

                //Your authentication key
                String authkey = "111347ANDsZtRM1DZu57278d8e";
                //Multiple mobiles numbers separated by comma
                String mobiles = "8939455668";
                //Sender ID,While using route4 sender id should be 6 characters long.
                String senderId = "BALLEE";
                //Your message to send, Add URL encoding here.
                String message = "hi, thank you for placing order with Egarden.";
                //define route
                String route="transactional";

                URLConnection myURLConnection=null;
                URL myURL=null;
                BufferedReader reader=null;

                //encoding message
                String encoded_message=URLEncoder.encode(msg);

                //Send SMS API
                String mainUrl="https://control.msg91.com/api/sendhttp.php?";

                //Prepare parameter string
                StringBuilder sbPostData= new StringBuilder(mainUrl);
                sbPostData.append("authkey="+authkey);
                sbPostData.append("&mobiles="+mobiles);
                sbPostData.append("&message="+encoded_message);
                sbPostData.append("&route="+route);
                sbPostData.append("&sender="+senderId);

                //final string
                mainUrl = sbPostData.toString();
                try
                {
                    //prepare connection
                    myURL = new URL(mainUrl);
                    myURLConnection = myURL.openConnection();
                    myURLConnection.connect();
                    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

                    //reading response
                    String response;
                    while ((response = reader.readLine()) != null)
                        //print response
                        Log.d("RESPONSE", ""+response);

                    //finally close connection
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

            }
        });


    }

}