package com.example.dropdownlistpopupwindow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var placeTypes: ArrayList<String>
    private lateinit var placeTypesDropdownView: ListPopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dropDownHeight = 800
        placeTypes = getResources().getStringArray(R.array.place_types).toCollection(ArrayList())
        if (placeTypes.get(0) != getString(R.string.pick_place_type)) {
            placeTypes.add(0, getString(R.string.pick_place_type))
        }
        placeTypesDropdownView = ListPopupWindow(this)
        placeTypesDropdownView.setAdapter(ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, placeTypes))
        placeTypesDropdownView.setAnchorView(tv_place_type)
        placeTypesDropdownView.setHeight(dropDownHeight)
        placeTypesDropdownView.setModal(true)
        tv_place_type.post({
            placeTypesDropdownView.setWidth(tv_place_type.getMeasuredWidth())
        })
        placeTypesDropdownView.setOnItemClickListener({ adapterView, view, position, viewId ->
            tv_place_type.setText(placeTypes.get(position))
            placeTypesDropdownView.dismiss()
        })
        tv_place_type.setOnClickListener({
            placeTypesDropdownView.show()
        })

    }
}
