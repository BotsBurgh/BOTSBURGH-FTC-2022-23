package org.firstinspires.ftc.teamcode.api.utils

import org.firstinspires.ftc.teamcode.api.utils.itertools.map
import org.firstinspires.ftc.teamcode.api.utils.itertools.separatedBy
import java.io.Writer

class CSVWriter(header: Header, private val writer: Writer) {
    val header = header.toArray()

    init {
        writer.write(stringifyRow(header.iterator()) + '\n')
    }

    fun writeRow(row: Array<String>) {
        if (row.size != header.size) {
            throw IllegalArgumentException("Attempted to add row of size '${row.size}', when header row is of size '${header.size}'")
        }

        writer.write(stringifyRow(row.iterator()) + '\n')
    }

    fun writeRow(row: List<String>) = writeRow(Array(row.size) {
        row[it]
    })

    /**
     * Same as [writeRow], but it uses `vararg`s.
     *
     * # Implementation Detail
     *
     * In Kotlin on the JVM, the `vararg` and [Array] parameters compile to the same thing. This means we can't do
     * function overloading for both, which is why we've suffixed a `V` instead.
     */
    fun writeRowV(vararg row: String) = writeRow(Array(row.size) {
        row[it]
    })

    private fun stringifyRow(row: Iterator<String>): String {
        val res = StringBuilder()

        row.map { proofString(it) }.separatedBy(",").forEach { res.append(it) }

        return res.toString()
    }

    /**
     * formats a string so that it can be embedded in a CSV file.
     *
     * # Rules
     *
     * - Text with a comma `,` in it must be surrounded in double quotes `"<text>"`.
     * - Text with a double quote `"` in it must replace it with a pair of double quotes, then surround the text in double quotes.
     *
     * # Examples
     *
     * - `normal text` -> `normal text`
     * - `text with, comma` -> `"text with, comma"`
     * - `hi "there"` -> `"hi ""there"""`
     */
    private fun proofString(x: String): String {
        var res = x.replace('\n', ' ')

        if (res.contains(',') || res.contains('"')) {
            res = '"' + res.replace("\"", "\"\"") + '"'
        }

        return res
    }

    class Header(header: List<String>) {
        constructor(vararg header: String) : this(header.toList())

        private val header = header.toMutableList()

        fun addHeader(name: String) {
            header.add(name)
        }

        fun toArray(): Array<String> {
            return Array(header.size) {
                header[it]
            }
        }

        operator fun iterator() = header.iterator()
    }
}
