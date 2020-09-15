package com.hairsalon.android.api.users

import io.reactivex.subjects.BehaviorSubject

object UserEmitter {

    var userEmitter: BehaviorSubject<Any?>? = BehaviorSubject.create()

    fun getEmitter(): BehaviorSubject<Any?>? {
        return userEmitter
    }
}