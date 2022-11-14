package com.example.sevenminworkout

import android.app.Application
import com.example.sevenminworkout.Database.HistryDatabase

class WorkOutApp:Application() {
    val db:HistryDatabase by lazy {
        HistryDatabase.getInstance(this)
    }
}