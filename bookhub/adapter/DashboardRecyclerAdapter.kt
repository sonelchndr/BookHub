package com.sonelchndr.bookhub.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sonelchndr.bookhub.DescriptionActivity
import com.sonelchndr.bookhub.R
import com.sonelchndr.bookhub.model.Book
import com.squareup.picasso.Picasso
import java.util.ArrayList


class DashboardRecyclerAdapter(val context: Context,val itemList:ArrayList<Book>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashViewHolder>(){
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashViewHolder {

         val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
           return DashViewHolder(view)
       }

       override fun getItemCount(): Int {

           return itemList.size
       }

       override fun onBindViewHolder(holder: DashViewHolder, position: Int) {

          // val text=itemList[position]
         //  holder.textView.text=text

           val book=itemList[position]
           holder.txtBookName.text=book.bookName
           holder.txtBookAuthor.text=book.bookAuthor
           holder.txtBookPrice.text=book.bookPrice
           holder.txtBookRating.text=book.bookRating
         //  holder.imgBookImage.setImageResource(book.bookImage)
           Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage);
           holder.llContent.setOnClickListener {
               //Toast.makeText(context,"clicked on ${holder.txtBookName.text}",Toast.LENGTH_LONG).show()
           val intent=Intent(context,DescriptionActivity::class.java)
               //for desc of book
               intent.putExtra("book_id",book.bookId)

                 context.startActivity(intent)

           }
       }


       class DashViewHolder(view: View):RecyclerView.ViewHolder(view)     //creating viewholder
       {

         //  var rela:RelativeLayout=view.findViewById(R.id.relative)

           val txtBookName: TextView = view.findViewById(R.id.txtBookName)
           val txtBookAuthor: TextView = view.findViewById(R.id.txtBookAuthor)
           val txtBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
           val txtBookRating: TextView = view.findViewById(R.id.txtBookRating)
           val imgBookImage: ImageView = view.findViewById(R.id.imgBookImage)
           val llContent: LinearLayout =view.findViewById(R.id.llContent)
       }
}