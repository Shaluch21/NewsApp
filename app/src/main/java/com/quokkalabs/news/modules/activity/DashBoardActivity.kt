package com.quokkalabs.news.modules.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.quokkalabs.news.R
import com.quokkalabs.news.databinding.ActivityDashBoardBinding
import com.quokkalabs.news.modules.fragment.home.HomeFragment
import com.quokkalabs.news.modules.fragment.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dash_board.*

@AndroidEntryPoint
class DashBoardActivity : AppCompatActivity(){

    private lateinit var binding: ActivityDashBoardBinding
    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbar.title = getString(R.string.app_name)

        binding.bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.homeFragment -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
                val fragment = HomeFragment()
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.searchFragment -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
                val fragment = SearchFragment()
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    override fun onBackPressed() {
        when {
            binding.bottomNavView.selectedItemId != R.id.homeFragment -> {
                binding.bottomNavView.selectedItemId = R.id.homeFragment
            }
            pressedTime + 2000 > System.currentTimeMillis() -> {
                super.onBackPressed()
                finishAffinity()
            }
            else -> {
                Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
        }
        pressedTime = System.currentTimeMillis()
    }


}