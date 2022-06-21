package com.ahnsh1996.keepit.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return uuid?.let { UUID.fromString(it) }
    }

    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return Gson().toJson(stringList)
    }

    @TypeConverter
    fun toStringList(jsonString: String): List<String> {
        return Gson().fromJson(jsonString, object: TypeToken<List<String>>() {}.type)
    }
}