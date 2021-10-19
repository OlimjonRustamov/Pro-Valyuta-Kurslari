package uz.olimjon_rustamov.provalyutakurslari.retrofit.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.dao.CurrencyDao
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse

@Database(
    entities = [CurrencyResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): CurrencyDao

    companion object {
        @Volatile
        private var database: AppDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "currency.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
        }
    }

    object get {
        fun getDatabase(): AppDatabase {
            return database!!
        }
    }
}