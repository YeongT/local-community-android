package com.implude.localcommunity.ui.article_add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticleAddViewModel : ViewModel() {
    val title = MutableLiveData("")
    val content = MutableLiveData("")
}