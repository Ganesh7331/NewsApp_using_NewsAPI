package com.example.newsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            binding.Progressbar.isVisible=true
            try{
                val response=RetrofitInstance.api.getTopHeadlines("us","API_KEY")
                if(response.isSuccessful){
                    var abc = response.body()!!.articles
                    setUpRecyclerView(abc)
                    binding.Progressbar.isVisible = false


                }
            }catch (e: IOException){

            }catch (e: HttpException){

            }

        }
    }
    private fun setUpRecyclerView(newss:List<Article>)=binding.Rvmain.apply{
        newsAdapter= NewsAdapter(newss)
        adapter= newsAdapter
        layoutManager= LinearLayoutManager(this@MainActivity)
        val itemDecoration = SpacesItemDecoration(20)
        binding.Rvmain.addItemDecoration(itemDecoration)
    }

}