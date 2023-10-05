package com.technocraze.budgetwiseproj

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.technocraze.budgetwiseproj.ui.theme.EducationSkyBlue
import com.technocraze.budgetwiseproj.ui.theme.FoodBlue
import com.technocraze.budgetwiseproj.ui.theme.ShoppingBlue
import com.technocraze.budgetwiseproj.ui.theme.TransportationYellow


sealed class Category(
  val name: String,
  val totalBudget:Int,
  open var amountSpent: Int,
  @DrawableRes val icon : Int,
  val color: Color,
  var weight:Float=0.0f,){

  data class FoodCategory(override var amountSpent: Int=0):
    Category("Food",500,amountSpent,R.drawable.ic_food, FoodBlue)
  class ShoppingCategory(override var amountSpent: Int=0):
    Category("Shopping",500,0,R.drawable.ic_shopping, ShoppingBlue)
  class TransportationCategory(override var amountSpent: Int=0):
    Category("Transportation",500,amountSpent,R.drawable.ic_transportation, TransportationYellow)
  class EducationCategory(override var amountSpent: Int=0):
    Category("Education",500,amountSpent,R.drawable.ic_education, EducationSkyBlue)

}