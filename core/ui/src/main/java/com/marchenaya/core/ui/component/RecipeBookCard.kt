package com.marchenaya.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.marchenaya.core.ui.theme.RecipeBookTheme
import com.marchenaya.ui.R

@Composable
fun RecipeBookCard(cardImage: String, cardText: String, onClick: () -> Unit) {
    ConstraintLayout(
        constraintSet = recipeBookCardConstraintSet(),
        modifier = Modifier.clickable { onClick() }
    ) {
        ElevatedCard(
            modifier = Modifier.layoutId("card")
        ) {
            AsyncImage(
                model = cardImage,
                contentDescription = cardText,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                error = painterResource(id = R.drawable.ic_placeholder_24dp)
            )
        }
        RecipeBookText(
            text = cardText,
            modifier = Modifier
                .layoutId("text")
                .padding(top = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

private fun recipeBookCardConstraintSet() = ConstraintSet {

    val card = createRefFor("card")
    val text = createRefFor("text")

    constrain(card) {
        top.linkTo(parent.top, 8.dp)
    }
    constrain(text) {
        top.linkTo(card.bottom)
        start.linkTo(card.start)
        end.linkTo(card.end)
        width = Dimension.fillToConstraints
    }

}

@Preview
@Composable
fun RecipeBookCardPreview() {
    RecipeBookTheme {
        RecipeBookCard(
            cardImage = stringResource(R.string.image_preview),
            cardText = stringResource(R.string.card_text_preview)
        ) { }
    }
}