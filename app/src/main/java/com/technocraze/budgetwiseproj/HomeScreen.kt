package com.technocraze.budgetwiseproj

import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technocraze.budgetwiseproj.composeui.ExpenseCategoryItem
import com.technocraze.budgetwiseproj.composeui.ExpenseMultiColorProgressBar
import com.technocraze.budgetwiseproj.composeui.FloatingActionBtn
import com.technocraze.budgetwiseproj.ui.theme.EducationSkyBlue
import com.technocraze.budgetwiseproj.ui.theme.FoodBlue
import com.technocraze.budgetwiseproj.ui.theme.Green
import com.technocraze.budgetwiseproj.ui.theme.Grey
import com.technocraze.budgetwiseproj.ui.theme.ShoppingBlue
import com.technocraze.budgetwiseproj.ui.theme.TransportationYellow
import kotlinx.coroutines.launch


// @OptIn(ExperimentalMaterialApi::class)
// @Composable
// fun HomeScreen(categories: SnapshotStateList<Category>, bottomSheetState: ModalBottomSheetState) {
//   HomeUi(categories, bottomSheetState)
// }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen( bottomSheetState: ModalBottomSheetState) {
  val coroutineScope = rememberCoroutineScope()
  val viewModel:HomeViewModel = viewModel(LocalContext.current as ComponentActivity)
  val categories = viewModel.categories
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(40.dp), contentAlignment = Alignment.Center
  ) {
    Card(
      shape = RoundedCornerShape(20.dp)
    ) {
      Column(
        Modifier.padding(vertical = 20.dp)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          BudgetHeader(
            title = "Spent",
            amount = categories.sumOf { it.amountSpent },
            amountTextStyle = TextStyle(
              fontWeight = FontWeight.Bold
            )
          )
          HeaderDivider()
          BudgetHeader(
            title = "Available",
            amount = categories.sumOf { it.totalBudget - it.amountSpent },
            amountTextStyle = TextStyle(
              fontWeight = FontWeight.Bold,
              color = Green
            )
          )
          HeaderDivider()
          BudgetHeader(
            title = "Budget",
            amount = categories.sumOf { it.totalBudget },
            amountTextStyle = TextStyle(
              fontWeight = FontWeight.Bold
            )
          )
        }
        Column(Modifier.padding(20.dp)) {
          ExpenseMultiColorProgressBar(categories)
        }
        categories.forEach { category ->
          ExpenseCategoryItem(category)
        }

      }
    }
  }
  FloatingActionBtn() {
    if (bottomSheetState.isVisible) {
      coroutineScope.launch { bottomSheetState.hide() }
    } else {
      coroutineScope.launch { bottomSheetState.show() }
    }
  }

}

@Composable
private fun HeaderDivider() {
  Divider(
    modifier = Modifier
      .height(25.dp) //fill the max height
      .width(1.dp)
  )
}

@Composable
private fun BudgetHeader(
  title: String, amount: Int,
  titleTextStyle: TextStyle = TextStyle(),
  amountTextStyle: TextStyle = TextStyle()
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = title,
      color = Color.Gray,
      fontWeight = FontWeight.Bold,
      fontSize = 16.sp,
      style = titleTextStyle,
      maxLines = 1
    )
    Text(
      text = "$${amount}",
      fontSize = 20.sp,
      style = amountTextStyle,
      maxLines = 1
    )
  }
}
