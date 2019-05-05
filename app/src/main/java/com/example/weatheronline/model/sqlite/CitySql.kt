package com.example.weatheronline.model.sqlite

class CitySql {
    var id:Int=0
    var key: String? = null
    var localizedName: String? = null
    var localizedNameCountry: String? = null

    constructor()
    constructor(key: String, localizedName: String,localizedNameCountry: String) {
        this.key = key
        this.localizedName = localizedName
        this.localizedNameCountry=localizedNameCountry
    }


}