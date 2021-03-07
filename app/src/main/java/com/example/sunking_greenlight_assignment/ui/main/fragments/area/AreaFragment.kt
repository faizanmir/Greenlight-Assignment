package com.example.sunking_greenlight_assignment.ui.main.fragments.area

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.BR
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseFragment
import com.example.sunking_greenlight_assignment.databinding.FragmentAreaBinding
import com.example.sunking_greenlight_assignment.ui.main.MainViewModel
import com.example.sunking_greenlight_assignment.viewModelFactory.ViewModelProviderFactory
import javax.inject.Inject

class AreaFragment : BaseFragment<FragmentAreaBinding, MainViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    var vmMain: MainViewModel? = null
    var areaAdapter: AreaAdapter? = null

    override fun getBindingVariable(): Int {
        return BR.vmMain
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_area
    }

    override fun getViewModel(): MainViewModel? {
        vmMain = ViewModelProvider(getBaseActivity()!!, factory).get(MainViewModel::class.java)
        return vmMain
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        areaAdapter = AreaAdapter {
            if (it.area != null) {
                vmMain?.selectedArea?.value  =  it
                vmMain?.switchToEmployees(it.territory!!)
            }
        }
        getViewDataBinding().areaRv.adapter = areaAdapter

        vmMain?.areaList?.observe(viewLifecycleOwner, {
            areaAdapter?.updateData(it)
        })

        getViewDataBinding().zoneHeader.setOnClickListener {
            isListReversed = if(isListReversed.not()) {
                vmMain?.sortListByDescending(vmMain?.areaList?.value!!)
                true
            }else {
                vmMain?.sortListAscending(vmMain?.areaList?.value!!)
                false
            }
        }





    }
}