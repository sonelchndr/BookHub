package com.sonelchndr.bookhub.fragment


import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.sonelchndr.bookhub.R
import com.sonelchndr.bookhub.adapter.FavouriteRecyclerAdapter
import com.sonelchndr.bookhub.database.BookDatabase
import com.sonelchndr.bookhub.database.BookEntity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_favourites.*


class FavouritesFragment : Fragment() {


    lateinit var recyclerFavourite: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: FavouriteRecyclerAdapter

    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    var dbBookList= listOf<BookEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_favourites, container, false)
        recyclerFavourite=view.findViewById(R.id.recyclerFavourite)

        layoutManager=GridLayoutManager(activity as Context,2)
        progressLayout=view.findViewById(R.id.progressLayout)
        progressBar=view.findViewById(R.id.progressBar)

       dbBookList=RetrieveFavourites(activity as Context).execute().get()


        if(activity!=null){
            progressLayout.visibility=View.GONE
            recyclerAdapter= FavouriteRecyclerAdapter(activity as Context,dbBookList)
            recyclerFavourite.adapter=recyclerAdapter
            recyclerFavourite.layoutManager=layoutManager
        }
        return view
    }
   class RetrieveFavourites(val context: Context):AsyncTask<Void,Void,List<BookEntity>>(){
       override fun doInBackground(vararg params: Void?): List<BookEntity> {
           val db= Room.databaseBuilder(context,BookDatabase::class.java,"books-db").build()
           return db.bookDao().getAllBooks()
       }

   }

}
