package com.example.localjobs

import java.util.*

class WorkerModel (
    val id: Int  = getAutoId(),
    var name: String = "",
    var email: String = ""
){
    companion object {

        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}
