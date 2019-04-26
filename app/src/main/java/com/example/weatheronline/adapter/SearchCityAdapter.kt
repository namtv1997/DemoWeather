package com.example.weatheronline.adapter



import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatheronline.R
import com.example.weatheronline.model.cityresult.CityResult
import com.example.weatheronline.model.weathercurentday.WeatherCurent
import kotlinx.android.synthetic.main.item_search_city.view.*

class SearchCityAdapter(private var listCity: ArrayList<CityResult>, var onclickiItemListener: IClickItemListener) :
    RecyclerView.Adapter<SearchCityAdapter.ViewHodel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodel {
        return ViewHodel(LayoutInflater.from(parent.context).inflate(R.layout.item_search_city, parent, false))
    }


    override fun getItemCount(): Int = listCity.size

    override fun onBindViewHolder(holdel: ViewHodel, position: Int) {
        holdel.bind(listCity[position])
        holdel.itemView.setOnClickListener {
            onclickiItemListener.onItemClick(listCity.get(position))
        }
    }

    inner class ViewHodel(itemview: View) : RecyclerView.ViewHolder(itemview) {


        fun bind(listCity: CityResult) = with(itemView) {
            tvItemCity.text = listCity.localizedName
        }

    }
}