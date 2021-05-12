package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.framework.network

import com.squareup.moshi.Json
import domain.Property


data class NetworkResponse(
    @Json(name = "data") val networkPropertyList: List<NetworkProperty>
)

data class NetworkProperty(
    @Json(name = "id") val facilityIid: String,
    @Json(name = "auction_date") val auctionDate: String,
    @Json(name = "available_from") val availableFrom: String,
    val bedrooms: Int,
    val bathrooms: Int,
    @Json(name = "carspaces") val carSpaces: Int,
    @Json(name = "date_first_listed") val dateFirstListed: String,
    @Json(name = "date_updated") val dateUpDated: String,
    val description: String,
    @Json(name = "display_price") val displayPrice: String,
    val currency: String,
    val location: Location,
    val owner: Owner,
    @Json(name = "property_images") val propertyImages: List<PropertyImage>,
    val agent: Agent,
    @Json(name = "property_type") val propertyType: String,
    @Json(name = "sale_type") val saleType: String,
)

data class Location(
    val address: String,
    val state: String,
    val suburb: String,
    val postcode: String?,
    val latitude: Double,
    val longitude: Double,
)

data class Owner(
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "dob") val dateOfBerth: String,
    @Json(name = "avatar") val PersonImage: SizeAbleImage
)

data class Agent(
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "company_name") val companyName: String,
    @Json(name = "avatar") val avatar: SizeAbleImage
)

data class SizeAbleImage(
    val small: ImageURL,
    val medium: ImageURL,
    val large: ImageURL,
)

data class PropertyImage(
    val id: String,
    val attachment: Attachment
)

data class Attachment(
    val url: String,
    @Json(name = "thumb") val small: ImageURL,
    val medium: ImageURL,
    val large: ImageURL
)

data class ImageURL(
    val url: String
)

fun NetworkResponse.asDomainModel(): List<Property> {
    return networkPropertyList.map {
        Property(
            id = it.facilityIid,
            propertyImageURL = it.propertyImages[0].attachment.small.url,
            propertyAddress = it.location.address,
            agentImageURL = it.agent.avatar.small.url,
            agentName = it.agent.firstName + " " + it.agent.lastName,
            bedroomNumber = it.bedrooms,
            bathroomNumber = it.bathrooms,
            carParkNumber = it.carSpaces,
            companyName = it.agent.companyName,
        )
    }
}

