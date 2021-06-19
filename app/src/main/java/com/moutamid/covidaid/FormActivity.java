package com.moutamid.covidaid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;



public class FormActivity extends AppCompatActivity {

    SwitchCompat coughSwitch, feverSwitch, throatSwitch, breadthSwitch, headacheSwitch,
            covidSwitch;

    TextInputEditText spo2Edittext, heartRateEdittext,temperature;

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        coughSwitch = findViewById(R.id.cough_switch);
        feverSwitch = findViewById(R.id.fever_switch);
        throatSwitch = findViewById(R.id.throat_switch);
        breadthSwitch = findViewById(R.id.breadth_switch);
        headacheSwitch = findViewById(R.id.headache_switch);
        covidSwitch = findViewById(R.id.covid_switch);
        spo2Edittext = findViewById(R.id.spo2_edittext);
        heartRateEdittext = findViewById(R.id.heartRate_edittext);
        temperature = findViewById(R.id.temperature);

        findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                if (spo2Edittext.getText() == null) {
                    Toast.makeText(FormActivity.this, "Please enter SpO2 value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spo2Edittext.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "Please enter SpO2 value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (temperature.getText() == null) {
                    Toast.makeText(FormActivity.this, "Please enter temperature value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (temperature.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "Please enter temperature value!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (heartRateEdittext.getText() == null) {
                    Toast.makeText(FormActivity.this, "Please enter Heart rate!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (heartRateEdittext.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "Please enter heart rate!", Toast.LENGTH_SHORT).show();
                    return;
                }


                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                Map<String, String> params = new HashMap<>();
                params.put("Contact with confirmed",covidSwitch.isChecked() ? "1" : "0");
                params.put("Headache",headacheSwitch.isChecked() ? "1" : "0");
                params.put("Sore throat",throatSwitch.isChecked() ? "1" : "0");
                params.put("Shortness of breath",breadthSwitch.isChecked() ? "1" : "0");
                params.put("Cough",coughSwitch.isChecked() ? "1" : "0");
                params.put("Fever",feverSwitch.isChecked() ? "1" : "0");
                params.put("Male","1");
                params.put("Age","1");
                String body1 = "{"+params.entrySet().stream() .map(e -> "\""+ e.getKey() + "\":\"" + String.valueOf(e.getValue()) + "\"") .collect(Collectors.joining(", "))+"}";
                RequestBody body = RequestBody.create(mediaType, body1);
                okhttp3.Request request;
                request = new okhttp3.Request.Builder()
                        .url("https://covaccine2.herokuapp.com/")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    //System.out.println(response.body().toString());
                   // JSONObject json;
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        String name = json.getString("result");
                        System.out.println(name);
                        new Utils().showDialog(FormActivity.this,
                                "",
                                ""+name,
                                "",
                                "",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }, true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=new HashMap<>();
//                params.put("Contact with confirmed",covidSwitch.isChecked() ? "1" : "0");
//                params.put("Headache",headacheSwitch.isChecked() ? "1" : "0");
//                params.put("Sore throat",throatSwitch.isChecked() ? "1" : "0");
//                params.put("Shortness of breath",breadthSwitch.isChecked() ? "1" : "0");
//                params.put("Cough",coughSwitch.isChecked() ? "1" : "0");
//                params.put("Fever",feverSwitch.isChecked() ? "1" : "0");
//                params.put("Male","1");
//                params.put("Age","1");
//                return params;
//            }
        });

    }
}