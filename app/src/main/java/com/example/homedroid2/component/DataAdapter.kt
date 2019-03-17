package com.example.homedroid2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.homedroid2.models.Book;
import com.squareup.picasso.Picasso

class DataAdapter(private val onBookClick: (Book) -> Unit) :
    RecyclerView.Adapter<DataAdapter.BooksViewHolder>() {

    private var books: ArrayList<Book> = ArrayList(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_1, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindBooks(books[position], onBookClick)
    }

    override fun getItemCount(): Int = books.size

    fun updateDataSet(list: ArrayList<Book>) {
        var size = this.books.size
        this.books.addAll(list)
        var sizeNew = this.books.size
        notifyItemRangeChanged(size, sizeNew)

    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView? = itemView.findViewById(R.id.tv_book)
        val bookImageVIew: ImageView? = itemView?.findViewById(R.id.iv_wallpaper)
        val bookRating: TextView? = itemView?.findViewById(R.id.tv_rating)
        val author: TextView? = itemView?.findViewById(R.id.tv_author)
        fun bindBooks(books: Book, onBookClick: (Book) -> Unit) {

            titleTextView?.text = books.best_book?.title
            bookRating?.text = books.average_rating
            author?.text = books.best_book?.author?.name
            Picasso.get().load(books.best_book?.image_url).into(bookImageVIew)
            itemView.setOnClickListener {
                onBookClick.invoke(books)
            }
        }
    }
}
