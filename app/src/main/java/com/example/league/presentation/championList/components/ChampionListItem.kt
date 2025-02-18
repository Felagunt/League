package com.example.league.presentation.championList.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.league.core.domain.Consts
import com.example.league.domain.models.Champion

@Composable
fun ChampionListItem(
    champion: Champion,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = Consts.imageLoadingUrl + "${champion.name}_0.jpg",
            contentDescription = champion.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .weight(0.3f)
                .height(100.dp)
        )

        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = champion.name ?: "",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = champion.blurb ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 24.sp
            )
        }
    }
}