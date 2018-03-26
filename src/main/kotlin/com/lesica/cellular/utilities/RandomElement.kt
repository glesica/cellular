package com.lesica.cellular.utilities

import java.util.*

private val defaultRandom = Random()

/**
 * Choose a random element from the iterable.
 *
 * Uses a modified version of Algorithm R to achieve
 * O(N) complexity.
 *
 * This method will throw an exception if the iterable
 * is empty, same as the `reduce` method.
 */
fun <T> Iterable<T>.randomElement(random: Random = defaultRandom): T? {
    var i = 1
    return reduce { choice, candidate ->
        val j = random.nextInt(i++)
        if (j == 0) {
            candidate
        } else {
            choice
        }
    }
}
