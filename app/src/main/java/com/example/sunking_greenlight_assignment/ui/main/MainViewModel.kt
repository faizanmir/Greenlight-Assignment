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

    /***
     * This view model will act as a holder for mutable live data for
     * holding reference to mutableLiveData which will be used by fragments
     * for UI inflation
     * **/

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


    /**
     * This function triggers api request which which is used to populate the data for the first time
     * and then consecutively will check if sync has occurred, While sync failure conditions can be made
     * better ,Due to time limitation I have simply coded it to check if countries have been populated in the local DB
     * **/
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
                //This check can be made better to check data sanctity ,Right now I am jusr checking if countries have been fetched
                //and assume that fetch as happened
                viewModelScope.launch {
                    dataManager.getAllCountries().collect{
                        if(it.isEmpty())
                        {
                            getNavigator()?.showMessage("Oops sync encountered an error ,Please try again")
                            dataManager.setPerformSync(true)
                        }else {
                            switchToCountries()
                            dataManager.setPerformSync(false)
                        }
                    }
                }

            }
        }

    }


    /**
     *
     * This function is used to fetch list of countries from the repository which in-turn will
     * check for data in local db , same applies for all other functions which fetch other
     * parameters such as zones and areas
     *
     * **/
    fun fetchCountries() {
        viewModelScope.launch {
                withContext(IO) {
                    dataManager.getAllCountries().collect {
                        if(it.isNotEmpty()) {
                            countryList.postValue(it)
                        }

                    }
                }
        }
    }


    /**
     * All functions which fetch subcategories such as zone, region and area have a
     * @param query - which is used to run query in the local db once the db is populated
     */

    private fun fetchZone(query: String) {
        viewModelScope.launch {
            withContext(IO) {
                dataManager.getZonesWhereTerritoryIs(query).collect {
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


    //fetches countries first and then makes a fragment transaction
    private fun switchToCountries() {
        fetchCountries()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, CountryFragment())
            ?.addToBackStack("country")
            ?.commit()
    }


    //fetches zone first and then makes a fragment transaction
    fun switchToZone(query: String) {
        fetchZone(query)
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, ZoneFragment())
            ?.addToBackStack("zone")
            ?.commit()

    }

    //fetches regions first and then makes a fragment transaction
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

    //checks if the name is present in the list
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
                Log.d("TAG", "sortList:Type not found ", )
            }
        }
    }




    inline fun <reified T> sortListByDescending(list: List<T>)
    {

        when (T::class.java) {
            Country::class.java -> {
                val cList = list as List<Country>
                countryList.value = cList.sortedByDescending { country -> country.id }
            }
            Zone::class.java -> {
                val zList = list as List<Zone>
                zoneList.value = zList.sortedByDescending { zone -> zone.id }
            }
            Region::class.java -> {
                val rList = list as List<Region>
                regionList.value = rList.sortedByDescending { region: Region -> region.id }
            }
            Area::class.java -> {
                val aList = list as List<Area>
                areaList.value = aList.sortedByDescending { area -> area.id }
            }
            else -> {
                Log.d("TAG", "sortListByDescending:Type not found ",)
            }
        }

    }


    companion object{
        private const val TAG = "MainViewModel"

        fun writeCode()
        {

        }
    }


}






