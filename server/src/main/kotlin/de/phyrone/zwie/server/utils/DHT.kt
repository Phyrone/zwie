package de.phyrone.zwie.server.utils

import com.google.common.hash.Funnel
import com.google.common.hash.Hashing

class DHT<Key, Value>(
    private val keyFunnel: Funnel<Key>,
    private val valueFunnel: Funnel<Value>,
    seed: Int = 0,
) {
    private val hasher = Hashing.murmur3_128(seed)
    private val values = mutableMapOf<Long, Value>()


    operator fun get(key: Key): Value {
        if (values.isEmpty()) error("DHT is empty")

        val hash = hasher.hashObject(key, keyFunnel).asLong()

        val valueKey = values.keys.sorted().firstOrNull { candidate -> candidate >= hash } ?: values.keys.first()

        return values[valueKey]!!
    }

    fun addValue(value: Value) {
        values[hasher.hashObject(value, valueFunnel).asLong()] = value
    }

    fun removeValue(value: Value) {
        values.values.remove(value)
    }


    companion object {
        private val hashing = Hashing.murmur3_32_fixed()
    }
}