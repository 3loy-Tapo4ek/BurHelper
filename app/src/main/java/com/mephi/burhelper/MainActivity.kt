package com.mephi.burhelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mephi.burhelper.ui.theme.AppTheme
import com.mephi.burhelper.ui.components.DocumentCard
import com.mephi.burhelper.ui.components.ExpandableFab
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    private var documentList = mutableStateListOf<String>("Стандартный_документ.docx")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleLink(intent)?.let { titleFromLink ->
            if (!documentList.contains(titleFromLink)) {
                documentList.add(0, titleFromLink)
            }
        }

        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        ExpandableFab(
                            onCreateClick = {
                                val size = documentList.size

                                val new_title = "Документ_${size+1}.docx"

                                documentList.add(0, new_title)
                            },
                            onImportClick = {},
                        )
                    }
                    ) { innerPadding ->
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),

                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(documentList) { title ->
                            DocumentCard(
                                title = title,
                                date = "DD.MM.YYYY"
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        handleLink(intent)?.let { titleFromLink ->
            if (!documentList.contains(titleFromLink)) {
                documentList.add(0, titleFromLink)
            }
        }
    }

    private fun handleLink(intent: Intent?): String? {
        val uri = intent?.data ?: return null

        val docId = uri.pathSegments.getOrNull(0)

        if (docId.isNullOrBlank()) {
            return null
        }

        return Uri.decode(docId)
    }
}
