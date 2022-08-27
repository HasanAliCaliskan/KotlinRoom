package com.hasanali.kotlinroom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.hasanali.kotlinroom.databinding.ActivityMainBinding
import com.hasanali.kotlinroom.model.User
import com.hasanali.kotlinroom.roomdb.UserDao
import com.hasanali.kotlinroom.roomdb.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userDb: UserDatabase
    private lateinit var userDao: UserDao
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val v = binding.root
        setContentView(v)
        userDb = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "Users").build()
        userDao = userDb.userDao()
    }

    fun save(view: View) {
        val user = User(binding.editTextFirstName.text.toString(), binding.editTextLastName.text.toString())
        compositeDisposable.add(
            userDao.insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse() {
        val intent = Intent(this,DetailActivity::class.java)
        finish()
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}