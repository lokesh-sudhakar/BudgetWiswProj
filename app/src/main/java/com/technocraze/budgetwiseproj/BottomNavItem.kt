package com.technocraze.budgetwiseproj

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

object NavPath{

  const val HOME = "home"
  const val TRENDS = "trends"
  const val TRANSACTION = "transaction"
  const val COMMUNITY = "community"
  const val PROFILE = "profile"

}

sealed class BottomNavItem(@StringRes val title: Int, val icon: ImageVector, val navRoute: String,
                           ) {

  object Home: BottomNavItem(R.string.home, Icons.Outlined.Home,NavPath.HOME)
  object Trends: BottomNavItem(R.string.trends,Icons.Default.Refresh,NavPath.TRENDS )
  object Transactions: BottomNavItem(R.string.transaction,Icons.Outlined.AddCircle,NavPath.TRANSACTION)
  object Community: BottomNavItem(R.string.community,Icons.Outlined.AccountBox,NavPath.COMMUNITY)
  object Profile: BottomNavItem(R.string.profile,Icons.Outlined.Person,NavPath.PROFILE)

}