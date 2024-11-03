package lat.pam.mycityjakarta.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.model.Details
import com.example.mycityjakarta.data.Data
import com.example.mycityjakarta.data.Data.getCoffeeShop
import com.example.mycityjakarta.data.Data.getHistoricalBuildings
import com.example.mycityjakarta.data.Data.getMall
import com.example.mycityjakarta.data.Data.getParks
import com.example.mycityjakarta.data.Data.getRestaurant
import lat.pam.mycityjakarta.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

    fun updateCategory(category: Int) {
        _uiState.value = _uiState.value.copy(
            category = category,
            placeList = getList(category)
        )
    }

    fun updatePlace(selectedPlace: Details) {
        _uiState.value = _uiState.value.copy(
            place = selectedPlace
        )
    }

    fun updateTitle(title: Int) {
        _uiState.value = _uiState.value.copy(
            title = title
        )
    }
}

fun getList(category: Int): List<Details> {
    return when (category) {
        R.string.coffee_shop -> getCoffeeShop()
        R.string.restaurant -> getRestaurant()
        R.string.parks -> getParks()
        R.string.mall -> getMall()
        R.string.historical_buildings -> getHistoricalBuildings()
        else -> getCoffeeShop()
    }
}

data class AppUiState(
    val categoryList: List<Int> = Data.category,
    val category: Int = R.string.coffee_shop,
    val title: Int = R.string.app_name,
    val placeList: List<Details> = getList(category),
    val place: Details = placeList[0]
)