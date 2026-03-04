package ci.nsu.mobile.main.ui.colorsearch

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ci.nsu.mobile.main.R
import ci.nsu.mobile.main.model.ColorModel

class ColorSearchViewModel : ViewModel() {
    private val availableColors = listOf(
        ColorModel("Red",R.color.red),
        ColorModel("Orange",R.color.orange),
        ColorModel("Yellow",R.color.yellow),
        ColorModel("Green",R.color.green),
        ColorModel("Blue",R.color.blue),
        ColorModel("Indigo",R.color.indigo),
        ColorModel("Violet",R.color.violet)
    )

    private val _searchResult = MutableLiveData<ColorModel?>()
    val searchResult: LiveData<ColorModel?> =_searchResult

    private val _allColors = MutableLiveData<List<ColorModel>>(availableColors)
    val allColors: LiveData<List<ColorModel>> = _allColors

    fun searchColor(query:String){
        val foundColor = availableColors.find {
            it.name.equals(query,ignoreCase = true)
        }

        if (foundColor != null){
            _searchResult.value = foundColor
            Log.d("ColorSearch","Цвет '${foundColor.name}' найден")
        } else {
            Log.d("ColorSearch","Пользовательский цвет '$query' не найден")
            _searchResult.value = null
        }
    }
}