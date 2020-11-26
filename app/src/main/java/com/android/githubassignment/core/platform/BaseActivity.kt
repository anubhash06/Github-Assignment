package com.android.githubassignment.core.platform


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.githubassignment.R
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_layout.*
import kotlinx.android.synthetic.main.layout_retry.*
import java.lang.Exception
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() , HasSupportFragmentInjector ,ActivityContract {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector


    private val handler by lazy {
        Handler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addLayout(savedInstanceState)
    }

    override fun onBackPressed() {
        try {
            (supportFragmentManager.findFragmentById(
                R.id.fragmentContainer
            ) as BaseFragment).onBackPressed()
        }catch (e: Exception){

        }

        super.onBackPressed()
    }

    private fun addLayout(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            LayoutInflater.from(this).inflate(layoutId(),fragmentContainer,true)
        }
    }


    abstract fun layoutId(): Int


    override fun showProgress() {
//        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
//        progressBar.visibility = View.GONE
    }

    override fun showError(message: String, retry: (() -> Unit)?) {
        tvError.text = message
        retryLayout.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
        btnRetry.visibility = if(retry == null) View.GONE else View.VISIBLE
        btnRetry.setOnClickListener {
            hideErrorLayout()
            retry?.invoke()
        }
        ivClose.setOnClickListener {
            hideErrorLayout()
        }
    }

    private fun hideErrorLayout() {
        fragmentContainer.visibility = View.VISIBLE
        retryLayout.visibility = View.GONE
    }

    override fun notify(@StringRes message: Int) =
        Snackbar.make(fragmentContainer, message, Snackbar.LENGTH_SHORT).show()

}

