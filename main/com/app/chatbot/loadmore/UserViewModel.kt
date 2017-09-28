package com.app.chatbot.loadmore

import android.databinding.BaseObservable
import com.app.chatbot.BR
import com.app.chatbot.R
import com.app.chatbot.databinding.UserItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import org.lib.adapter.LastAdapter
import org.lib.repository.Fire

/**
 * Created by putraxor on 27/09/17.
 */

class UserViewModel : BaseObservable() {

    val data = mutableListOf<User>()
    val adapter: LastAdapter = LastAdapter(data, BR.item).map<User, UserItemBinding>(R.layout.user_item)
    private val repo: DatabaseReference by lazy { Fire.appDb.child("users") }
    private var lastKey = ""

    /**
     * Load more paging
     */
    fun loadMore() {
        val query = if (lastKey.isEmpty()) repo.orderByKey() else repo.orderByKey().endAt(lastKey)

        query.limitToLast(3)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snap: DataSnapshot) {
                        snap.children.sortedByDescending { it.key }.forEachIndexed { index, it ->
                                    if (index.toLong() == snap.childrenCount - 1) {
                                        lastKey = it.key
                                    } else {
                                        data.add(it.getValue(User::class.java))
                                    }
                                }
                        adapter.notifyDataSetChanged()
                    }
                    override fun onCancelled(error: DatabaseError?) {}
                })
    }
}