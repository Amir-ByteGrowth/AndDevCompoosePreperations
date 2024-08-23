package com.example.workmanager.workers.chainingworkers.custominputmerger

import androidx.work.Data
import androidx.work.InputMerger

class CustomInputMerger : InputMerger() {

    override fun merge(inputs: List<Data>): Data {
        val builder = Data.Builder()

        // Custom logic to merge inputs
        val mergedMap = mutableMapOf<String, String>()

        for (input in inputs) {
            for ((key, value) in input.keyValueMap) {
                val existingValue = mergedMap[key]
                if (existingValue != null) {
                    // Custom merging logic: Here we concatenate the values
                    mergedMap[key] = existingValue + value.toString()
                } else {
                    mergedMap[key] = value.toString()
                }
            }
        }

        builder.putAll(mergedMap as Map<String, Any>)
        return builder.build()
    }
}