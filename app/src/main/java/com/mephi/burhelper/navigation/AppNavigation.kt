package com.mephi.burhelper.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.mephi.burhelper.ui.screens.HomeScreen
import com.mephi.burhelper.ui.screens.HomeViewModel
import com.mephi.burhelper.ui.screens.TemplateSelectionScreen


@Serializable
private data object Home : NavKey
@Serializable
private data object TemplateSelection : NavKey

@Composable
fun AppNavigation(
    viewModel: HomeViewModel
) {
    val backStack = rememberNavBackStack(Home)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavDisplay(
        backStack = backStack,

        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },

        entryProvider = entryProvider {
            entry<Home> {
                HomeScreen(
                    documents = uiState.documents,
                    searchQuery = uiState.searchQuery,
                    isDarkTheme = uiState.isDarkTheme,
                    onSearchQueryChange = viewModel::onSearchQueryChange,
                    onToggleTheme = viewModel::onToggleTheme,
                    onCreateDocument = {
                        backStack.add(TemplateSelection)
                    },
                    onDeleteDocument = viewModel::onDeleteDocument,
                    onImportDocument = {},
                    onDocumentClick = {}
                )
            }
            entry<TemplateSelection> {
                TemplateSelectionScreen(
                    isDarkTheme = uiState.isDarkTheme,
                    onToggleTheme = viewModel::onToggleTheme,
                    onBack = {
                        if (backStack.size > 1) {
                            backStack.removeLastOrNull()
                        }
                    },
                    onTemplateSelected = { templateName ->
                        viewModel.onCreateDocumentFromTemplate(templateName)
                        backStack.removeLastOrNull()
                    }
                )
            }
        }

    )
}