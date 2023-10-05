package com.technocraze.budgetwiseproj


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomNavigation(navController: NavController) {

  val navItems = remember {
    listOf(BottomNavItem.Home,  BottomNavItem.Trends,
      BottomNavItem.Transactions, BottomNavItem.Community,BottomNavItem.Profile)
  }
  BottomNavigation(backgroundColor = Color.White) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    navItems.forEach{navItem->
      // val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
      var isSelected = currentRoute==navItem.navRoute
      BottomNavigationItem(
        icon = {
          Column(modifier = Modifier
            .padding(0.dp)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {
            if (isSelected){
              Divider(color = MaterialTheme.colors.primary, thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 20.dp))
            } else {
              Spacer(modifier = Modifier.padding(2.dp))
            }
            Column(modifier=Modifier.weight(1f),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally) {
              Icon(
                imageVector = navItem.icon,
                modifier = Modifier.padding(0.dp), contentDescription = "")
              Text(text = stringResource(id = navItem.title),
                fontSize = 12.sp,
                color=if (isSelected)MaterialTheme.colors.primary else Color.Black,
                overflow = TextOverflow.Ellipsis)
            }
          }
        },
        label = {
          Text(text = stringResource(id = navItem.title), overflow = TextOverflow.Ellipsis)
        }, alwaysShowLabel = false,
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = Color.Black,
        selected = currentRoute == navItem.navRoute,
        onClick = {
          // navItems.forEach { navItem.isSelected=false }
          // navItem.isSelected = true
          navController.navigate(navItem.navRoute)
        },

      )

    }
  }
}