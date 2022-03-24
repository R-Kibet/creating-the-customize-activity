package com.example.anew

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anew.models.BoardSize
import com.example.anew.utils.EXTRA_SIZE

class CustomActivity : AppCompatActivity() {

    //reference views
    private lateinit var imageSelect: RecyclerView
    private lateinit var btnSave: Button
    private lateinit var gameName: EditText
    private lateinit var boardSize: BoardSize

    private var imagesRequired = -1
    private var chosenImage = mutableListOf<Uri>() // Uri - Uniform Resource Identifier - string that identifies where a particular resource leaves
                                                  //resource is image stored on the phone


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)


        //initialize referemces
        imageSelect = findViewById(R.id.imageSelect)
        btnSave = findViewById(R.id.btnSave)
        gameName = findViewById(R.id.gameName)

        //pulling data out from intent and cast it
        boardSize =  intent.getSerializableExtra(EXTRA_SIZE) as BoardSize

        imagesRequired =boardSize.getPairs()

        supportActionBar?.title = "choose the number (0/ $imagesRequired)"

        //setting up back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //contains 2 core components as the main activity
        //adapter and layout manager
        imageSelect.layoutManager = GridLayoutManager(this, boardSize.getWidth())
        imageSelect.setHasFixedSize(true)

        //adapter

        //it will take 3 parmaeters [Context (this) , list of chosen images through uri ,boardsize]
        imageSelect.adapter = imageSelectAdapter(this,chosenImage,boardSize)
    }


    //initializing the home button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { //contains android prefix as it was added
            finish()

            return true
        }
        return  super.onOptionsItemSelected(item)
    }


}