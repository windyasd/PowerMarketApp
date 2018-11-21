package comv.example.sunshine.powermarketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import comv.example.sunshine.powermarketapp.util.MyApplication;
import comv.example.sunshine.powermarketapp.util.PlantAdapter;
import comv.example.sunshine.powermarketapp.util.Plant_price;
import comv.example.sunshine.powermarketapp.util.Utility;

public class ShowActivity extends AppCompatActivity {
    private ScrollView priceScrollView;
    private TextView title;
    private TextView titleUpdateTime;
    private LinearLayout pricelistLayout;
    public List<Plant_price> pricelists=new ArrayList<>();
    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //初始化各控件
        priceScrollView=(ScrollView)findViewById(R.id.priceScrollView_layout);
        title=(TextView)findViewById(R.id.title);
        titleUpdateTime=(TextView)findViewById(R.id.title_update_time);
        pricelistLayout=(LinearLayout)findViewById(R.id.pricelist_layout);
        bingPicImg=(ImageView)findViewById(R.id.bing_pic_img);



        title.setText("电力市场软件");
        titleUpdateTime.setText("2018/11/17");


//        Utility.requestData("http://39.105.220.221:8080/MyFirstWebApp/QueryPrice",pricelists);



        //请求地址
        String url = "http://39.105.220.221:8080/MyFirstWebApp/QueryPrice";
        String tag = "QUERY";
        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());

//        requestQueue.cancelAll(tag);


        final StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Utility.handlePlantPrice(response,pricelists);
                            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_price);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(ShowActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            PlantAdapter adapter=new PlantAdapter(pricelists);
                            recyclerView.setAdapter(adapter);

                            priceScrollView.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Log.e("TAG", "onResponse: ", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG",error.getMessage(),error );
            }
        });

        //设置TAG标签
        request.setTag(tag);
        //将请求添加到队列中
        requestQueue.add(request);
    }
}
