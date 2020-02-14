package com.example.testresultapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testresultapp.R
import com.example.testresultapp.ui.utils.Status
import com.example.testresultapp.ui.utils.toast
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val viewModel2 by lazy { ViewModelProvider(this).get(MainViewModel2::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModels()

        button.setOnClickListener { viewModel.incrementCount() }
        button2.setOnClickListener { viewModel2.incrementCounter() }
    }

    private fun setupViewModels() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                toast(message = it.getOrNull() ?: "no message")
            } else {
                val err = it.exceptionOrNull()

                err?.let { error ->
                    error.printStackTrace()
                    toast(message = error.message ?: "no error message")
                }
            }
        })

        viewModel2.status.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Status.Success<*> -> toast(message = it.message)
                is Status.Failure -> {
                    it.err?.let { error ->
                        error.printStackTrace()

                        toast(message = error.message ?: "no error message from status error")
                    }
                }
            }
        })
    }
}
