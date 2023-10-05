package com.technocraze.budgetwiseproj.utils

object Utils {

  fun isNumericToX(toCheck: String?): Boolean {
    return toCheck?.toIntOrNull() != null
  }

}