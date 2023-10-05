package com.technocraze.budgetwiseproj.composeui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technocraze.budgetwiseproj.Category
import com.technocraze.budgetwiseproj.DropDownTextField
import com.technocraze.budgetwiseproj.utils.Utils

@Composable
fun BootomSheetContent(
  category: SnapshotStateList<Category>,
  onSaveClick: (String, Int) -> Unit,
  isSheetFullScreen: Boolean = false
) {
  val context= LocalContext.current
  val configuration = LocalConfiguration.current
  val screenHeight = configuration.screenHeightDp.dp
  val modifier = if (isSheetFullScreen)
    Modifier.fillMaxSize()
  else
    Modifier
      .height(screenHeight / 2)
      .fillMaxWidth()
  Column(
    modifier = modifier.padding(12.dp),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
  ) {
    Text(
      text = "Add Expenses",
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    Column(
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.Top,
    ) {
      var selectedCategory = remember {
        mutableStateOf<String>(category[0].name)
      }
      DropDownTextField(category.map { it.name },
        defaultCategory = selectedCategory.value) { categoryName ->
        selectedCategory.value = categoryName
      }
      Spacer(modifier = Modifier.height(10.dp))
      var amountAdded by remember {
        mutableStateOf("0")
      }

      TextField(
        modifier = Modifier.fillMaxWidth(),
        label = {
          Text("Enter Amount")
        },
        value = amountAdded,
        onValueChange = {
          amountAdded = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
      )

      Button(onClick = {
        if (Utils.isNumericToX(amountAdded) ){
          var category = category.find { it.name == selectedCategory.value }!!
          if (amountAdded.toInt()<0){
            Toast.makeText(context, "Amount should not be less than 0", Toast.LENGTH_SHORT).show()
            return@Button
          }
          if (amountAdded.toInt() >category.totalBudget-category.amountSpent){
            Toast.makeText(context, "Amount Exceeds the budget limit of the category", Toast.LENGTH_SHORT).show()
            return@Button
          }
        } else {
          Toast.makeText(context, "Enter Valid amount", Toast.LENGTH_SHORT).show()
          return@Button
        }

        selectedCategory?.let {
          if (!amountAdded.isEmpty()) {
            onSaveClick(it.value, amountAdded.toInt())
          }
        }

      }) {
        Text(text = "Save")
      }
    }

  }
}