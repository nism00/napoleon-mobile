package com.example.napoleon_mobile

class Order {
    var name: String = ""
    var phone: String = ""
    var date: String = ""
    var time: String = ""

    constructor(name:String, phone: String, date: String, time: String) {
        this.name = name
        this.phone = phone
        this.date = date
        this.time = time
    }
}