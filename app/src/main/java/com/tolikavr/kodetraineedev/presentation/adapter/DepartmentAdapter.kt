package com.tolikavr.kodetraineedev.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tolikavr.kodetraineedev.presentation.ui.viewpager.DepartmentFragment
import com.tolikavr.kodetraineedev.presentation.utils.ARG_POSITION
import com.tolikavr.kodetraineedev.presentation.utils.departmentName


class DepartmentAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

  override fun getItemCount(): Int = departmentName.size

  override fun createFragment(position: Int): Fragment {
    val fragment = DepartmentFragment()
    fragment.arguments = Bundle().apply {
      putInt(ARG_POSITION, position)
    }
    return fragment
  }

}