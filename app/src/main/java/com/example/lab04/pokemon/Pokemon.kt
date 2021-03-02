package com.example.lab04.pokemon
class Pokemon {
    var name:String = ""
    var base_experience:Int = 0
    var height:Int = 0
    var weight:Int = 0
    constructor(name:String, base_experience:Int, height:Int, weight:Int){
        this.name = name
        this.base_experience = base_experience
        this.height = height
        this.weight = weight
    }
}