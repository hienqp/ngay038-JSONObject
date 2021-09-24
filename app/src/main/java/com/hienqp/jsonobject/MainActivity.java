package com.hienqp.jsonobject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadJSONObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
    }

    private class ReadJSONObject extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            StringBuilder stringBuilder = new StringBuilder();
            try {
                JSONObject jsonObject = new JSONObject(s);
                String monhoc = jsonObject.getString("monhoc");
                String noihoc = jsonObject.getString("noihoc");
                String website = jsonObject.getString("website");
                String fanpage = jsonObject.getString("fanpage");
                String logo = jsonObject.getString("logo");

                stringBuilder.append(monhoc).append("\n");
                stringBuilder.append(noihoc).append("\n");
                stringBuilder.append(website).append("\n");
                stringBuilder.append(fanpage).append("\n");
                stringBuilder.append(logo).append("\n");

                String string = stringBuilder.toString();

                Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}