import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.homedroid2.R
import com.example.homedroid2.models.Book;
import com.squareup.picasso.Picasso


class DataAdapter constructor(val books: List<Book>, val onBookClick: BooksViewHolder.OnBookClick) :
    RecyclerView.Adapter<DataAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.recycler_view_item_1, parent, false),
            onBookClick
        )
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder?.bindBooks(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BooksViewHolder(itemView: View?, val onBookClick: OnBookClick) : RecyclerView.ViewHolder(itemView!!) {
        val titleTextView: TextView? = itemView?.findViewById(R.id.tv_book)
        val newsImageVIew: ImageView? = itemView?.findViewById(R.id.iv_wallpaper)
        fun bindBooks(books: Book) {

            titleTextView?.text = books.BestBook().title
            Picasso.get().load(books.BestBook().image_url).into(newsImageVIew)
            itemView.setOnClickListener {
                onBookClick.onNewsClick(books)
            }
        }

        interface OnBookClick {
            fun onNewsClick(news: Book)
        }
    }
}