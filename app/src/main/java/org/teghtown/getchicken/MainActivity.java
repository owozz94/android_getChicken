package org.teghtown.getchicken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_1 = findViewById(R.id.text_1);

        GetChickenBithumbAPI test = new GetChickenBithumbAPI();
        try {

            text_1.setText(test.GetTicker("BTC","KRW"));
        } catch (Exception e) {
            text_1.setText(e.toString());
            e.printStackTrace();
        }

    }

}