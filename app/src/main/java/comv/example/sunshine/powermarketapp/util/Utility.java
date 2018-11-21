package comv.example.sunshine.powermarketapp.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import comv.example.sunshine.powermarketapp.MainActivity;
import comv.example.sunshine.powermarketapp.R;

/**
 * Created by Sunshine on 2018/11/16.
 */

public class Utility {
    /*
    * 解析服务器返回的电厂数据
    *
    * */
    public static boolean handlePlantPrice(String response, List<Plant_price> PlantPriceList) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allPlantPrice = new JSONArray(response);
                for (int i = 0; i < allPlantPrice.length(); i++) {
                    JSONObject plantPriceObject = allPlantPrice.getJSONObject(i);
                    String plantName = plantPriceObject.getString("PlantName");
                    double morning = plantPriceObject.getDouble("morning");
                    double afternoon = plantPriceObject.getDouble("afternoon");
                    double night = plantPriceObject.getDouble("night");
                    Plant_price plantPrice = new Plant_price(plantName, morning, afternoon, night);
                    PlantPriceList.add(plantPrice);

                }
                return true;

            } catch (JSONException e) {
                Log.e("JSONException", "handlePlantPrice: ", e);
            }
        }
        return false;
    }

    /*
    * 从服务器获取数据
    *
    * */
    public static void requestData(String url, final List<Plant_price> PlantPriceList) {

        String tag = "QUERY";
        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(MyApplication.getContext());
        requestQueue.cancelAll(tag);

        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Utility.handlePlantPrice(response, PlantPriceList);

                        } catch (Exception e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Log.e("TAG", "onResponse: ", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        //设置TAG标签
        request.setTag(tag);
        //将请求添加到队列中
        requestQueue.add(request);
    }

}
