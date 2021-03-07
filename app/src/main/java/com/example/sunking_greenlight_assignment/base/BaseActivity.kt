package com.example.sunking_greenlight_assignment.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : DaggerAppCompatActivity() {

    private var mViewDataBinding: T? = null
    private var mViewModel: BaseViewModel<*>? = null
    abstract fun getViewModel(): V
    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        onSetUp()
    }


    open fun onSetUp() {
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings();
        mViewDataBinding?.lifecycleOwner = this
    }

   fun getBinding():ViewDataBinding?{
        return mViewDataBinding
    }


}