package com.tolikavr.kodetraineedev.presentation.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.tolikavr.kodetraineedev.data.network.NetworkResult
import com.tolikavr.kodetraineedev.databinding.DepartmentFragmentBinding
import com.tolikavr.kodetraineedev.presentation.adapter.UsersAdapter
import com.tolikavr.kodetraineedev.presentation.utils.ARG_POSITION
import com.tolikavr.kodetraineedev.presentation.utils.departmentList
import com.tolikavr.kodetraineedev.presentation.utils.querySearch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DepartmentFragment : Fragment() {

  private lateinit var binding: DepartmentFragmentBinding
  private lateinit var usersAdapter: UsersAdapter
  private val viewModel by viewModel<DepartmentFragmentViewModel>()
  private var position = ""

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DepartmentFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    usersAdapter = UsersAdapter()

    fetchUsers()

    with(binding.rv) {
      adapter = usersAdapter
      layoutManager = LinearLayoutManager(requireContext())
    }

    binding.refreshView.setOnRefreshListener {
      fetchUsers()
    }

    querySearch.observe(requireActivity()) {
      viewModel.handleSearchQuery(it)
    }

    arguments?.takeIf { it.containsKey(ARG_POSITION) }?.apply {
      position = getInt(ARG_POSITION).toString()
    }
  }

  private fun fetchUsers() {
    fetchResponse()
    fetchData()
  }

  private fun fetchResponse() {
    viewModel.getAllUsers()
  }

  private fun fetchData() {
    binding.refreshView.isRefreshing = true

    viewModel.response.observe(requireActivity()) { response ->
      when (response) {
        is NetworkResult.Loading -> {
          binding.refreshView.isRefreshing = false
          showProgressBar()
        }

        is NetworkResult.Success -> {
          binding.refreshView.isRefreshing = false
          hideProgressBar()
          viewModel.getUserData(departmentList[position.toInt()]).observe(requireActivity()) {
            usersAdapter.updateData(it)
          }
        }

        is NetworkResult.Error -> {
          binding.refreshView.isRefreshing = false
          hideProgressBar()
        }
      }
    }
  }

  private fun showProgressBar() {
    binding.progressBar.visibility = View.VISIBLE
  }

  private fun hideProgressBar() {
    binding.progressBar.visibility = View.GONE
  }
}