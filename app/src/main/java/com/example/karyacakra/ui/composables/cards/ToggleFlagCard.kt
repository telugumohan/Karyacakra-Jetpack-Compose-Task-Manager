package com.example.karyacakra.ui.composables.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.karyacakra.R


@Composable
fun ToggleFlagCard (
    isFlagged: Boolean,
    modifier: Modifier = Modifier,
    onToggleFlag: () -> Unit
) {
    Card(
        modifier = modifier.border(width = 1.dp, color = Color.Gray).clickable { onToggleFlag() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Toggle Flag", style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = if (isFlagged) "On" else "Off", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = onToggleFlag) {
                Icon(
                    painter = painterResource(id = if (isFlagged) R.drawable.baseline_flag_24 else R.drawable.baseline_outlined_flag_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}
