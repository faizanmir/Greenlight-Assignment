package com.example.sunking_greenlight_assignment.ui.main

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sunking_greenlight_assignment.R
import com.example.sunking_greenlight_assignment.base.BaseViewModel
import com.example.sunking_greenlight_assignment.data.dataManager.DataManager
import com.example.sunking_greenlight_assignment.models.*
import com.example.sunking_greenlight_assignment.ui.main.fragments.area.AreaFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.country.CountryFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.employee.EmployeeFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.region.RegionFragment
import com.example.sunking_greenlight_assignment.ui.main.fragments.zone.ZoneFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel constructor(dataManager: DataManager) : BaseViewModel<MainNavigator>(dataManager) {


    var fragmentManager: FragmentManager? = null
    var countryList: MutableLiveData<List<Country>> = MutableLiveData(arrayListOf())
    var zoneList: MutableLiveData<List<Zone>> = MutableLiveData(arrayListOf())
    var regionList = MutableLiveData<List<Region>>(arrayListOf())
    var areaList = MutableLiveData<List<Area>>(arrayListOf())
    var employeeList = MutableLiveData<List<Employee>>(arrayListOf())
    private var employeeHolder = listOf<Employee>()


    var selectedCountry =  MutableLiveData<Country>()
    var selectedZone  = MutableLiveData<Zone>()
    var selectedRegion =  MutableLiveData<Region>()
    var selectedArea =  MutableLiveData<Area>()


    fun populateLocalData() {
        viewModelScope.launch {
            try {
                dataManager.fetchData().collect {
                    withContext(IO) {
                        if (dataManager.getShouldPerformSync()) {
                            dataManager.insertAllEmployees(it?.data?.employee!!)
                            dataManager.insertAllZones(it.data.zone!!)
                            dataManager.insertAllRegions(it.data.region!!)
                            dataManager.insertCountries(it.data.country!!)
                            dataManager.insertAllAreas(it.data.area!!)
                            dataManager.setPerformSync(false)
                            switchToCountries()
                            getNavigator()?.showMessage("One time data fetch complete")
                        } else {
                            getNavigator()?.showMessage("Showing offline data")
                            switchToCountries()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                getNavigator()?.showMessage("Oops something went wrong ,Please try again")
                switchToCountries()
                dataManager.setPerformSync(true)
            }
        }

    }


    fun fetchCountries() {
        viewModelScope.launch {
            try {
                withContext(IO) {
                    dataManager.getAllCountries().collect {
                        if(it.isNotEmpty()) {
                            countryList.postValue(it)
                        }else{
                            throw SyncFailedException()
                        }

                    }
                }
            }catch (e:Exception)
            {
                e.printStackTrace()
                getNavigator()?.showMessage(e.message!!)
                dataManager.setPerformSync(true)
            }
        }
    }

    private fun fetchZone(territory: String) {
        viewModelScope.launch {
            withContext(IO) {
                dataManager.getZonesWhereTerritoryIs(territory).collect {
                    zoneList.postValue(it)
                }
            }
        }
    }

    private fun fetchRegion(query: String) {
        viewModelScope.launch {
            withContext(IO)
            {
                dataManager.getRegionWhereTerritoryIs(query).collect {
                    regionList.postValue(it)
                }
            }
        }
    }

    private fun fetchArea(query: String) {
        viewModelScope.launch {
            withContext(IO) {
                dataManager.getAreaWhereTerritoryIs(query).collect {
                    areaList.postValue(it)

                }
            }
        }
    }

    private fun fetchEmployee(query: String) {
        viewModelScope.launch {
            withContext(IO) {
                dataManager.getEmployeeWhereTerritoryIs(query).collect {
                    employeeList.postValue(it)
                    employeeHolder = it
                }
            }
        }
    }

    private fun switchToCountries() {
        fetchCountries()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, CountryFragment())
            ?.addToBackStack("country")
            ?.commit()
    }


    fun switchToZone(query: String) {
        fetchZone(query)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, ZoneFragment())
            ?.addToBackStack("zone")
            ?.commit()

    }

    fun switchToRegion(query: String) {
        fetchRegion(query)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, RegionFragment())
            ?.addToBackStack("region")
            ?.commit()
    }


    fun switchToArea(query: String) {
        fetchArea(query)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, AreaFragment())
            ?.addToBackStack("area")
            ?.commit()
    }

    fun switchToEmployees(query: String) {
        fetchEmployee(query = query)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, EmployeeFragment())
            ?.addToBackStack("employee")
            ?.commit()

    }

    fun performEmployeeTextSearch(query:String)
    {
        if(query.isNotEmpty()) {
            val newList  =  arrayListOf<Employee>()
            employeeHolder.forEach {
                if (it.name?.toLowerCase(Locale.getDefault())?.contains(query.toLowerCase(Locale.getDefault()))!!) {
                    newList.add(it)
                }
            }
            employeeList.value =  newList
        }else {
         employeeList.value =  employeeHolder
        }
    }


    /**
     * Sorting lists ascending or descending
     * could also be done in the database but
    // since the data has already been fetched that would make an unnecessary
    //IO call hence this function deals with the already loaded list

     **/

    inline fun <reified T> sortListAscending( list: List<T>)
    {

        when (T::class.java){
            Country::class.java -> {
                val cList  = list as List<Country>
                countryList.value = cList.sortedBy { country -> country.id }
            }
            Zone::class.java -> {
                val zList  = list as List<Zone>
                zoneList.value =  zList.sortedBy {zone -> zone.id  }
            }
            Region::class.java -> {
                val rList =  list as  List<Region>
                regionList.value =rList.sortedBy{ region: Region ->region.id  }
            }
            Area::class.java ->{
                val aList  = list as List<Area>
                areaList.value =  aList.sortedBy { area -> area.id  }
            }
            else ->{
                Log.e("TAG", "sortListByDescending:Type not found ", )
            }
        }
    }




    inline fun <reified T> sortListByDescending(list: List<T>)
    {

        when (T::class.java){
            Country::class.java -> {
                val cList  = list as List<Country>
                countryList.value = cList.sortedByDescending { country -> country.id }
            }
            Zone::class.java -> {
                val zList  = list as List<Zone>
                zoneList.value =  zList.sortedByDescending {zone -> zone.id  }
            }
            Region::class.java -> {
                val rList =  list as  List<Region>
                regionList.value =rList.sortedByDescending{ region: Region ->region.id  }
            }
            Area::class.java ->{
                val aList  = list as List<Area>
                areaList.value =  aList.sortedByDescending { area -> area.id  }
            }
            else ->{
                Log.e("TAG", "sortListByDescending:Type not found ", )
            }
        }
    }


    companion object{
        private const val TAG = "MainViewModel"
    }
}






