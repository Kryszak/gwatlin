package io.github.kryszak.e2e

fun <T> List<T>.randomElements(count: Int): List<T> {
    return this.shuffled().subList(0, count)
}