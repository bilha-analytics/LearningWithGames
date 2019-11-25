package com.ba.streamreader

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_home_menu.*
import java.io.Serializable
import kotlin.collections.ArrayList
import kotlin.random.Random

class HomeMenuActivity : AppCompatActivity() {

    companion object{
        val STR_XKCD = "XKCD"
        val STR_MAKEUP = "Makeup"
        val STR_QUOTES = "Quotes"
        val STR_BOOK_COVERS = "Book Covers"
        val STR_CAT_FACTS  = "Cat Facts"
        val STR_BORED_ACTIVITIES = "Bored?"
        val STR_JOKES = "Joke-a-Jokes!"
        val STR_RICK_MORTY = "Rick and Morty"
        val STR_TRIVIA = "Trivia"
        val STR_NUMBERS = "Number Facts"
    }

    data class MenuItem(
        val name:String,
        val description: String,
        val icon: Int,
        var url:String,
        val hasImage: Boolean) : Serializable {

        init { updateRandomURL()  }

        fun updateRandomURL(){
            if( name == STR_XKCD ){
                url = "http://xkcd.com/${Random.nextInt(2226)}/info.0.json"
            }else if( name == STR_BOOK_COVERS){
                url = "http://covers.openlibrary.org/b/ID/${Random.nextLong(9099999)}-M.jpg"
            }else if( name == STR_RICK_MORTY){
                url = "https://rickandmortyapi.com/api/character/${Random.nextInt(493)}"
            }else if (name == STR_NUMBERS){
                val opts = listOf<String>("math", "trivia", "year", "date")
                url = "http://numbersapi.com/random/${opts[Random.nextInt(opts.size - 1) ]}?json"
            }

        }

    }

    class MenuItemAdapter (
        private var context: Context,
        private var listData : ArrayList<MenuItem>): BaseAdapter(  ) {

        private class ItemLayout(row: View?){
            var name : TextView? = null
            var description : TextView? = null
            var icon : ImageView? = null

            init {
                this.name = row?.findViewById<TextView>(R.id.txt_menuTitle)
                this.description = row?.findViewById<TextView>(R.id.txt_menuDescription)
                this.icon = row?.findViewById<ImageView>(R.id.img_menuIcon)
            }

        }


        override fun getCount(): Int {
            return this.listData.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return this.listData[position]
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            lateinit var view: View
            lateinit var itemLayout : ItemLayout

            if( convertView == null ){
                val inflater = context.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
                view = inflater.inflate(R.layout.liat_item_layout, null)
                itemLayout = ItemLayout( view )
                view?.tag = itemLayout

            }else{
                view = convertView
                itemLayout = view.tag as ItemLayout
            }

            val item = listData[position]
            itemLayout.name?.text = item.name
            itemLayout.description?.text = item.description
            itemLayout.icon?.setImageResource( item.icon )

            return view
        }

    }



//    private val homeMenu = listOf<String>("XKCD", "Makeup", "Quotes")

    private val homeMenu: ArrayList<MenuItem> = ArrayList<MenuItem>()


    private fun generateMenuListData() {
        homeMenu.add(MenuItem(STR_XKCD, "Daily XKCD comic", R.drawable.xkcd, "http://xkcd.com/info.0.json", true ))
        homeMenu.add(MenuItem(STR_MAKEUP, "Major makeup brands and their products", R.drawable.makeup, "https://makeup-api.herokuapp.com/api/v1/products.json", true) )
        homeMenu.add(
            MenuItem(
                STR_QUOTES,
                "Random common, inspirational, silly quotes",
                R.drawable.quotes,
                "https://quote-garden.herokuapp.com/quotes/random",
                false
            )
        )
        homeMenu.add( MenuItem(STR_BOOK_COVERS, "Random book covers discovery", R.drawable.books, "http://covers.openlibrary.org/b/ID/1-M.jpg", true))
        homeMenu.add( MenuItem(STR_BORED_ACTIVITIES, "Are you bored? Try some of these activities", R.drawable.bored, "http://www.boredapi.com/api/activity/", false))
        homeMenu.add( MenuItem(STR_CAT_FACTS, "Random infor about cats", R.drawable.cat_facts, "https://cat-fact.herokuapp.com/facts" , false ))
        homeMenu.add( MenuItem(STR_JOKES, "Programming and random jokes", R.drawable.jokes, "https://official-joke-api.appspot.com/random_joke", false))
        homeMenu.add( MenuItem(STR_RICK_MORTY, "Information about characters in Rick and Morty series", R.drawable.rick_morty, "https://rickandmortyapi.com/api/character/1", true))
        homeMenu.add( MenuItem(STR_TRIVIA, "Open Trivia database", R.drawable.trivia, "https://opentdb.com/api.php?amount=1", false))
        homeMenu.add( MenuItem(STR_NUMBERS, "Facts about numbers @ math, year, trivia", R.drawable.numbers, "http://numbersapi.com/random/year?json", false))

    }

//    private lateinit var adapter_homeMenu : ArrayAdapter<String>
    private lateinit var adapter_homeMenu : MenuItemAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_menu)


//        adapter_homeMenu = ArrayAdapter(this, android.R.layout.simple_list_item_1, homeMenu)
        generateMenuListData()
        adapter_homeMenu = MenuItemAdapter(this, homeMenu)

        ls_homeMenu.adapter = adapter_homeMenu

        ls_homeMenu.setOnItemClickListener(){ _, _, index, _ ->
//            Toast.makeText(this, "Selected ${homeMenu[index]}", Toast.LENGTH_SHORT).show()
            val intented = Intent( this, ItemViewActivity::class.java)
            intented.putExtra("menuItem", homeMenu[index] )
            startActivity( intented )
        }
    }
}
