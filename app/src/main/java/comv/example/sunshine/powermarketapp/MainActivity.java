package comv.example.sunshine.powermarketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comv.example.sunshine.powermarketapp.util.MyApplication;
import comv.example.sunshine.powermarketapp.util.PlantAdapter;
import comv.example.sunshine.powermarketapp.util.Plant_price;
import comv.example.sunshine.powermarketapp.util.Utility;

public class MainActivity extends AppCompatActivity {

     public List<Plant_price> allPlantPrice=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




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
                            Toast.makeText(MainActivity.this,"response success",Toast.LENGTH_SHORT).show();
                            Utility.handlePlantPrice(response,allPlantPrice);
                            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            PlantAdapter adapter=new PlantAdapter(allPlantPrice);
                            recyclerView.setAdapter(adapter);
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
