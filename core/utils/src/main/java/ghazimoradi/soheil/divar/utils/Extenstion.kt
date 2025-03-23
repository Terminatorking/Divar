package ghazimoradi.soheil.divar.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

inline fun <reified T> T?.toJson(): String? {
    return if (this == null) return null
    else json.encodeToString(this)
}

inline fun <reified T> String?.fromJson(): T? {
    return if (this.isNullOrEmpty()) return null
    else json.decodeFromString(this)
}

inline fun <T> Iterable<T>.findIndex(predicate: (T) -> Boolean): Int? {
    this.forEachIndexed { index, t ->
        if (predicate(t)) {
            return index
        }
    }
    return null
}
