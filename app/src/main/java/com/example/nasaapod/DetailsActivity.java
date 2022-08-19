package com.example.nasaapod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DetailsActivity extends AppCompatActivity {
    int pos;
    TextView Txt_title,Txt_Date,Txt_explanation;
    ImageView image_view;
    URL url;
    URI uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        image_view = findViewById(R.id.imageView);
        Txt_title = findViewById(R.id.title);
        Txt_Date=findViewById(R.id.date);
        Txt_explanation=findViewById(R.id.explanation);
        if(getIntent().getExtras() !=null)
        {
            pos=getIntent().getIntExtra("pos",0);
        }
        Log.e("pos",""+pos);
        try {
            JSONObject jo= null;
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            //Log.e("ress",""+jsonArray);
                jo = jsonArray.getJSONObject(pos);

                String title=jo.getString("title");
                Txt_title.setText(title);
                String date=jo.getString("date");
                Txt_Date.setText(date);
                String exp=jo.getString("explanation");
                Txt_explanation.setText(exp);
                String imgurl= jo.getString("hdurl");


            //Log.e("img",""+imgurl);
            try {
                url = new URL(imgurl);
                uri = null;
                uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                url = uri.toURL();

            } catch (URISyntaxException | MalformedURLException e) {
                e.printStackTrace();
            }

            Picasso.get()
                    .load(imgurl)
                    .into(image_view);







        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}