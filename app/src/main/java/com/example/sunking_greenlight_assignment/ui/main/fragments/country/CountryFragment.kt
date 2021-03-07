package com.example.sunking_greenlight_assignment.ui.main.fragments.country

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.sunking_greenlight_assignment.BR
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseFragment
import com.example.sunking_greenlight_assignment.databinding.FragmentCountryBinding
import com.example.sunking_greenlight_assignment.ui.main.MainViewModel
import com.example.sunking_greenlight_assignment.viewModelFactory.ViewModelProviderFactory
import javax.inject.Inject

class CountryFragment : BaseFragment<FragmentCountryBinding, MainViewModel>() {
    lateinit var vmMain: MainViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory
    var countryAdapter: CountryAdapter? = null


    override fun getBindingVariable(): Int {
        return BR.vmMain
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_country

    }

    override fun getViewModel(): MainViewModel {
        vmMain = ViewModelProvider(getBaseActivity()!!, factory).get(MainViewModel::class.java)
        return vmMain
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = getViewDataBinding()

        countryAdapter = CountryAdapter {
            vmMain.selectedCountry.value =  it
            vmMain.switchToZone(it.territory!!)
        }


        vmMain.fetchCountries()

        binding.countryRv.adapter = countryAdapter

        vmMain.countryList.observe(viewLifecycleOwner, { list ->
            if (list != null) {
                countryAdapter!!.updateData(list)
            }
        })
    }
}