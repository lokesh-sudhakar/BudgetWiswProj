package com.technocraze.budgetwiseproj.composeui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionBtn(
  onFabClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxSize(),
    contentAlignment = Alignment.BottomEnd
  ) {
    FloatingActionButton(

      modifier = Modifier.padding(10.dp),
      backgroundColor = MaterialTheme.colors.primary,
      onClick = {
        onFabClick()
      },
      shape = CircleShape,
    ) {
      Icon(
        Icons.Filled.Add,
        ""
      )
    }
  }
}