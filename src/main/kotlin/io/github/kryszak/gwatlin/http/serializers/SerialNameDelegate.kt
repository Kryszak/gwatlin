package io.github.kryszak.gwatlin.http.serializers

import kotlinx.serialization.SerialName
import kotlin.reflect.KProperty

/**
 * Use when you want to read the [SerialName] of a class via a property.
 * Uses Java reflection to get the concrete class's annotation. Fails
 * if class is not annotated.
 *
 * Notice that this won't interfere with the Kotlin Serialization's
 * type field based polymorphic serialization, even if you call the
 * field `type`. This is because delegated properties don't have a
 * backing field and are ignored by default by the framework.
 *
 * ```kotlin
 * @Serializable
 * @SerialName("Foobar")
 * data class Foobar(val a: Int) {
 *   val type by SerialNameDelegate()
 * }
 * ```
 */
internal class SerialNameDelegate<T>(val mapper: (String) -> T) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        val serialName = requireNotNull(thisRef.javaClass.getAnnotation(SerialName::class.java)) {
            "${thisRef.javaClass} not annotated with @SerialName"
        }.value
        return mapper(serialName)
    }

    companion object {
        /**
         * Default [SerialNameDelegate] for String fields
         */
        val serialNameDelegate = SerialNameDelegate { it }
    }
}
