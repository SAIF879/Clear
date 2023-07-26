package com.example.clear.di

import android.content.Context
import androidx.room.Room
import com.example.clear.networkServices.CommonApiServices
import com.example.clear.room.dao.DictionaryDataBaseDao
import com.example.clear.room.dao.NoteDataBaseDao
import com.example.clear.room.dao.TodoDataBaseDao
import com.example.clear.room.database.ClearDataBase
import com.example.clear.utils.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideNotesDao(noteDataBase: ClearDataBase): NoteDataBaseDao = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun providesTodoDao(todoDataBase : ClearDataBase) : TodoDataBaseDao = todoDataBase.todoDao()

    @Singleton
    @Provides
    fun providesDictionaryDao(dictionaryDataBase:ClearDataBase) : DictionaryDataBaseDao = dictionaryDataBase.dictionaryDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): ClearDataBase =
        Room.databaseBuilder(context, ClearDataBase::class.java, "notes_db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesDictionaryApi(): CommonApiServices {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CommonApiServices::class.java)
    }
}
