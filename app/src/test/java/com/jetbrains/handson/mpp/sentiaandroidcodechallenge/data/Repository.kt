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