package com.moutamid.covidaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    SwitchCompat coughSwitch, feverSwitch, throatSwitch, breadthSwitch, headacheSwitch,
            covidSwitch;

    TextInputEditText spo2Edittext, heartRateEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        coughSwitch = findViewById(R.id.cough_switch);
        feverSwitch = findViewById(R.id.fever_switch);
        throatSwitch = findViewById(R.id.throat_switch);
        breadthSwitch = findViewById(R.id.breadth_switch);
        headacheSwitch = findViewById(R.id.headache_switch);
        covidSwitch = findViewById(R.id.covid_switch);
        spo2Edittext = findViewById(R.id.spo2_edittext);
        heartRateEdittext = findViewById(R.id.heartRate_edittext);

        findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
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

                if (heartRateEdittext.getText() == null) {
                    Toast.makeText(FormActivity.this, "Please enter Heart rate!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (heartRateEdittext.getText().toString().isEmpty()) {
                    Toast.makeText(FormActivity.this, "Please enter heart rate!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String JsonUrl = "https://covaccine2.herokuapp.com/";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.POST, JsonUrl, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String inline = response.toString();
                               //Toast.makeText(FormActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                                new Utils().showDialog(FormActivity.this,
                                        "Api response",
                                        ""+inline,
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
//                                try {
////                                    JSONObject obj = new JSONObject(response.toString());
//
//                                }
//                                catch (JSONException e) {
//
//                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();


                        params.put("Contact with confirmed",covidSwitch.isChecked() ? "1" : "0");
                        params.put("Headache",headacheSwitch.isChecked() ? "1" : "0");
                        params.put("Sore throat",throatSwitch.isChecked() ? "1" : "0");
                        params.put("Shortness of breath",breadthSwitch.isChecked() ? "1" : "0");
                        params.put("Cough",coughSwitch.isChecked() ? "1" : "0");
                        params.put("Fever",feverSwitch.isChecked() ? "1" : "0");
                        params.put("Male","1");
                        params.put("Age 60+","1");


                        return params;
                    }
                };
//                params.put("Contact with confirmed","1");
//                params.put("Headache","1");
//                params.put("Sore throat","1");
//                params.put("Shortness of breath","1");
//                params.put("Cough","1");
//                params.put("Fever","1");
//                params.put("Male","1");
//                params.put("Age 60+","1");
                // Access the RequestQueue through your singleton class.
                RequestQueue requestQueue = Volley.newRequestQueue(FormActivity.this);
                requestQueue.add(jsObjRequest);
//                new Utils().showDialog(FormActivity.this,
//                        "",
//                        "Cough: " + (coughSwitch.isChecked() ? "1" : "0")
//                                + "\nFever: " + (feverSwitch.isChecked() ? "1" : "0")
//                                + "\nSoar throat: " + (throatSwitch.isChecked() ? "1" : "0")
//                                + "\nShortness of breath: " + (breadthSwitch.isChecked() ? "1" : "0")
//                                + "\nHeadache: " + (headacheSwitch.isChecked() ? "1" : "0")
//                                + "\nContact with Covid: " + (covidSwitch.isChecked() ? "1" : "0")
//                                + "\nSpO2 value: " + spo2Edittext.getText().toString()
//                                + "\nHeart rate: " + heartRateEdittext.getText().toString()
//                        ,
//                        "",
//                        "",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        }, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        }, true);
            }
        });

    }
}