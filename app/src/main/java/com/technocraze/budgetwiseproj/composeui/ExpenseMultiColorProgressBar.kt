package com.technocraze.budgetwiseproj.composeui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.technocraze.budgetwiseproj.Category
import com.technocraze.budgetwiseproj.ui.theme.EducationSkyBlue
import com.technocraze.budgetwiseproj.ui.theme.FoodBlue
import com.technocraze.budgetwiseproj.ui.theme.Grey
import com.technocraze.budgetwiseproj.ui.theme.ShoppingBlue
import com.technocraze.budgetwiseproj.ui.theme.TransportationYellow



@Composable
fun ExpenseMultiColorProgressBar(categories: SnapshotStateList<Category>) {
  val weightList =
    categories.map {
      var w = (it.amountSpent.toFloat() / 2000)
      if (w == 0.0f) 0.0000001f else w
    }

  var remainingWeight = (2000 - categories.sumOf { it.amountSpent }).toFloat() / 2000

  if (remainingWeight == 0.0f) {
    remainingWeight = 0.0000001f
  }

  var updateTransition = updateTransition(
    targetState = categories,
    label = "kmkm"
  )

  categories[0].weight = updateTransition.animateFloat(
    transitionSpec = { tween(3000) },
    label = "width",
    targetValueByState = {
      it
      weightList[0]
    }
  ).value
  // categories[0].weight = weight1
  categories[1].weight = updateTransition.animateFloat(
    transitionSpec = { tween(3000) },
    label = "width",
    targetValueByState = {
      it
      weightList[1]
    }
  ).value
  categories[2].weight = updateTransition.animateFloat(
    transitionSpec = { tween(3000) },
    label = "width",
    targetValueByState = {
      it
      weightList[2]
    }
  ).value
  categories[3].weight = updateTransition.animateFloat(
    transitionSpec = { tween(3000) },
    label = "width",
    targetValueByState = {
      it
      weightList[3]
    }
  ).value

  var remainingW  = updateTransition.animateFloat(
    transitionSpec = { tween(3000) },
    label = "width",
    targetValueByState = {
      it
      remainingWeight
    }
  ).value



  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(32.dp)
      .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
      .border(
        border = BorderStroke(0.dp, Color.Transparent),
        shape = RoundedCornerShape(10.dp)
      )
      .background(Grey)
  ) {
    Box(
      // Food
      modifier = Modifier
        .weight(categories[0].weight)
        .height(32.dp)
        .clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
        .background(FoodBlue)
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 3000,
            easing = LinearOutSlowInEasing
          )
        ),
    )
    Box(
      // Shopping
      modifier = Modifier
        .weight(categories[1].weight)
        .height(32.dp)
        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
        .background(ShoppingBlue)
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 3000,
            easing = LinearOutSlowInEasing
          )
        ),
    )
    Box(
      // Transportation
      modifier = Modifier
        .weight(categories[2].weight)
        .height(32.dp)
        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
        .background(TransportationYellow)
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 3000,
            easing = LinearOutSlowInEasing
          )
        ),
    )
    Box(
      // Education
      modifier = Modifier
        .weight(categories[3].weight)
        .height(32.dp)
        .clip(shape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp))
        .background(EducationSkyBlue)
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 3000,
            easing = LinearOutSlowInEasing
          )
        ),
    )
    // remaining
    Spacer(
      modifier = Modifier
        .weight(remainingW)
        .animateContentSize(
          animationSpec = tween(
            durationMillis = 3000,
            easing = LinearOutSlowInEasing
          )
        )
    )
  }
}
