package com.example.weatheronline.model.sqlite

class CitySql {
    var id:Int=0
    var key: String? = null
    var localizedName: String? = null

    constructor()
    constructor(key: String, localizedName: String) {
        this.key = key
        this.localizedName = localizedName
    }


}