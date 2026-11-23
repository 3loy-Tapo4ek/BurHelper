package com.mephi.burhelper.ui.components

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mephi.burhelper.ui.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableFab(
    onCreateClick: () -> Unit,
    onImportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (isExpanded)
        {
            ExtendedFloatingActionButton(onClick = { onCreateClick() }) {
                Icon(Icons.Outlined.Add, "Add")
                Text(
                    text = "Создать документ"
                )
            }

            ExtendedFloatingActionButton(onClick = { onImportClick()}) {
                Icon(Icons.Outlined.Upload, "Add")
                Text(
                    text = "Импортировать шаблон"
                )
            }
        }

        FloatingActionButton(onClick = { isExpanded = !isExpanded }) {
            Icon(Icons.Outlined.Add, "Add")
        }
    }
}

@Preview
@Composable
fun FabPreview() {
    AppTheme {
        ExpandableFab(
            onCreateClick = {},
            onImportClick = {}
        )
    }
}