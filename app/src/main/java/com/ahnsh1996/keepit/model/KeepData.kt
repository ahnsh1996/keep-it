package com.ahnsh1996.keepit.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class KeepData(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val title: String = "",
    val creationDate: Date = Date(),
    val lastWriteDate: Date = Date(),
    val data: String = "",
    val webUrlList: MutableList<String> = mutableListOf(),
    val phoneList: MutableList<String> = mutableListOf(),
    val emailList: MutableList<String> = mutableListOf()
) : Parcelable