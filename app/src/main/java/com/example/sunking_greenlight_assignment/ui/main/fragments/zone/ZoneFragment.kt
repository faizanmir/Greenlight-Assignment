package com.example.sunking_greenlight_assignment.ui.main.fragments.zone

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.BR
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseFragment
import com.example.sunking_greenlight_assignment.databinding.FragmentZoneBinding
import com.example.sunking_greenlight_assignment.ui.main.MainViewModel
import com.example.sunking_greenlight_assignment.viewModelFactory.ViewModelProviderFactory
import javax.inject.Inject

class ZoneFragment : BaseFragment<FragmentZoneBinding, MainViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    var vmMain: MainViewModel? = null
    var zoneAdapter: ZoneAdapter? = null

    override fun getBindingVariable(): Int {
        return BR.vmMain
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_zone
    }

    override fun getViewModel(): MainViewModel? {
        vmMain = ViewModelProvider(getBaseActivity()!!, factory).get(MainViewModel::class.java)
        return vmMain
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        zoneAdapter = ZoneAdapter {
            vmMain?.selectedZone?.value = it
            vmMain?.switchToRegion(it.territory!!)
        }

        getViewDataBinding().zoneRv.adapter = zoneAdapter

        vmMain?.zoneList?.observe(viewLifecycleOwner, {
            if (it != null) {
                zoneAdapter?.updateList(it)
            }
        })

        getViewDataBinding().zoneHeader.setOnClickListener {
            isListReversed = if (isListReversed.not()) {
                vmMain?.sortListByDescending(vmMain?.zoneList?.value!!)
                true
            } else {
                vmMain?.sortListAscending(vmMain?.zoneList?.value!!)
                false
            }

        }


    }
}