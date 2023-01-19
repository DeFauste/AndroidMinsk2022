package com.example.weather.fragment.weather

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.ItemWeatherBinding
import com.example.weather.remote.data.Weather


class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var weathers: List<Weather>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount(): Int = weathers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.apply {
            val weather = weathers[position]
            txtCurDate.text = weather.dt_txt.replace(" ", "")
            txtTemperature.text = weather.main.temp.toInt().toString()
            txtWeatherSum.text = weather.weather[0].main
            val idImg = weather.weather[0].icon
            Glide
                .with(txtWeatherSum)
                .load("https://openweathermap.org/img/wn/$idImg@2x.png")
                .centerCrop()
                .placeholder(R.drawable.progress_bar)
                .into(imgIcWeather)
        }
    }
}