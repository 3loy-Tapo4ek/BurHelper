package com.mephi.burhelper.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.temporal.TemporalQuery

data class HomeUiState(
    val documents: List<String> = listOf("Стандартный_Докумнет.docx"),
    val searchQuery: String = "",
    val isDarkTheme: Boolean = true
)

class HomeViewModel : ViewModel() {
    private val uiState_ = MutableStateFlow(HomeUiState())

    val uiState = uiState_.asStateFlow()

    //функции

    fun onSearchQueryChange(query: String) {
        uiState_.update { it.copy(searchQuery = query) }
    }

    fun onToggleTheme() {
        uiState_.update { it.copy(isDarkTheme = !it.isDarkTheme) }
    }

    //Карточки доб уб
    fun onCreateDocument() {
        uiState_.update { state ->
            val newTitle = "Документ_${state.documents.size + 1}.docx"

            state.copy(documents = listOf(newTitle) + state.documents)
        }
    }

    fun onDeleteDocument(title : String) {
        uiState_.update { state ->
            state.copy(documents = state.documents.filterNot {it == title} )
        }
    }

    fun onCreateDocumentFromTemplate(templateName: String) {
        uiState_.update { state ->
            val number = state.documents.size + 1
            val title = "${templateName}_$number.pdf"

            state.copy(documents = listOf(title) + state.documents)
        }
    }
    //линки линки 2 ствола
    fun onDocumentLinkReceived(title: String) {
        if (title.isBlank()) { return }

        uiState_.update { state ->
            if (title in state.documents) state
            else state.copy(documents = listOf(title) + state.documents)
        }
    }
}