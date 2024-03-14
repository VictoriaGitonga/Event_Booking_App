package com.vicky.project2.data

import com.google.firebase.database.Exclude

data class EventsCreated(
    @get: Exclude
    var id: String? = null,
    var eventName: String? = null,
    var eventDate: String? = null,
    var eventTicketPrice: String? = null,
    var eventLocation: String? = null,
    var eventPoster: String? = null,
    var eventDescription: String? = null

)