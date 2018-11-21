package comv.example.sunshine.powermarketapp.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comv.example.sunshine.powermarketapp.R;

/**
 * Created by Sunshine on 2018/11/16.
 */

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private List<Plant_price> mPlantList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View showView;
        TextView plant_name;
        TextView morning_price;
        TextView afternoon_price;
        TextView night_price;
        Button button;

        public ViewHolder(View view){
            super(view);
            showView=view;
            plant_name=(TextView) view.findViewById(R.id.plant_name);
            morning_price=(TextView) view.findViewById(R.id.morning_price);
            afternoon_price=(TextView) view.findViewById(R.id.afternoon_price);
            night_price=(TextView) view.findViewById(R.id.night_price);
            button=(Button)view.findViewById(R.id.buy);
        }
    }

    public PlantAdapter(List<Plant_price> PlantList){
        mPlantList=PlantList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.showView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Plant_price plant_price=mPlantList.get(position);
                Toast.makeText(v.getContext(),"You clicked the plant:"+plant_price.getPlantName(),Toast.LENGTH_SHORT).show();

            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Plant_price plant_price=mPlantList.get(position);
                Toast.makeText(v.getContext(),"You are buying the plant:"+plant_price.getPlantName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plant_price plant_price=mPlantList.get(position);
        holder.plant_name.setText(plant_price.getPlantName());
        holder.morning_price.setText(String.valueOf(plant_price.getMorning_price()));//注意要转化为String
        holder.afternoon_price.setText(String.valueOf(plant_price.getAfternoon_price()));
        holder.night_price.setText(String.valueOf(plant_price.getNight_price()));


    }

    @Override
    public int getItemCount() {
        return mPlantList.size();
    }
}

