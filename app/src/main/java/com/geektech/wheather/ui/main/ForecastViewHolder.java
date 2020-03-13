package com.geektech.wheather.ui.main;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geektech.wheather.R;
import com.geektech.wheather.data.pojo.CurrentWeather;
import com.geektech.wheather.R;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    private TextView tvMin, tvMax, tvTemp;
    private ImageView imageView;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTemp = itemView.findViewById(R.id.tvWeather);
        tvMin = itemView.findViewById(R.id.tvWeatherMin);
        tvMax = itemView.findViewById(R.id.tvWeatherMax);
        imageView = itemView.findViewById(R.id.imageviewItem);
    }

    @SuppressLint("SetTextI18n")
    public void bind(CurrentWeather currentWeather){
        tvTemp.setText(currentWeather.getMain().getTemp().toString());
        tvMax.setText(currentWeather.getMain().getTempMax().toString());
        tvMin.setText(currentWeather.getMain().getTempMin().toString());
        Glide.with(itemView).load("http://openweathermap.org/img/wn/" +
                currentWeather.getWeather().get(0).getIcon() + "@2x.png").centerCrop().into(imageView);
    }
}
