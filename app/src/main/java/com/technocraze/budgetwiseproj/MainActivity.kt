package com.technocraze.budgetwiseproj

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.technocraze.budgetwiseproj.composeui.BootomSheetContent
import com.technocraze.budgetwiseproj.ui.theme.BudgetWiseProjTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


  @OptIn(ExperimentalMaterialApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BudgetWiseProjTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          val navController = rememberNavController()
          val coroutineScope = rememberCoroutineScope()
          val modalSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
            skipHalfExpanded = true,
          )
          val viewModel:HomeViewModel = viewModel(LocalContext.current as ComponentActivity)
          var categories =viewModel.categories
          var isSheetFullScreen by remember { mutableStateOf(false) }
          val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp

          // handling back whe bottom sheet is visible
          BackHandler(modalSheetState.isVisible) {
              coroutineScope.launch {
                modalSheetState.hide()
              }

          }

          ModalBottomSheetLayout(sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
            sheetContent = {
              BootomSheetContent(categories, { cat, amount ->
                coroutineScope.launch {
                  modalSheetState.hide()
                  viewModel.addExpense(cat,amount)
                }
              })
            }) {
            Scaffold(
              bottomBar = {
                AppBottomNavigation(navController = navController)
              },
            ) {
              it

              NavHost(
                modifier = Modifier.padding(it),
                navController = navController, startDestination = NavPath.HOME
              ) {
                composable(NavPath.HOME) {
                  HomeScreen(modalSheetState)
                }
                composable(NavPath.TRENDS) {
                  TrendsScreen()
                }
                composable(NavPath.TRANSACTION) {
                  TransactionsScreen()
                }
                composable(NavPath.COMMUNITY) {
                  CommunityScreen()
                }
                composable(NavPath.PROFILE) {
                  ProfileScreen()
                }
              }

            }
          }


        }
      }
    }
  }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownTextField(
  categoryList: List<String>,
  defaultCategory: String = categoryList[0],
  onCategorySelected: (String) -> Unit
) {

  val context = LocalContext.current
  val coffeeDrinks = categoryList
  var expanded by remember { mutableStateOf(false) }
  var selectedText by remember { mutableStateOf(categoryList[0]) }
  Box(
    modifier = Modifier
      .fillMaxWidth(),
    contentAlignment = Alignment.TopStart
  ) {
    ExposedDropdownMenuBox(modifier = Modifier.fillMaxWidth(),
      expanded = expanded,
      onExpandedChange = {
        expanded = !expanded
      }
    ) {
      TextField(
        modifier = Modifier.fillMaxWidth(),
        value = selectedText,
        onValueChange = {},
        readOnly = true,
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
      )

      ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
      ) {
        categoryList.forEach { item ->
          DropdownMenuItem(
            onClick = {
              selectedText = item
              expanded = false
              onCategorySelected(item)
            },
          ) {
            Text(text = item)
          }

        }
      }
    }
  }
}

@Composable
fun TrendsScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(text = "Trends Screen", color = Color.Black)
  }
}

@Composable
fun TransactionsScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(text = "Transactions Screen", color = Color.Black)
  }
}

@Composable
fun CommunityScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(text = "Community Screen", color = Color.Black)
  }
}

@Composable
fun ProfileScreen() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(text = "Profile Screen", color = Color.Black)
  }
}
