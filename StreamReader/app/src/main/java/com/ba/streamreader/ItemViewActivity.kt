package com.ba.streamreader

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import androidx.core.text.HtmlCompat
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
        if( currentMenuItem.name == HomeMenuActivity.STR_MAKEUP){
            doProcess(getRandomMakeupItem())
        }else if( currentMenuItem.name == HomeMenuActivity.STR_CAT_FACTS) {
            //doProcess(getRandomCatFactItem())
            Ion.with(this)
                .load( "https://aws.random.cat/meow" ) // random cat images
                .asString()
                .setCallback{ e, res ->
                    val jobj = JSONObject( getRandomCatFactItem() )
                    jobj.put("img", JSONObject(res).getString("file"))
                    doProcess( jobj.toString() )
                }
        } else if(currentMenuItem.name == HomeMenuActivity.STR_BOOK_COVERS){
            currentMenuItem.updateRandomURL()
            val jobj = JSONObject()
            jobj.put("img", currentMenuItem.url )
            doProcess( jobj.toString() )

        } else {
            currentMenuItem.updateRandomURL()

            Ion.with(this)
                .load(currentMenuItem.url)
                .asString()
                .setCallback{ e, res ->
                    doProcess(res)
                }
        }
    }

    private fun getRandomMakeupItem() : String {
        val json = JSONObject( "$MainActivity.makeupList_JSONObject" )
        val ls = json.getJSONArray("obj")
        val res = ls.getString( Random.nextInt( ls.length() - 1 ) )
        return  res
    }


    private fun getRandomCatFactItem() : String {
        val json = JSONObject( "${MainActivity.catfactsList_JSONObject}" )
        val ls = json.getJSONArray("all")
        val res = ls.getString( Random.nextInt( ls.length() - 1 ) )
        return  res
    }

    fun doProcess(result:String) {
        if( result == null ) return

        var json = JSONObject( result )

        var isImaged: Boolean = false
        var dtitle: String = ""
        var subtext: String = ""
        var content: String = ""

        if (currentMenuItem.name == HomeMenuActivity.STR_XKCD) {
            isImaged = true
            dtitle = "" + json.getString("day") + "-" +
                    json.getString("month") + "-" +
                    json.getString("year") + ", " +
                    json.getString("safe_title")
            subtext = json.getString("alt")
            content = json.getString("img")

        } else if (currentMenuItem.name == HomeMenuActivity.STR_MAKEUP) {
            isImaged = true
            dtitle = json.getString("name") + " by " + json.getString("brand")
            subtext = json.getString("description")
            content = json.getString("image_link")

        } else if (currentMenuItem.name == HomeMenuActivity.STR_QUOTES) {
            isImaged = false
            dtitle = json.getString("quoteAuthor")
            content = json.getString("quoteText")

        }else if (currentMenuItem.name == HomeMenuActivity.STR_NUMBERS) {
            isImaged = false
            dtitle = json.getString("type")+":\t"+ json.getString("number")
            content = ""+json.getString("text")

        } else if (currentMenuItem.name == HomeMenuActivity.STR_JOKES) {
            isImaged = false
            dtitle = json.getString("type")
            content = ""+json.getString("setup")+"\n\n"+json.getString("punchline")

        } else if (currentMenuItem.name == HomeMenuActivity.STR_TRIVIA) {
            isImaged = false
            val res = json.getJSONArray("results").get(0)
            if( res != null ) {
                json = JSONObject(res.toString() )
                dtitle = json.getString("category")
                subtext = "Difficulty:\t"+json.getString("difficulty")+
                        "\nAnswer:\t" + json.getString("correct_answer")
                content = Html.fromHtml(json.getString("question") ).toString()
            }

        }else if (currentMenuItem.name == HomeMenuActivity.STR_CAT_FACTS) {
            isImaged = true
            dtitle = "Random cat + Random fact" //json.getString( "type")
            subtext = json.getString("text")
            content = json.getString("img")

        } else if (currentMenuItem.name == HomeMenuActivity.STR_BOOK_COVERS) {
            isImaged = true
            dtitle = "Random book covers" //json.getString( "type")
            content = json.getString("img")

        }else if( currentMenuItem.name == HomeMenuActivity.STR_BORED_ACTIVITIES){
            isImaged = false
            dtitle = json.getString( "type").capitalize()
            subtext = "Can be done by "+json.getString( "participants")+" person(s)."+
                    "\nAccessibility: "+json.getString( "accessibility")+
                    "\nPrice rank: "+ json.getString( "price")+
                    "\nLink: "+json.getString( "link")
            content = json.getString("activity")

        }else if (currentMenuItem.name == HomeMenuActivity.STR_RICK_MORTY) {
            isImaged = true
            dtitle = json.getString("name")

            val origin = json.getJSONObject("origin")
            val home = json.getJSONObject("location")
            content = json.getString("image")

            subtext =
                json.getString("species") + ", " + json.getString("gender")+
                        "\nStatus:\t " + json.getString("status") +
                        "\nOrigin:\t" + origin.getString("name") +
                        "\nResides:\t" + home.getString("name")

        }

        displayContent(isImaged, dtitle, subtext, content)
    }

    private fun displayContent(isImaged:Boolean, dtitle:String, subtext: String, content: String){

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
