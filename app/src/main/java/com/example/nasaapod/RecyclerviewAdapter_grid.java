package com.example.nasaapod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerviewAdapter_grid extends RecyclerView.Adapter<RecyclerviewAdapter_grid.MyViewHolder> {


    private ArrayList<DataModel> dataArrayList;
    URL url;
    URI uri;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_view;
        public LinearLayout linear_layout_1;

        public MyViewHolder(View view) {
            super(view);

            image_view = view.findViewById(R.id.imageView);
            linear_layout_1=view.findViewById(R.id.linear_layoutgrid);

        }
    }


    public RecyclerviewAdapter_grid(Context context,ArrayList<DataModel> dataArray) {
        this.context=context;
        this.dataArrayList = dataArray;
    }

    @Override
    public RecyclerviewAdapter_grid.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);

        return new RecyclerviewAdapter_grid.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerviewAdapter_grid.MyViewHolder holder, int position) {
        DataModel dataModel = dataArrayList.get(position);
        String imgurl= dataModel.getUrl();


        //Log.e("img",""+imgurl);
        try {
            url = new URL(imgurl);
            uri = null;
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            url = uri.toURL();

        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }

        //Log.e("TEST", String.valueOf(url));
        Picasso.get()
                .load(imgurl)
                .into(holder.image_view);
        //  String crs_id=dataModel.getCourse_id();
       /* holder.linear_layout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SubjectActivity.class).putExtra("course_id", crs_id).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (dataArrayList != null) {
            return dataArrayList.size();
        } else {
            return 0;
        }
        //  return productList.size();
    }
}
