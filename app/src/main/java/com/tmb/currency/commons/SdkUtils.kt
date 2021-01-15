package com.tmb.currency.commons

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.annotation.IntRange

private const val minVersion = Build.VERSION_CODES.LOLLIPOP.toLong()
private const val maxVersion = Build.VERSION_CODES.Q.toLong()

fun versionFrom(@IntRange(from = minVersion, to = maxVersion) versionCode: Int): Boolean =
    SDK_INT >= versionCode


