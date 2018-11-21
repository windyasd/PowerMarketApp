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



import java.util.ArrayList;
import java.util.List;

import comv.example.sunshine.powermarketapp.util.MyApplication;
import comv.example.sunshine.powermarketapp.util.PlantAdapter;
import comv.example.sunshine.powermarketapp.util.Plant_price;
import comv.example.sunshine.powermarketapp.util.Utility;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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

        sendRequestWithOkHttp();

    }


    /*
   * 发送网络请求--GET
   * dependency:OkHttp3
   * method:get
   * */
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    okhttp3.Request request=new okhttp3.Request.Builder()
                            .url("http://39.105.220.221:8080/MyFirstWebApp/QueryPrice")
                            .build();
                    okhttp3.Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData,pricelists);
                }catch (Exception e){
                    Log.d("getError", "run: ",e);
                }
            }
        }).start();
    }

    /*
   * 发送网络请求--POST
   * dependency:OkHttp3
   * method:post
   * */
    private void sendPostRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("username","admin")
                            .add("password","123456")
                            .build();
                    okhttp3.Request request=new okhttp3.Request.Builder()
                            .url("http://39.105.220.221:8080/MyFirstWebApp/QueryPrice")
                            .post(requestBody)
                            .build();
                    okhttp3.Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    //处理返回数据
                    showResponse(responseData,pricelists);
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
    private void showResponse(final String response,final List<Plant_price> pricelists){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作
                Utility.handlePlantPrice(response,pricelists);
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_price);
                LinearLayoutManager layoutManager=new LinearLayoutManager(ShowActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                PlantAdapter adapter=new PlantAdapter(pricelists);
                recyclerView.setAdapter(adapter);

                priceScrollView.setVisibility(View.VISIBLE);
            }
        });

    }
}
