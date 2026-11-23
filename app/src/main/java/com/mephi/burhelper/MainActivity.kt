package com.mephi.burhelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mephi.burhelper.navigation.AppNavigation
import com.mephi.burhelper.ui.screens.HomeScreen
import com.mephi.burhelper.ui.screens.HomeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        handleLink(intent)?.let(viewModel::onDocumentLinkReceived)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            AppTheme(darkTheme = uiState.isDarkTheme) {
                AppNavigation(viewModel = viewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        handleLink(intent)?.let(viewModel::onDocumentLinkReceived)
    }

    private fun handleLink(intent: Intent?): String? =
        intent?.data
            ?.takeIf { it.scheme == "burhelper" && it.host == "document" }
            ?.pathSegments
            ?.firstOrNull()
            ?.takeIf(String::isNotBlank)
}
