package com.technocraze.budgetwiseproj

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Density
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.asFlow

class HomeViewModel: ViewModel() {

   val categories = mutableStateListOf(
      Category.FoodCategory(100),
      Category.ShoppingCategory(150),
      Category.TransportationCategory(80),
      Category.EducationCategory(90)
    )

  


  fun addExpense(categoryName:String, amount:Int){
    var categoryChanged = categories.find { categoryName == it.name }
    categoryChanged?.amountSpent = categoryChanged?.amountSpent?.plus(amount)!!
    var ind = categories.indexOf(categoryChanged)
    categories.remove(categoryChanged)
    categories.add(ind, categoryChanged!!)
  }


}