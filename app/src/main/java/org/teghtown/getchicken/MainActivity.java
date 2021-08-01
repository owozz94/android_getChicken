package org.teghtown.getchicken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView text_1, text_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);

        //네트워크 연결가능 test
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) { //네트워크 연결 가능여부
                Toast.makeText(getApplicationContext(), "Network is connected", Toast.LENGTH_LONG).show();
                new DownloadJson().execute(); // 필요한 경우 인자 전달
            } else {
                Toast.makeText(getApplicationContext(), "Network isn't connected", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            text_2.setText(e.toString());
        }
    }

    //getTicker api 가져옴
    private class DownloadJson extends AsyncTask<StringBuffer, StringBuffer, String> {
        @Override
        protected String doInBackground(StringBuffer... arg0) { //... :  복수의 데이터를 전송 가능
            try {
                GetChickenBithumbAPI getChickenBithumbAPI = new GetChickenBithumbAPI();
                StringBuffer sb = getChickenBithumbAPI.GetTicker("BTC", "KRW");

                String s = sb.toString(); //json object로 만들어야됨.
                JSONObject jsonObject = new JSONObject(s);
                JSONObject data = (JSONObject)jsonObject.get("data");
                String opening_price = (String)data.get("opening_price");

                return opening_price;

            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(String result) { //스레드 작업이 모두 끝난 후 수행할 작업
            text_2.setText(result);
        }

    }


}