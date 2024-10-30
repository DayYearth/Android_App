package com.example.gmail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.R
import com.example.gmail.models.EmailModel

class EmailAdapter(private val emailList: List<EmailModel>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ava: ImageView = itemView.findViewById(R.id.ava)
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val previewText: TextView = itemView.findViewById(R.id.preview_text)
        val star: ImageView = itemView.findViewById(R.id.star)

        init {
            star.setOnClickListener ( View.OnClickListener() {
                star.setImageResource(R.drawable.baseline_star_24)
                Toast.makeText(itemView.context, "Đã gắn sao cho email ", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmailAdapter.EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailAdapter.EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.ava.setImageResource(email.avatarResId)
        holder.senderName.text = email.senderName
        holder.timestamp.text = email.timestamp
        holder.subject.text = email.subject
        holder.previewText.text = email.previewText

        holder.star.setOnClickListener ( View.OnClickListener() {
                email.isStarred = !email.isStarred
                if (email.isStarred) {
                    holder.star.setImageResource(R.drawable.baseline_star_24)
                    Toast.makeText(holder.itemView.context, "Đánh dấu email quan trọng", Toast.LENGTH_SHORT).show()
                }
                else {
                    holder.star.setImageResource(R.drawable.baseline_star_border_24)
                    Toast.makeText(holder.itemView.context, "Bỏ đánh dấu email quan trọng", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun getItemCount(): Int = emailList.size


}