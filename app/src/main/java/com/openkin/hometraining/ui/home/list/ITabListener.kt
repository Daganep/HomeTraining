package com.openkin.hometraining.ui.home.list

import com.google.android.material.tabs.TabLayout

interface ITabListener : TabLayout.OnTabSelectedListener {

    override fun onTabSelected(tab: TabLayout.Tab?)

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        //ничего не делать
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        //ничего не делать
    }
}
