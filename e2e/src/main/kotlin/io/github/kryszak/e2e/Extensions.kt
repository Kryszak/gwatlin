package io.github.kryszak.e2e

/**
 * Retrieves random elements from list
 * @param count of elements to be returned
 */
fun <T> List<T>.randomElements(count: Int): List<T> {
    return this.shuffled().subList(0, count)
}