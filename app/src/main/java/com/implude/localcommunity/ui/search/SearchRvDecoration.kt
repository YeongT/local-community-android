package com.implude.localcommunity.ui.search

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SearchRvDecoration(private val divWidth : Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = divWidth
    }
}
