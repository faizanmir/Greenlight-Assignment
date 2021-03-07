package com.example.sunking_greenlight_assignment.ui.main.fragments.region

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.BR
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseFragment
import com.example.sunking_greenlight_assignment.databinding.FragmentRegionBinding
import com.example.sunking_greenlight_assignment.ui.main.MainViewModel
import com.example.sunking_greenlight_assignment.viewModelFactory.ViewModelProviderFactory
import javax.inject.Inject

class RegionFragment : BaseFragment<FragmentRegionBinding, MainViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    var vmMain: MainViewModel? = null
    var regionAdapter: RegionAdapter? = null

    override fun getBindingVariable(): Int {
        return BR.vmMain
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_region
    }

    override fun getViewModel(): MainViewModel? {
        vmMain = ViewModelProvider(getBaseActivity()!!, factory).get(MainViewModel::class.java)
        return vmMain
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        regionAdapter = RegionAdapter {
            vmMain?.selectedRegion?.value = it
            vmMain?.switchToArea(it.territory!!)
        }

        getViewDataBinding().regionRv.adapter = regionAdapter

        vmMain?.regionList?.observe(viewLifecycleOwner, {
            if (it != null) {
                regionAdapter?.updateData(it)
            }
        })


        getViewDataBinding().zoneHeader.setOnClickListener {
            isListReversed = if(isListReversed.not()) {
                vmMain?.sortListByDescending(vmMain?.regionList?.value!!)
                true
            }else {
                vmMain?.sortListAscending(vmMain?.regionList?.value!!)
                false
            }
        }


    }
}