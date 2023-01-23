package com.miu.curriculumvitae

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val PREF = "PREFEKEY"
    var users = ArrayList<Person>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // generate data
        val person1: Person = Person(
            R.drawable.bilguun_avatar,
            "Bilguun",
            "Batnasan",
            "1234",
            "Full-stack Developer",
            "Software Engineer at Thermo Fisher Scientific, Inc (via KForce)\n",
            "https://github.com/bilguun458",
            Contact(
                "6418191619",
                "bbatnasan@miu.edu",
                "bilguun1020",
                "bilguun1020"
            ),
        )
        users.add(person1)


        val prefs = getSharedPreferences(PREF, MODE_PRIVATE)

        if (prefs.getBoolean("auth", false)) {
            val email = prefs.getString("email", "")
            if (email != null) {
                val foundUser = findUserByEmail(email)
                if (foundUser != null) {
                    startMainActivity(foundUser)
                }
            }
        }

        login.setOnClickListener {
            val inputEmail = et_email.text.toString()
            if (isValidated(inputEmail, et_password.text.toString())) {
                val editor = prefs.edit()

                editor.putBoolean("auth", true)
                editor.putString("email", inputEmail)
                editor.apply()

                val foundUser = findUserByEmail(inputEmail)
                if (foundUser != null) {
                    startMainActivity(foundUser)
                }


            } else {
                Toast.makeText(
                    applicationContext,
                    "Invalid credentials",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun startMainActivity(person: Person) {
        var mainIntent = Intent(applicationContext, MainActivity::class.java)

        mainIntent.putExtra("PERSON", person)
        startActivity(mainIntent)
    }

    private fun isValidated(email: String, password: String): Boolean {
        for (user in users) {
            if (user.contact.email == email && user.password == password) {
                return true
            }
        }
        return false
    }

    private fun findUserByEmail(email: String): Person? {
        for (user in users) {
            if (user.contact.email == email) {
                return user
            }
        }
        return null
    }
}