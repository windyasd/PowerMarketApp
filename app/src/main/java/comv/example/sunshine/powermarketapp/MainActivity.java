package comv.example.sunshine.powermarketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;



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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

     public List<Plant_price> allPlantPrice=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    * 发送网络请求
    * dependency:OkHttp3
    * method:get
    * */
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://39.105.220.221:8080/MyFirstWebApp/QueryPrice")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
//                    showResponse();
                }catch (Exception e){
                    Log.d("getError", "run: ",e);
                }
            }
        }).start();

    }

    /*
    * 处理返回数据，并进行UI操作
    * 要用到解析JSON数据的方法
    * */
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作
            }
        });

    }
}
