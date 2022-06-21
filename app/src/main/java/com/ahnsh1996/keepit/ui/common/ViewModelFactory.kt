package com.ahnsh1996.keepit.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahnsh1996.keepit.database.AppDatabase
import com.ahnsh1996.keepit.repository.KeepDataLocalDataSource
import com.ahnsh1996.keepit.repository.KeepDataRepository
import com.ahnsh1996.keepit.viewmodel.HomeViewModel
import com.ahnsh1996.keepit.viewmodel.KeepViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val appDatabase = AppDatabase.getInstance(context)
                val keepDataLocalDataSource = KeepDataLocalDataSource(appDatabase.keepDataDao())
                val keepDataRepository = KeepDataRepository.getInstance(keepDataLocalDataSource)
                HomeViewModel(keepDataRepository) as T
            }
            modelClass.isAssignableFrom(KeepViewModel::class.java) -> {
                val appDatabase = AppDatabase.getInstance(context)
                val keepDataLocalDataSource = KeepDataLocalDataSource(appDatabase.keepDataDao())
                val keepDataRepository = KeepDataRepository.getInstance(keepDataLocalDataSource)
                KeepViewModel(keepDataRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}