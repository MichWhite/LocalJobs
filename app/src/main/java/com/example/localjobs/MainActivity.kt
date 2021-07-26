package com.example.localjobs

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: WorkerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add_worker)

        initView()
        initRecyclerView()
        sqLiteHelper = SQLiteHelper(this)

        btnAdd.setOnClickListener {addWorker()}
        btnView.setOnClickListener {getWorker()}

    }

    private fun getWorker() {
        val wrkList = sqLiteHelper.getAllWorkers()
        Log.e("YO","{$wrkList.size}")
        adapter?.addItem(wrkList)
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edEmail = findViewById(R.id.edEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        recyclerView = findViewById(R.id.recyclerView)
    }


    private fun addWorker(){
        val name = edName.text.toString()
        val email = edEmail.text.toString()

        if(name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }
        else {
            val wrk = WorkerModel(name = name, email = email)
            val status = sqLiteHelper.insertWorker(wrk)

            if (status > -1) {
                Toast.makeText(this, "Worker Added", Toast.LENGTH_SHORT).show()
                clearEditText()
                getWorker()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()

            }
        }


    }

    private fun initRecyclerView(){
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter = WorkerAdapter()
        recyclerView.adapter = adapter
    }

    private fun clearEditText() {
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

}
