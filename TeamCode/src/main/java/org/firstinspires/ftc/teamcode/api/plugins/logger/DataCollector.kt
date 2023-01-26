package org.firstinspires.ftc.teamcode.api.plugins.logger

import org.firstinspires.ftc.teamcode.api.utils.CSVWriter

class DataCollector {
    private val callbacks = emptyMap<String, () -> String?>().toMutableMap()
    private var table: CSVWriter? = null

    fun registerCallback(name: String, func: () -> String?) = if (table == null) {
        callbacks[name] = func
    } else {
        throw IllegalStateException("Attempted to register data callback '$name' after collectData was already called")
    }

    fun collect() {
        if (table == null) {
            table = CSVWriter(CSVWriter.Header(callbacks.keys.toList()))
        }

        table!!.addRow(Array(table!!.width) {
            // If callback returns null, map it to an empty string
            callbacks[table!!.header[it]]!!() ?: ""
        })
    }

    fun export(): String = table!!.toString()
}
