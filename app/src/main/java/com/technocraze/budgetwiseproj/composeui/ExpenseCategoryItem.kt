package com.technocraze.budgetwiseproj.composeui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technocraze.budgetwiseproj.Category
import com.technocraze.budgetwiseproj.ui.theme.Green
import com.technocraze.budgetwiseproj.ui.theme.Grey

@Composable
fun ExpenseCategoryItem(category: Category) {
  val localDensity = LocalDensity.current
  val weight by animateFloatAsState(
    targetValue = category.amountSpent.toFloat()/category.totalBudget,
    animationSpec = tween(3000),
    label="animatio",
    finishedListener = {
      it.toFloat()/category.totalBudget
    }
  )

  Column(Modifier.padding(bottom = 12.dp)) {
    Row(
      Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp, top = 0.dp, start = 20.dp, end = 20.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      var iconHeight by remember {
        mutableStateOf(0.dp)
      }
      Image(
        modifier = Modifier
          .size(40.dp)
          .onGloballyPositioned {
            iconHeight = with(localDensity)
            { it.size.height.toDp() }
          }
          .weight(0.3f),
        painter = painterResource(id = category.icon),
        contentDescription = ""
      )
      Column(
        Modifier
          .height(iconHeight)
          .padding(horizontal = 5.dp)
          .weight(1f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = category.name,
          fontWeight = FontWeight.Bold,
          maxLines = 1
        )
        Text(
          text = "Spent $${category.amountSpent} of $${category.totalBudget}",
          style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            fontSize = 12.sp,
          ),
          maxLines = 1
        )
      }
      Column(
        modifier = Modifier
          .height(iconHeight)
          .weight(0.2f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Text(
          text = "$${category.totalBudget - category.amountSpent}",
          style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Green,
          ), maxLines = 1
        )
        Text(
          text = "left",
          fontWeight = FontWeight.Light,
          fontSize = 12.sp,
          maxLines = 1
        )
      }
    }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(5.dp)
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
          .weight(if (weight ==0.0f) 0.000001f else weight)
          .height(5.dp)
          .clip(shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
          .background(category.color)
          .animateContentSize(
            animationSpec = tween(
              durationMillis = 3000,
              easing = LinearOutSlowInEasing
            )
          ),
      )
      Spacer(
        modifier = Modifier
          .weight(if ((1-weight) ==0.0f) 0.000001f else (1-weight))
          .animateContentSize(
            animationSpec = tween(
              durationMillis = 3000,
              easing = LinearOutSlowInEasing
            )
          )
      )
    }
  }
}