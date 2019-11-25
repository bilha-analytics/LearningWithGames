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

    data class MenuItem(
        val name:String,
        val description: String,
        val icon: Int,
        var url:String,
        val hasImage: Boolean) : Serializable {

        init { updateRandomURL()  }

        fun updateRandomURL(){
            if( name == "XKCD" ){
                url = "http://xkcd.com/${Random.nextInt(2226)}/info.0.json"
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
        homeMenu.add(MenuItem("XKCD", "Daily XKCD comic", R.drawable.xkcd, "http://xkcd.com/info.0.json", true ))
        homeMenu.add(MenuItem("Makeup", "Major makup brnads and their products", R.drawable.makeup, "https://makeup-api.herokuapp.com/api/v1/products.json", true) )
        homeMenu.add(
            MenuItem(
                "Quotes",
                "Random common, inspirational, silly quotes",
                R.drawable.quotes,
                "https://quote-garden.herokuapp.com/quotes/random",
                false
            )
        )
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
