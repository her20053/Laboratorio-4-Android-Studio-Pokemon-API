package com.example.lab04
class CheckString {
    private var string:String = ""
    constructor(string:String){
        this.string = string
    }
    fun checkChars(): Int {
        return  string.length
    }
    fun valNumbers(): Boolean{
        var wrongForNumber: Boolean = false
        for(l in string){
            when(l){
                '0','1','2','3','4','5','6','7','8','9' -> wrongForNumber = true
            }
        }
        return wrongForNumber
    }
    fun checkSpace() : Int {
        var count:Int = 0
        for(l in string){
            when(l){
                ' ' -> count++
            }
        }
        return count
    }
    fun fixString():String{
        var fixedName:String = ""
        for(l in string){
            when {
                l.isLowerCase() -> fixedName += l
                l.isUpperCase() -> fixedName += l.toLowerCase()
                else -> l
            }
        }
        return fixedName
    }
}