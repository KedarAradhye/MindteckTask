package com.android.mindtecktask.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.android.mindtecktask.model.Imagemodel
import com.android.mindtecktask.model.Itemmodel
import com.android.mindtecktask.view.adapter.CustomAdapter
import com.android.mindtecktask.view.adapter.ViewPagerAdapter
import java.util.Locale.filter


class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var sliderDots: LinearLayout
    lateinit var dots: Array<ImageView?>
    var dotcounts: Int = 0
    var mPosition: Int = 0

    lateinit var recyclerview: RecyclerView
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: ArrayList<Imagemodel>
    lateinit var itemCategoryList: ArrayList<Itemmodel>
    lateinit var itemCategorySortedList: ArrayList<Itemmodel>
    lateinit var itemmodel: Itemmodel
    lateinit var imagemodel1: Imagemodel
    lateinit var data: ArrayList<Itemmodel>
    lateinit var customadapter: CustomAdapter
    lateinit var mSearchView: SearchView

    private fun filter(text: String) {
        val filteredlist: ArrayList<Itemmodel> = ArrayList()

        for (item in itemCategoryList) {
            if (item.text.toLowerCase().contains(text.toLowerCase())) {
                if(item.imageCategoryType == mPosition+1) {
                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            customadapter.filterList(filteredlist)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.android.mindtecktask.R.layout.activity_main)
        initViews();
        setViews();

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mPosition = position;
                for (i in 0 until dotcounts) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            com.android.mindtecktask.R.drawable.non_active_dot
                        )
                    )
                }
                dots[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        com.android.mindtecktask.R.drawable.active_dot
                    )
                )

                itemCategorySortedList.clear()
                for (items in itemCategoryList) {
                    if (items.imageCategoryType == position + 1) {
                        itemCategorySortedList.add(items)
                    }
                }
                customadapter = CustomAdapter(itemCategorySortedList)
                recyclerview.adapter = customadapter
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })


    }

    private fun setViews() {
        imageList = ArrayList<Imagemodel>()
        imagemodel1 = Imagemodel(
            1,
            "Froentend Languages",
            com.android.mindtecktask.R.drawable.ic_frontend_languages
        )
        imageList.add(imagemodel1)
        imagemodel1 = Imagemodel(
            2,
            "Backend Languages",
            com.android.mindtecktask.R.drawable.ic_backend_languages
        )
        imageList.add(imagemodel1)
        imagemodel1 =
            Imagemodel(3, "Database Languages", com.android.mindtecktask.R.drawable.ic_database)
        imageList.add(imagemodel1)

        viewPagerAdapter = ViewPagerAdapter(this@MainActivity, imageList)
        viewPager.adapter = viewPagerAdapter

        dotcounts = imageList.count()

        dots = arrayOfNulls<ImageView?>(dotcounts)

        for (i in 0 until dotcounts) {
            dots[i] = ImageView(this)
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    com.android.mindtecktask.R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDots.addView(dots[i], params)
        }

        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                getApplicationContext(),
                com.android.mindtecktask.R.drawable.active_dot
            )
        );

        itemCategoryList = ArrayList<Itemmodel>()
        itemCategorySortedList = ArrayList<Itemmodel>()

        itemmodel = Itemmodel(1, com.android.mindtecktask.R.drawable.ic_angular, "Angular", 1)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(2, com.android.mindtecktask.R.drawable.ic_swift, "Swift", 1)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(3, com.android.mindtecktask.R.drawable.ic_android, "Android", 1)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(4, com.android.mindtecktask.R.drawable.ic_react, "React JS", 1)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(5, com.android.mindtecktask.R.drawable.ic_c_sharp, "C#", 2)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(6, com.android.mindtecktask.R.drawable.ic_java, "java", 2)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(7, com.android.mindtecktask.R.drawable.ic_sql, "SQL", 3)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(8, com.android.mindtecktask.R.drawable.ic_mongodb, "Mongo DB", 3)
        itemCategoryList.add(itemmodel)
        itemmodel = Itemmodel(8, com.android.mindtecktask.R.drawable.ic_mysql, "MySql", 3)
        itemCategoryList.add(itemmodel)

        itemCategorySortedList.clear()
        for (items in itemCategoryList) {
            if (items.imageCategoryType == 1) {
                itemCategorySortedList.add(items)
            }
        }
        customadapter = CustomAdapter(itemCategorySortedList)
        recyclerview.adapter = customadapter

    }

    private fun initViews() {
        viewPager = findViewById(com.android.mindtecktask.R.id.viewPager)
        sliderDots = findViewById(com.android.mindtecktask.R.id.SliderDots)
        mSearchView = findViewById(com.android.mindtecktask.R.id.searchView)

        recyclerview = findViewById<RecyclerView>(com.android.mindtecktask.R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        data = ArrayList<Itemmodel>()

    }
}