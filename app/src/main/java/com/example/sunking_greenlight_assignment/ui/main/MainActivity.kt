package com.example.sunking_greenlight_assignment.ui.main

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.BR
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseActivity
import com.example.sunking_greenlight_assignment.databinding.ActivityMainBinding
import com.example.sunking_greenlight_assignment.ui.main.fragments.country.CountryFragment
import com.example.sunking_greenlight_assignment.viewModelFactory.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var vmMain: MainViewModel


    override fun getViewModel(): MainViewModel {
        vmMain = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        return vmMain
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        fragments.forEach {
            if (it is CountryFragment) {
                supportFragmentManager.popBackStack(
                    "country",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
        }
        super.onBackPressed()
    }


    override fun onSetUp() {
        vmMain.setNavigator(this)
        vmMain.fragmentManager = supportFragmentManager
        vmMain.populateLocalData()

        findViewById<ImageView>(R.id.back_btn).setOnClickListener {
            onBackPressed()
        }
    }

    override fun showMessage(message: String) {
        runOnUiThread {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }
    }

}