package ghazimoradi.soheil.ads_detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.divar.domain.model.onFailure
import ghazimoradi.soheil.divar.domain.model.onSuccess
import ghazimoradi.soheil.divar.domain.usecases.ads.GetAdsDetailUseCase
import ghazimoradi.soheil.divar.ui.model.UIMessage
import ghazimoradi.soheil.divar.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getAdsDetailUseCase: GetAdsDetailUseCase
) : BaseViewModel<AdsDetailUiState, AdsDetailUiEvent>() {

    private var adsId: Long? = null

    init {
        getInitData()
    }

    private fun getInitData() {
        savedStateHandle?.get<Int>("id")?.let {
            adsId = it.toLong()
            getAds()
        }
    }

    private fun getAds() {
        if (adsId != null) {
            viewModelScope.launch {
                getAdsDetailUseCase.invoke(adsId!!).collect {
                    it.onSuccess {
                        setState { copy(isLoading = false, ads = it) }
                    }.onFailure { apiError ->
                        setState { copy(isLoading = false) }
                        setUiMessage(UIMessage(stringValue = apiError.message))
                    }
                }
            }
        }
    }

    override fun createInitialState() = AdsDetailUiState()

    override fun onTriggerEvent(event: AdsDetailUiEvent) {
        when (event) {
            AdsDetailUiEvent.OnRefresh -> getAds()
            is AdsDetailUiEvent.ShowFullScreenSlider -> {
                setState { copy(showFullScreenSlider = event.isFullScreen) }
            }

            is AdsDetailUiEvent.OnShareClick -> {
                shareAds(event.context)
            }
        }
    }

    private fun shareAds(context: Context) {
        val shareText = """
            ${currentState.ads?.title}
            
            ${currentState.ads?.description}
            
            ${currentState.ads?.images?.firstOrNull()}
            
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(sendIntent , "Divar Share"))
    }
}
