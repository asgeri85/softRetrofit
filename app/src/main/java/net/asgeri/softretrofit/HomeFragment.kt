package net.asgeri.softretrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.asgeri.softretrofit.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val api = RetrofitClient.api
    private val todoAdapter = TodoAdapter()
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTodo.adapter = todoAdapter
        viewModel.getData()
        observeData()

    }

    private fun observeData(){
        viewModel.todos.observe(viewLifecycleOwner){
            todoAdapter.updateList(it)
        }
    }

    fun adYazdir() {
        Log.e("işlem", "OK")
        lifecycleScope.launch() {
            Log.e("işlem", "Birinci İşlem")

            withContext(Dispatchers.IO) {
                Log.e("işlem", "Burda IO")
            }

        }

        lifecycleScope.launch(Dispatchers.IO) {
            delay(3000)
            Log.e("işlem", "Bitti11")
        }


        GlobalScope.launch {
            delay(3000)
            Log.e("işlem", "Bitti22")
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}