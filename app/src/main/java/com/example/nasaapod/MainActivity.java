package com.example.nasaapod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView gridView;
    private ArrayList<DataModel>data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.recycler_grid_view);
        try {
            JSONObject jo= null;
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            //Log.e("ress",""+jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                jo = jsonArray.getJSONObject(i);

               String url=jo.getString("url");

                DataModel dataModel = new DataModel(url);
                data.add(dataModel);
            }
            RecyclerviewAdapter_grid   recyclerviewAdapter= new RecyclerviewAdapter_grid(MainActivity.this,data);
            //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(new GridLayoutManager(CourseActivity.this, 2));
            gridView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            gridView.setItemAnimator(new DefaultItemAnimator());
            gridView.setAdapter(recyclerviewAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
