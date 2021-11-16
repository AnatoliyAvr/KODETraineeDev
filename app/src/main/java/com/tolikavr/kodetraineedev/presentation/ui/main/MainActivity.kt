package com.tolikavr.kodetraineedev.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import com.tolikavr.kodetraineedev.databinding.ActivityMainBinding
import com.tolikavr.kodetraineedev.presentation.adapter.DepartmentAdapter
import com.tolikavr.kodetraineedev.presentation.utils.departmentName
import com.tolikavr.kodetraineedev.presentation.utils.querySearch


class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val searchItem = binding.include.searchView

    searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        return false
      }

      override fun onQueryTextChange(query: String): Boolean {
        querySearch.value = query
        return false
      }
    })

    val tabLayout = binding.tabLayout
    val viewpager = binding.viewpager

    viewpager.adapter = DepartmentAdapter(this)

    TabLayoutMediator(
      tabLayout, viewpager
    ) { tab, position ->
      tab.text = departmentName[position]
    }.attach()
  }
}