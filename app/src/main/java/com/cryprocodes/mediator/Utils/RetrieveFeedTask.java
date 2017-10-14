package com.cryprocodes.mediator.Utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yonifra on 12/10/17.
 */

public class RetrieveFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
        HttpURLConnection connection = null;
        URL object = null;
        try {
            object = new URL(urls[0]);
            connection = (HttpURLConnection) object.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Accept","application/json");
        connection.setRequestProperty("genre_id","1");
        connection.setRequestProperty("ep","89");

        try {
            StringBuilder stringBuilder = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader streamReader = new InputStreamReader(
                        connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(
                        streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response).append("\n");
                }
                bufferedReader.close();
                Log.d("Result Value ", stringBuilder.toString());
                JSONObject jsonResultText = new JSONObject(
                        stringBuilder.toString());

                // TODO: Read the actual value from response
                return jsonResultText.toString();
            } else {
                Log.e("Error = ", connection.getResponseMessage());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return null;
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
