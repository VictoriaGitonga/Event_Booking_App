package com.vicky.project2.ui.event_org

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.vicky.project2.data.EventsCreated
import com.vicky.project2.utils.EVENT_ID
import com.vicky.project2.utils.SharedPreferencesConstants

class EventOrganizerViewModel: ViewModel() {

    //pass path to store event organizers
    //make it accessible anywhere inside class
   private val dbEventsCreated = FirebaseDatabase.getInstance().getReference(SharedPreferencesConstants.NODE_EVENTS_CREATED)

    //used to fetch data result
    private val _eventsCreated = MutableLiveData<List<EventsCreated>>()
    val event_created: LiveData<List<EventsCreated>>
            get() = _eventsCreated

    //gets saved data result
    private val _result = MutableLiveData<Exception?>()
        val result:LiveData<Exception?>
            get() = _result

    //saves data to firebase
    fun addEventsCreated(eventsCreated: EventsCreated){

        //creates a unique key to get back node event organizers
        //key is set as id of event organizer
        eventsCreated.id = dbEventsCreated.push().key

        //setValue returns a task
        //then attach setOnClickListener to it which is called when save operation is completed
        dbEventsCreated.child(eventsCreated.id!!).setValue(eventsCreated)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    _result.value = null
                    EVENT_ID = eventsCreated.id!!.toString()
                }else{
                    _result.value = it.exception
                }
            }
    }

    //fetches data from firebase
        fun fetchEventsCreated(){
            dbEventsCreated.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {

                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val event_created = mutableListOf<EventsCreated>()
                        for (eventsCreatedSnapshot in snapshot.children){
                            //gets events created from snapshot
                            val eventsCreated = eventsCreatedSnapshot.getValue(EventsCreated::class.java)
                            //returns id of events created
                            eventsCreated?.id = eventsCreatedSnapshot.key
                            eventsCreated?.let { event_created.add(it) }
                        }
                        _eventsCreated.value = event_created
                    }
                }

            })
        }
}

