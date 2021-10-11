package ru.study.HeartStoneCards.presentation.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import ru.study.HeartStoneCards.R
import ru.study.HeartStoneCards.presentation.MainActivityNavigator
import ru.study.HeartStoneCards.presentation.view.fragment.CardDetailsFragment
import ru.study.HeartStoneCards.presentation.view.fragment.ListFragment
import ru.study.HeartStoneCards.presentation.viewModel.MainViewModel

class  MainActivity : AppCompatActivity(), MainActivityNavigator {

    companion object {
        const val CLASS_NAME_EXTRA = "CLASS_NAME_EXTRA"
        const val MAIN_TRANSACTION = "MAIN_TRANSACTION"
    }

    private var toolBar: Toolbar? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)

        toolBar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        drawerToggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolBar,
                R.string.app_name,
                R.string.app_name //todo
        )
        drawerToggle?.let { drawerToggle -> drawerLayout?.addDrawerListener(drawerToggle) }

        mainViewModel.classes.observe(this, {
            it.classes.forEach { currentClass ->
                navigationView?.menu?.add(currentClass)?.setOnMenuItemClickListener {
                    val listFragment = ListFragment()
                    val bundle = Bundle()
                    bundle.putString(CLASS_NAME_EXTRA, currentClass)
//                    mainViewModel.checkDisposeBag()
                    mainViewModel.getCards(currentClass)
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                    supportFragmentManager.beginTransaction().replace(R.id.mainContainer, listFragment, MAIN_TRANSACTION).commit()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu?.findItem(R.id.search_button)
        val searchView = menuItem?.actionView
        if (searchView is SearchView) {
            searchView.queryHint = "Search"
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        mainViewModel.changeClassData(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
        menuItem?.setOnActionExpandListener (object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                mainViewModel.returnClassData()
                return true
            }

        })
        return true
    }

    override fun goToCardDetailsFragment() {
        val cardDetailsFragment = CardDetailsFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, cardDetailsFragment)
                .addToBackStack(null)
                .commit()
    }
}
