package com.example.sunking_greenlight_assignment.ui.main.fragments.employee

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.BR
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseFragment
import com.example.sunking_greenlight_assignment.databinding.FragmentEmployeeBinding
import com.example.sunking_greenlight_assignment.ui.main.MainViewModel
import com.example.sunking_greenlight_assignment.viewModelFactory.ViewModelProviderFactory
import javax.inject.Inject

class EmployeeFragment : BaseFragment<FragmentEmployeeBinding, MainViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    var vmMain: MainViewModel? = null
    var employeeAdapter: EmployeeAdapter? = null


    override fun getBindingVariable(): Int {
        return BR.vmMain
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_employee
    }

    override fun getViewModel(): MainViewModel? {
        vmMain = ViewModelProvider(getBaseActivity()!!, factory).get(MainViewModel::class.java)
        return vmMain
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        employeeAdapter = EmployeeAdapter()

        getViewDataBinding().employeeRv.adapter = employeeAdapter

        vmMain?.employeeList?.observe(viewLifecycleOwner, {
            if (it != null) {
                employeeAdapter?.updateData(it)
            }
        })



        getViewDataBinding().searchEt.doOnTextChanged(action = { query: CharSequence?, _: Int, _: Int, _: Int ->
            if(query!= null)
            {
                vmMain?.performEmployeeTextSearch(query.toString())
            }
        })

    }
}