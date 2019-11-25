package com.ba.streamreader

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.ba.streamreader.MainActivity.Companion.makeupList_JSONObject
import com.koushikdutta.ion.Ion
import com.skyfishjy.library.RippleBackground
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_view.*
import kotlinx.android.synthetic.main.image_item_layout.view.*
import kotlinx.android.synthetic.main.text_item_layout.view.*
import org.json.JSONObject
import java.io.Serializable
import kotlin.random.Random

class ItemViewActivity : AppCompatActivity() {

//    private val textColors = listOf( "#99ff00", "#cc3399", "#ffff33", "#66ccff", "#cc6600")
    private val textColors = listOf( Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.GREEN, Color.BLUE)

    data class ContentItem(
        val hasImage: Boolean,
        val title: String,
        val subText:String,
        val content:String): Serializable

    private  lateinit var currentMenuItem : HomeMenuActivity.MenuItem

    private lateinit var currentContentItem: ContentItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_view)

        if( intent != null ){
            this.currentMenuItem = intent.getSerializableExtra("menuItem") as HomeMenuActivity.MenuItem
            fetchAnother()
        }
    }

    private fun prepDisplay(){
        if( currentMenuItem != null){
            txt_descriptionTitle.text = currentMenuItem.name + " <<< Loading... "
            txt_descriptionDetails.text = currentMenuItem.description
            lay_itemBox.removeAllViews()
            val pb = findViewById<ProgressBar>( R.id.pb_progressBar)
            if( pb != null) lay_itemBox.addView( pb )
        }
    }

    fun fetchAnother(){
        // show progress bar
        prepDisplay()

        // Dirty blocking fetch
        if( currentMenuItem.name == "Makeup"){

//            if(!::makeupList_JSONObject.isInitialized){
//                Ion.with(this)
//                    .load(currentMenuItem.url)
//                    .asString()
//                    .setCallback() { e, res ->
//                        makeupList_JSONObject = "{ \"obj\" : $res }"
//                        doProcess( getRandomMakeupItem() )
//                    }
//            }else {
                doProcess(getRandomMakeupItem())
//            }

        }else {
            currentMenuItem.updateRandomURL()

            Ion.with(this)
                .load(currentMenuItem.url)
                .asString()
                .setCallback() { e, res ->
                    doProcess(res)
                }
        }
    }

    private fun getRandomMakeupItem() : String {
        val json = JSONObject( "$makeupList_JSONObject" )
        val ls = json.getJSONArray("obj")
        val res = ls.getString( Random.nextInt( ls.length() - 1 ) )
        return  res
    }

    fun doProcess(result:String){

        val json = JSONObject( result)

        var isImaged: Boolean = false
        lateinit var dtitle: String
        lateinit var subtext:String
        lateinit var content:String

        if( currentMenuItem.name == "XKCD"){
            isImaged = true
            dtitle = ""+json.getString("day")+"-"+
                    json.getString("month")+"-"+
                    json.getString("year")+", "+
                    json.getString("safe_title")
            subtext = json.getString("alt")
            content = json.getString("img")
                
        }else if( currentMenuItem.name == "Makeup"){

            isImaged = true
            dtitle = json.getString( "name")+ " by " + json.getString("brand")
            subtext = json.getString("description")
            content = json.getString("image_link")

        }else if( currentMenuItem.name == "Quotes"){

            isImaged = false
            dtitle = json.getString("quoteAuthor")
            subtext = ""
            content = json.getString("quoteText")
        }


        currentContentItem = ContentItem(isImaged, dtitle, subtext, content)

        lateinit var view : View

        if( currentContentItem != null) {
            txt_descriptionTitle.text = currentContentItem.title
            txt_descriptionDetails.text = currentContentItem.subText

            lay_itemBox.removeAllViews()
            if (currentContentItem.hasImage) {

                view = layoutInflater.inflate(R.layout.image_item_layout, null)
//                view.img_itemImage.setImageResource(currentMenuItem.icon)
                Picasso.get( )
                    .load( currentContentItem.content )
                    .resize( 1200, 0 )
                    .into( view.img_itemImage )


            } else {
                view = layoutInflater.inflate(R.layout.text_item_layout, null)
                view.txt_itemText.text = currentContentItem.content
                view.txt_itemText.setTextColor( textColors[ Random.nextInt( textColors.size - 1) ])
            }

            lay_itemBox.addView( view )
        }

        findViewById<RippleBackground>(R.id.rippler)?.startRippleAnimation()
    }

    fun onClickListener(v:View){
        fetchAnother()
    }


    fun onZoomImage(v:View){

    }
}
