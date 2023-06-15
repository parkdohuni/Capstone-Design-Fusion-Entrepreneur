package com.example.cooperar.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchingData(
    var address: String? = null,
    var imageUrl: String? = null,
    var money: String? = null,
    var title: String? = null,
    var todo: String? = null,
) : Parcelable
