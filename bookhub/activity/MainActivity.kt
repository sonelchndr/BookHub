package com.sonelchndr.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sonelchndr.bookhub.fragment.DashboardFragment
import com.sonelchndr.bookhub.fragment.FavouritesFragment
import com.sonelchndr.bookhub.fragment.ProfilesFragment
import com.sonelchndr.bookhub.R
import com.sonelchndr.bookhub.fragment.AboutAppFragment

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar:Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView:NavigationView



    //for highlighting menuitems
    var previousMenuItem:MenuItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout=findViewById(R.id.drawerLayout)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.frame)
        navigationView=findViewById(R.id.navigationView)

        setUpToolbar()


        //for dashboard to open when app is created
       /* supportFragmentManager.beginTransaction()
            .replace(R.id.frame,DashboardFragment())
            .addToBackStack("Dashboard")
            .commit()
        supportActionBar?.title="Dashboard"*/

        openDashboard()


        val actionBarDrawerToggle=ActionBarDrawerToggle(
        this@MainActivity,
            drawerLayout, R.string.open_drawer
            , R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


        navigationView.setNavigationItemSelectedListener {


            //highlight
            if(previousMenuItem !=null)
            {
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it


            when(it.itemId)
            {
                R.id.dashboard ->
                {
                   // Toast.makeText(this@MainActivity,"clicked on dashboard",Toast.LENGTH_SHORT).show()
                   /* supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,DashboardFragment())
                        .addToBackStack("Dashboard")
                        .commit()

                    supportActionBar?.title="Dashboard"*/

                    openDashboard()

                    drawerLayout.closeDrawers()
                }
                R.id.favourites ->
                {
                    //Toast.makeText(this@MainActivity,"clicked on favourites",Toast.LENGTH_SHORT).show()

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            FavouritesFragment()
                        )
                        //.addToBackStack("Favourites")
                        .commit()

                    supportActionBar?.title="Favourites"

                    drawerLayout.closeDrawers()
                }
                R.id.profile ->
                {
                    //Toast.makeText(this@MainActivity,"clicked on profile",Toast.LENGTH_SHORT).show()

                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            ProfilesFragment()
                        )
                      //  .addToBackStack("Profile")
                        .commit()

                    supportActionBar?.title="profile"

                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp ->
                {
                    //Toast.makeText(this@MainActivity,"clicked on About app",Toast.LENGTH_SHORT).show()

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutAppFragment())
                        //.addToBackStack("About app")
                        .commit()
                    supportActionBar?.title="About App"

                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }


    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if (id==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    //its is used two times so better create a function for better appearance
    fun openDashboard()
    {
        val fragment= DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()

            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack("Dashboard")
            transaction.commit()
             supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)

    }

    override fun onBackPressed() {
       // super.onBackPressed() //DEFAULT BEHAVIOUR OF BACK BUTTON so remove it

        val frag=supportFragmentManager.findFragmentById(R.id.frame)

        when(frag){
            !is DashboardFragment -> openDashboard()
        }



    }


}
