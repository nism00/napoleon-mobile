package com.example.napoleon_mobile

class RequsetApi(var result: String, var message: String){
    companion object {
        val SUCCESS_RESULT = "success"
        val ERROR_RESULT = "error"
    }
}
class Menu(val id: Int, val name: String, val cost: String)

class Position(var id: Int, var name: String, var cost: String, var description: String, var image: String)
