package com.atahar.githubapi.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.atahar.githubapi.R
import com.atahar.githubapi.utils.AppConfig
import com.atahar.githubapi.utils.AppConfig.ACCESS_TOKEN
import com.atahar.githubapi.utils.AppConfig.NAME
import com.atahar.githubapi.utils.AppConfig.SHARED_PREF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = getString(R.string.title_home_activity)
        initialize()
    }

    private fun initialize() {
        sharedPref = getSharedPreferences(
            SHARED_PREF, Context.MODE_PRIVATE
        )

        auth = FirebaseAuth.getInstance()
        val provider: OAuthProvider.Builder = OAuthProvider.newBuilder("github.com")
        val scopes: ArrayList<String?> = object : ArrayList<String?>() {
            init {
                add(AppConfig.OAUTH2_SCOPE)
            }
        }
        provider.scopes = scopes

        auth
            .startActivityForSignInWithProvider(this, provider.build())
            .addOnSuccessListener {

/*
                Toast.makeText(
                    this,
                    "Welcome ${auth.currentUser!!.displayName}",
                    Toast.LENGTH_SHORT
                ).show()
*/

                sharedPref.edit()
                    .putString(ACCESS_TOKEN, (it.credential as OAuthCredential).accessToken)
                    .putString(NAME, auth.currentUser!!.displayName)
                    .apply()

                openUserListFragment()

            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Error ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    private fun openUserListFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<UserListFragment>(R.id.fragment_container_view)
        }
    }


    fun openUserDetailsFragment(login: String) {
        val bundle = bundleOf("userId" to login)

        val fragment = UserDetailsFragment()
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }


}