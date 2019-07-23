package com.madadipouya.redis.springdata.example.subscription.service.impl

import com.madadipouya.redis.springdata.example.model.Movie
import com.madadipouya.redis.springdata.example.subscription.model.Subscriber
import com.madadipouya.redis.springdata.example.subscription.service.SubscriptionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class DefaultSubscriptionService : SubscriptionService {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(DefaultSubscriptionService::class.java)
        val subscribers: MutableSet<Subscriber> = hashSetOf()
    }

    override fun subscribe(subscriber: Subscriber): Subscriber {
        subscribers.add(subscriber)
        return subscriber
    }

    override fun notifySubscribers(movie: Movie) {
        try {
            subscribers.forEach { subscriber ->
                subscriber.send(movie)
                subscriber.onError { error ->
                    logger.info("Seems the subscriber has already dropped out. Remove it from the list")
                    subscriber.completeWithError(error)
                    subscribers.remove(subscriber)
                }
            }
        } catch (ioException: IOException) {
            logger.warn("Failed to notify suscriber about the new Movie = {}, {}, {}", movie.name, movie.genre, movie.year)
        }
    }
}