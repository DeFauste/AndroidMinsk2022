package com.example.weather.fragment.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.database.City
import com.example.weather.databinding.ItemCityBinding

class CityAdapter(private val onClickListenerCity: onClickListenerCity) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    inner class CityViewHolder(val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.cityName == newItem.cityName
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var cites: List<City>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount(): Int = cites.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.CityViewHolder {
        return CityViewHolder(ItemCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.apply {
            val city = cites[position]
            txtCityName.text = city.cityName
            chbCurCity.isChecked = city.checkCity == 1

            txtCityName.setOnClickListener {
                onClickListenerCity.onClick(city.cityName)
            }
        }
    }
}