package com.example.mynotes.db

import android.content.Context
import androidx.room.*

@Database(entities = [PhoneBook::class], version = 1, exportSchema = false)
abstract class PhoneBookDatabase : RoomDatabase() {

    abstract fun phoneBookDao(): PhoneBookDao

    companion object {
        @Volatile
        private var INSTANCE: PhoneBookDatabase? = null

        fun getDatabase(context: Context): PhoneBookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneBookDatabase::class.java,
                    "phone_book_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Entity(tableName = "phone_book_table")
data class PhoneBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val tag: String
)

@Dao
interface PhoneBookDao {

    @Query("SELECT * FROM phone_book_table")
    fun getAllPhoneBooks(): List<PhoneBook>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhoneBook(phoneBook: PhoneBook)

    @Update
    suspend fun updatePhoneBook(phoneBook: PhoneBook)

    @Delete
    suspend fun deletePhoneBook(phoneBook: PhoneBook)
}
