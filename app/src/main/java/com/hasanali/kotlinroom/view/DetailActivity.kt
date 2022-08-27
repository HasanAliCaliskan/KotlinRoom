package com.hasanali.kotlinroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.hasanali.kotlinroom.adapter.DetailAdapter
import com.hasanali.kotlinroom.databinding.ActivityDetailBinding
import com.hasanali.kotlinroom.model.User
import com.hasanali.kotlinroom.roomdb.UserDao
import com.hasanali.kotlinroom.roomdb.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var userDb: UserDatabase
    private lateinit var userDao: UserDao
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val v = binding.root
        setContentView(v)
        userDb = Room.databaseBuilder(applicationContext,UserDatabase::class.java,"Users").build()
        userDao = userDb.userDao()
        getData()
    }

    private fun getData() {
        compositeDisposable.add(
            userDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(list: List<User>) {
        val adapter = DetailAdapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}