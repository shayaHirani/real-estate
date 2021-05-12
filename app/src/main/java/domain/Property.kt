package domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(
    val id: String,
    val propertyImageURL: String,
    val propertyAddress: String,
    val agentImageURL: String,
    val agentName: String,
    val bedroomNumber: Int,
    val bathroomNumber: Int,
    val carParkNumber: Int,
    val companyName : String,
):Parcelable