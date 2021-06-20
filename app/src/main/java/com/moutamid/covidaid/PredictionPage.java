package com.moutamid.covidaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PredictionPage extends AppCompatActivity {

    TextView receiver_msg;
    TextView receiver_msg2;
    TextView receiver_msg3;
    TextView receiver_msg4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction_page);

        //recieve results
        receiver_msg = (TextView)findViewById(R.id.received_value_id);
        receiver_msg2 = (TextView)findViewById(R.id.received_value_id2);
        receiver_msg3 = (TextView)findViewById(R.id.received_value_id3);
        receiver_msg4 = (TextView)findViewById(R.id.received_value_id4);

        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        double result[] = intent.getDoubleArrayExtra("result");


        String str1 = Double.toString(result[0]);
        String str2="The Probability of being COVID +ve is: "+str1+"%";

        String str3 = Double.toString(result[1]);
        String str4="The Probability of being COVID +ve is: "+str3+"%";

        String str5 = Double.toString(result[2]);
        String str6="The Probability of being COVID +ve is: "+str5+"%";

        String str7 = Double.toString(result[3]);
        String str8="The Probability of being COVID +ve is: "+str7+"%";

        // display the string into textView
        receiver_msg.setText(str2);
        receiver_msg2.setText(str4);
        receiver_msg3.setText(str6);
        receiver_msg4.setText(str8);


        //Onclickingbuttons
        findViewById(R.id.Visualise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Visualisation.class);
                intent.putExtra("result",result);
                startActivity(intent);
            }
        });

        findViewById(R.id.Analyse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PredictionPage.this, Analyse1.class));
            }
        });
    }
}

