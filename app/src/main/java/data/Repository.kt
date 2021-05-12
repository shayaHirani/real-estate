package com.jetbrains.handson.mpp.sentiaandroidcodechallenge.data

import domain.Property

class PropertiesRepository(
    private val internetConnection: PropertiesSource,
) {
    fun getSavedLocations(): List<Property> = internetConnection.getProperties()
}

interface PropertiesSource {
    fun getProperties(): List<Property>
}
//////test/////////////

object Testsample : PropertiesSource {
    override fun getProperties(): List<Property> {
        val listofTestData: List<Property> = listOf(
            Property(id = "1",
                propertyImageURL = "http//dafkhe",
                propertyAddress = "21 duke street",
                agentImageURL = "http//dafkhe",
                agentName = "ali",
                bedroomNumber = 2,
                bathroomNumber = 2,
                carParkNumber = 2,
                companyName = "behpoya",
            ), Property(
                id = "2",
                propertyImageURL = "http//dafkhe",
                propertyAddress = "21 duke street",
                agentImageURL = "http//dafkhe",
                agentName = "ali",
                bedroomNumber = 2,
                bathroomNumber = 2,
                carParkNumber = 2,
                companyName = "behpoya",
            ), Property(
                id = "3",
                propertyImageURL = "http//dafkhe",
                propertyAddress = "21 duke street",
                agentImageURL = "http//dafkhe",
                agentName = "ali",
                bedroomNumber = 2,
                bathroomNumber = 2,
                carParkNumber = 2,
                companyName = "behpoya",
            )
        )

        return listofTestData
    }

}