package com.mephi.burhelper.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mephi.burhelper.ui.components.DocumentCard
import com.mephi.burhelper.ui.components.ExpandableFab
import com.mephi.burhelper.ui.theme.AppTheme

@Composable
fun HomeScreen(
    documents: List<String>,
    searchQuery: String,
    isDarkTheme: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onToggleTheme: () -> Unit,
    onCreateDocument: () -> Unit,
    onDeleteDocument: (String) -> Unit,
    onImportDocument: () -> Unit,
    onDocumentClick: (String) -> Unit,

    modifier: Modifier = Modifier

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExpandableFab(
                onCreateClick = { onCreateDocument() },
                onImportClick = { onImportDocument() }
                )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //верхний ряд
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(modifier = Modifier.align(Alignment.Center), text = "ГЕРБ")

                Button(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    shape = RoundedCornerShape(12.dp),
                    onClick = onToggleTheme)
                { Icon(Icons.Outlined.DarkMode, "Mode")}
            }

            //Поисковая строчечка 1010 грязнулька
            TextField(
                modifier = Modifier.fillMaxWidth(),
                state = rememberTextFieldState(),

                placeholder = { Text(text = "Поиск документа") },
                leadingIcon = {Icon(Icons.Outlined.Menu, contentDescription = "Filters")},
                trailingIcon = {Icon(Icons.Outlined.Search, contentDescription = "Search")},
                shape = CircleShape,
                colors = TextFieldDefaults.colors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
            )

            Text(
                modifier = Modifier,
                text = "Список документов",
                style = MaterialTheme.typography.headlineLarge
            )

            //Карточки-роднульки
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(documents) { title ->
                    val context = LocalContext.current
                    DocumentCard(
                        title = title,
                        date = "DD.MM.YYYY",
                        onShareClick = {
                            val link = "burhelper://document/${Uri.encode(title)}"
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, link)
                            }
                            context.startActivity(
                                Intent.createChooser(sendIntent, "Поделиться документом")
                            )
                        },
                        onDeleteClick = { onDeleteDocument(title) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme(darkTheme = true) {
        HomeScreen(
            documents = listOf("Документ.docx", "Отчет.pdf", "Таблица.xlsx"),
            searchQuery = "",
            isDarkTheme = true,
            onSearchQueryChange = {},
            onToggleTheme = {},
            onCreateDocument = {},
            onDeleteDocument = {},
            onImportDocument = {},
            onDocumentClick = {}
        )
    }
}