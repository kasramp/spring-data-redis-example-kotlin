package com.madadipouya.redis.springdata.example.subscription.service

import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.subscription.model.Subscriber

interface SubscriptionService {

    fun subscribe(subscriber: Subscriber): Subscriber

    fun notifySubscribers(movie: Movie)
}