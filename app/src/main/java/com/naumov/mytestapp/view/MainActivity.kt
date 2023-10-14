package com.naumov.mytestapp.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.naumov.mytestapp.App
import com.naumov.mytestapp.R
import com.naumov.mytestapp.core.CheckPermissionDescription
import com.naumov.mytestapp.databinding.ActivityMainBinding
import com.naumov.mytestapp.model.ViewStateData
import com.naumov.mytestapp.network.ManagerNetworkConnect
import com.naumov.mytestapp.utils.DEBUG_ON
import com.naumov.mytestapp.utils.TAG
import com.naumov.mytestapp.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        checkPermission(Manifest.permission.READ_MEDIA_AUDIO)


        App.instanceNet.setRegisterStatusNetwork()

        initialViewModel()

        binding.fab.setOnLongClickListener {
            if (!ManagerNetworkConnect.instanceNet(App.cont)
                    .getStatusCurrentNetwork()
            ) {
                Toast.makeText(this, "not connect",LENGTH_SHORT ).show()
                return@setOnLongClickListener false
            }
            viewModel.onPressButtonRecord()

        }


    }

    private fun initialViewModel() {
        val model:DataViewModel  by lazy{ViewModelProvider(this)[DataViewModel::class.java]}
        viewModel = model
        viewModel.subscribe().observe(this@MainActivity){renderData(it)}
    }

    fun renderData(dataTranslate:ViewStateData){
        when (dataTranslate){
            is ViewStateData.Loading->{
                if (DEBUG_ON){
                    Log.d(TAG, "renderData: Loading")
                }
                showLoading(true)
            }
          is ViewStateData.Success->{
              if (DEBUG_ON){
                  Log.d(TAG, "renderData: Success")
              }
              showLoading(false)
              showMessBox(dataTranslate.resultSpeech?._result)
          }
            else -> {

            }
        }
    }

    private fun showMessBox(_result: String?) {


    }

    private fun showLoading(isLoading:Boolean) {
        TODO("Not yet implemented")
    }

    private fun checkPermission(permissionString: String) {
        if (checkSelfPermission(permissionString) == PackageManager.PERMISSION_GRANTED) {
            return
        } else {
            val description =
                CheckPermissionDescription().getDescriptionPermission(permissionString)
            if (shouldShowRequestPermissionRationale(permissionString)) {

                AlertDialog.Builder(
                    ContextThemeWrapper(
                        this, R.style.Theme_MyTestApp
                    )
                ).setTitle(description.getTitle())
                    .setMessage(description.getMessage())
                    .setPositiveButton(getString(R.string.open_permission_settings)) { _, _ ->
                        permissionRequest(permissionString, description.getKeyCode())
                    }
                    .setNegativeButton(getString(R.string.close_permission_settings)) { dialog, _ -> dialog.dismiss() }
                    .create().show()
            } else {
                permissionRequest(permissionString, description.getKeyCode())
            }
        }

    }

    private fun permissionRequest(stringRequest: String, codeRequest: Int) {
        requestPermissions(arrayOf(stringRequest), codeRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()

        App.instanceNet.deleteRegisterStatusNetwork()
    }
}