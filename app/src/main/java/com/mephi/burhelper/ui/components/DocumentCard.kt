package com.mephi.burhelper.ui.components

import android.content.ClipData
import android.content.Intent
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.w3c.dom.Document
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mephi.burhelper.ui.theme.AppTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import com.mephi.burhelper.ui.theme.pdfContainerDark
import com.mephi.burhelper.ui.theme.pdfContainerLight
import com.mephi.burhelper.ui.theme.wordContainerDark
import com.mephi.burhelper.ui.theme.wordDark
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext

@Composable
fun DocumentCard(
    title: String,
    date: String,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
    {
        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(wordContainerDark)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Дата создания: $date",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { onShareClick() },

                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Icon(Icons.Outlined.Share, contentDescription = "Shate")
                        Text(
                            text = "Поделиться",
                            style = MaterialTheme.typography.labelLarge
                        )

                    }
                    Button(
                        onClick = { onDeleteClick() },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Icon(Icons.Outlined.Delete, contentDescription = "Shate")
                        Text(
                            text = "Удалить",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}