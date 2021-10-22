package uz.olimjon_rustamov.provalyutakurslari.app

import android.app.Application
import uz.olimjon_rustamov.provalyutakurslari.Cashe.Cashe
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        Cashe.init(this)
    }
}