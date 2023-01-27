package org.firstinspires.ftc.teamcode.api.plugins.logger

import CSVWriter
import android.os.Environment
import java.io.BufferedWriter
import java.io.Closeable
import java.io.File
import java.io.FileWriter

const val DATA_FILE_PATH = "$LOG_FILE_PATH/data.csv"

class DataCollector: Closeable {
    private val callbacks = emptyMap<String, () -> String?>().toMutableMap()
    private var table: CSVWriter? = null

    private val file = File(DATA_FILE_PATH)
    private val writer by lazy {
        // Lazy create new file, so Logger has time to create the /logs folder
        file.createNewFile()
        BufferedWriter(FileWriter(file))
    }

    override fun close() = writer.close()

    fun registerCallback(name: String, func: () -> String?) = if (table == null) {
        callbacks[name] = func
    } else {
        throw IllegalStateException("Attempted to register data callback '$name' after collectData was already called")
    }

    fun collect() {
        if (table == null) {
            table = CSVWriter(CSVWriter.Header(callbacks.keys.toList()), writer)
        }

        table!!.writeRow(Array(table!!.header.size) {
            // If callback returns null, map it to an empty string
            callbacks[table!!.header[it]]!!() ?: ""
        })
    }
}
