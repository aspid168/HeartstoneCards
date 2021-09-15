package ru.study.HeartStoneCards

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import ru.study.HeartStoneCards.models.Card
import ru.study.HeartStoneCards.models.Categories

class  MainActivity : AppCompatActivity(), MainActivityNavigator {

    companion object {
        const val CLASS_NAME_EXTRA = "CLASS_NAME_EXTRA"
        const val MAIN_TRANSACTION = "MAIN_TRANSACTION"
    }

    private var toolBar: Toolbar? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    private lateinit var liveData: LiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)

        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)

        liveData = ViewModelProvider(this).get(LiveData::class.java)

        drawerToggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolBar,
                R.string.app_name,
                R.string.app_name //todo
        )
        drawerToggle?.let { drawerToggle -> drawerLayout?.addDrawerListener(drawerToggle) }

        liveData.classes.observe(this, {
            it.classes.forEach { currentClass ->
                navigationView?.menu?.add(currentClass)?.setOnMenuItemClickListener {
                    val listFragment = ListFragment()
                    val bundle = Bundle()
                    bundle.putString(CLASS_NAME_EXTRA, currentClass)
                    liveData.getCards(currentClass)
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                    supportFragmentManager.beginTransaction().add(R.id.mainContainer, listFragment, MAIN_TRANSACTION).commit()
                    drawerLayout?.closeDrawers()
                    !drawerLayout?.isDrawerVisible(GravityCompat.START)!!
                }
            }
        })

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    override fun goToCardDetailsFragment() {
        val cardDetailsFragment = CardDetailsFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, cardDetailsFragment)
                .addToBackStack(null)
                .commit()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        toolBar = null
//        drawerToggle = null
//        drawerLayout = null
//        navigationView = null
//        observer = null
//    }
}
