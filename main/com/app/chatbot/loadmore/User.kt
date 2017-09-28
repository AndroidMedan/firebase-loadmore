package com.app.chatbot.loadmore

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.app.chatbot.BR

/**
 * Created by putraxor on 27/09/17.
 */
class User() : BaseObservable() {


    constructor(username: String, fullName: String, biodata: String) : this() {
        this.username = username
        this.fullName = fullName
        this.biodata = biodata
    }

    //Property username
    @get:Bindable
    var username = ""
        set(username) {
            field = username
            notifyPropertyChanged(BR.username)
        }

    //Property fullName
    @get:Bindable
    var fullName = ""
        set(fullName) {
            field = fullName
            notifyPropertyChanged(BR.fullName)
        }

    //Property biodata
    @get:Bindable
    var biodata = ""
        set(biodata) {
            field = biodata
            notifyPropertyChanged(BR.biodata)
        }
}