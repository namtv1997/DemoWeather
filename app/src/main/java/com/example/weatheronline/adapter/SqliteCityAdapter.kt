package com.example.weatheronline.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatheronline.R
import com.example.weatheronline.model.sqlite.CitySql
import kotlinx.android.synthetic.main.item_get_city.view.*

class SqliteCityAdapter(private var listCitySql: List<CitySql>, var onclickiItemListener: IclickItemGetCity) :
    RecyclerView.Adapter<SqliteCityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_get_city, parent, false))
    }

    override fun getItemCount(): Int = listCitySql.size

    override fun onBindViewHolder(hodel: ViewHolder, position: Int) {
        hodel.bind(listCitySql[position])
        hodel.itemView.setOnClickListener {
            onclickiItemListener.onItemClickGetCity(listCitySql.get(position))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(citySql: CitySql) = with(itemView) {
            tvItemGetCity.text = citySql.localizedName
        }

    }
}