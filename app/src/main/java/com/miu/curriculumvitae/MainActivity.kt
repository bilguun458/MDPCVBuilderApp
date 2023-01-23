package com.miu.curriculumvitae

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.miu.curriculumvitae.fragments.AboutFragment
import com.miu.curriculumvitae.fragments.ContactFragment
import com.miu.curriculumvitae.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val person = intent.getSerializableExtra("PERSON") as Person
        supportActionBar?.title = "CV Builder "

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment().newInstance(person), "Home")
        adapter.addFragment(AboutFragment().newInstance(person), "About")
        adapter.addFragment(ContactFragment().newInstance(person), "Contact")

        viewPager.adapter = adapter

        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m3 -> {
                val pref = getSharedPreferences("PREFEKEY", Context.MODE_PRIVATE)
                val edit = pref.edit();
                edit.remove("auth")
                edit.remove("email")
                edit.apply()
                finish()
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}