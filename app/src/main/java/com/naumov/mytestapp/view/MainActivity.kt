package com.naumov.mytestapp.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.view.View.OnLongClickListener
import android.view.ViewGroup.LayoutParams
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.naumov.mytestapp.App
import com.naumov.mytestapp.R
import com.naumov.mytestapp.common.media.AudioRecord
import com.naumov.mytestapp.core.CheckPermissionDescription
import com.naumov.mytestapp.databinding.ActivityMainBinding
import com.naumov.mytestapp.model.ViewStateData
import com.naumov.mytestapp.network.ManagerNetworkConnect
import com.naumov.mytestapp.utils.TAG
import com.naumov.mytestapp.utils.getAlertDialog
import com.naumov.mytestapp.utils.logDebug
import com.naumov.mytestapp.viewmodel.DataViewModel
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: DataViewModel
    val audioRecord: AudioRecord = AudioRecord()

    private var recordActive: Boolean = false
    private var successRecord = true

    private lateinit var gestureDetector: GestureDetector
    private val tr: Thread = thread(name = "clickFAB") {
        while (recordActive) {
            logDebug(TAG, "FAB is active")
        }
    }

    var showFab:Boolean=true

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

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.action_load_save, IndicatorFragment.getInstant(), "fragment_indicator")
//            .commitNow()



        initBottomApp()
        initialViewModel()
        initFabButton()
        initBottomSheet()

    }


    private fun initBottomSheet() {
        binding.layoutSheetResult.root.setBackgroundResource(R.drawable.round_korner)

        binding.layoutSheetResult.buttonSave.setOnClickListener {
            Toast.makeText(this.applicationContext, "save result to base", Toast.LENGTH_SHORT).show()
            binding.layoutSheetResult.resultTranscrib.text=""
            downstairsBottomSheet()
        }

        binding.layoutSheetResult.buttonCancel.setOnClickListener {
            Toast.makeText(this.applicationContext, "cancel to save", Toast.LENGTH_SHORT).show()
            downstairsBottomSheet()
        }

        binding.layoutSheetResult.closeView.setOnClickListener {
            downstairsBottomSheet()
        }

        var moveUp:Boolean = false

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.layoutSheetResult.bottomSheetContainer)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                logDebug("bottomSheetBehavior onStateChanged:", newState.toString())
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {

                        moveUp = true
                        logDebug("bottomSheetBehavior onSlide:", moveUp.toString())
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                        moveUp = false
                        logDebug("bottomSheetBehavior onSlide:",moveUp.toString())
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                logDebug("bottomSheetBehavior onSlide:", slideOffset.toString())

                val l = 100-((binding.toolbar.height/binding.root.height)*100)
                if (((slideOffset*100).toInt()>=l-15) && moveUp) {
                    logDebug("moveUp onSlide: ", "stop")
                    moveUp = !moveUp
                }
//                binding.fab.scaleX=1-slideOffset
//
//                if (1-slideOffset==0.0f){
//                    binding.fab.hide()
//                    showFab = false
//                }
//                if(1-slideOffset!=0.0f || !showFab)
//                {
//                    binding.fab.show()
//                    showFab=true
//                }


            }

        })
    }

    private fun upstairBottomSheet(){

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.layoutSheetResult.bottomSheetContainer)
        if (bottomSheetBehavior.state==BottomSheetBehavior.STATE_HALF_EXPANDED) {
            return
        }
        bottomSheetBehavior.isFitToContents = true
        bottomSheetBehavior.halfExpandedRatio = 0.5f
        bottomSheetBehavior.state  = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    private fun downstairsBottomSheet(){

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.layoutSheetResult.bottomSheetContainer)
        if (bottomSheetBehavior.state==BottomSheetBehavior.STATE_COLLAPSED) {
            return
        }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun initBottomApp() {


    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_bottom_app_bar,menu)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initFabButton() {


        binding.fab.setOnClickListener {
            logDebug(TAG, "short press FAB")
        }

        binding.fab.setOnTouchListener { _v, event ->
            if (event.action ==
                MotionEvent.ACTION_DOWN
            ) {

                logDebug(TAG, "start press FAB")
                recordActive = true

                showSaveFile(recordActive)
                upstairBottomSheet()



            }

            if (event.action == MotionEvent.ACTION_UP) {
                logDebug(TAG, "stop press FAB")
                recordActive = false

                showSaveFile(recordActive)

                if (!successRecord){
                    return@setOnTouchListener false
                }
                transcribFile()
            }
            return@setOnTouchListener false
        }

        binding.fab.setOnLongClickListener(object : OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                if (!ManagerNetworkConnect.instanceNet(App.cont)
                        .getStatusCurrentNetwork()
                ) {
                    if (v != null) {
                        Toast.makeText(v.context, "not connect", LENGTH_SHORT).show()
                        return true
                    }
                }


                if (v != null) {
                    Toast.makeText(v.context, "record is $recordActive", LENGTH_SHORT).show()

                    audioRecord.startRecord{ err ->
                        recordActive = false
                        successRecord = false
                        getAlertDialog(App.cont, "Save audio", err.message)
                    }
                }

                return true

            }
        })

    }

    private fun transcribFile() {
       lifecycleScope.launch { viewModel.transcribationFile() }
    }


    private fun initialViewModel() {
        val model: DataViewModel by lazy { ViewModelProvider(this)[DataViewModel::class.java] }
        viewModel = model
        viewModel.subscribe().observe(this@MainActivity) { renderData(it) }
    }

    fun renderData(dataTranslate: ViewStateData) {
        when (dataTranslate) {
            is ViewStateData.Loading -> {
                logDebug("renderData", "Loading")
                showLoading(true)
            }
            is ViewStateData.Success -> {
                logDebug("renderData", "Success")
                showLoading(false)
                showMessBox(dataTranslate.resultSpeech?._result)
                binding.layoutSheetResult.resultTranscrib.text = dataTranslate.resultSpeech?._result
            }

            is ViewStateData.ErrorService -> {
                showLoading(false)
                getAlertDialog(App.cont, "Error tranlate", dataTranslate.error.message)
                logDebug("Error tranlate", dataTranslate.error.message)
            }
            else -> {

            }
        }
    }

    private fun showMessBox(_result: String?) {

        Toast.makeText(App.cont, _result, LENGTH_SHORT).show()

    }

    private fun showSaveFile(isLoading:Boolean) {
        binding.contentProgressBarSaveFile.progressBar.visibility = if (isLoading){View.VISIBLE} else {View.GONE}
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
//            binding.content_loading.loadingBag.visibility = View.VISIBLE
        } else {
//            binding.contentLoading.loadingBag.visibility = View.GONE
        }

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