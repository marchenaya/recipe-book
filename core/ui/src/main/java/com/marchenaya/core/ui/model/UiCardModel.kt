package com.marchenaya.core.ui.model

data class UiCardModel(val id: Int, val title: String, val imageUrl: String?) {

    companion object {
        val uiCardPreview = UiCardModel(
            id = 1,
            title = "Card title",
            imageUrl = "https://www.referenseo.com/wp-content/uploads/2019/03/image-attractive-960x540.jpg"
        )
    }
}
