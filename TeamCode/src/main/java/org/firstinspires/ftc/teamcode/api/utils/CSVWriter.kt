package org.firstinspires.ftc.teamcode.api.utils

import org.firstinspires.ftc.teamcode.api.utils.itertools.map
import org.firstinspires.ftc.teamcode.api.utils.itertools.separatedBy

class CSVWriter(header: Header) {
    constructor(vararg header: String) : this(Header(*header))

    val header = header.toArray()
    private val data = emptyList<Array<String>>().toMutableList()

    val width: Int
        get() = header.size
    val height: Int
        get() = data.size

    fun addRow(row: Array<String>) {
        if (row.size != header.size) {
            throw IllegalArgumentException("Attempted to add row of size '${row.size}', when header row is of size '${header.size}'")
        }

        data.add(row)
    }

    fun addRow(row: List<String>) = addRow(Array(row.size) {
        row[it]
    })

    /**
     * Same as [addRow], but it uses `vararg`s.
     *
     * # Implementation Detail
     *
     * In Kotlin on the JVM, the `vararg` and [Array] parameters compile to the same thing. This means we can't do
     * function overloading for both, which is why we've suffixed a `V` instead.
     */
    fun addRowV(vararg row: String) = addRow(Array(row.size) {
        row[it]
    })

    override fun toString(): String {
        // Stringify the header row, then convert it to a string builder
        val response = StringBuilder(stringifyRow(header.iterator()) + '\n')

        for (row in data) {
            response.append(stringifyRow(row.iterator()) + '\n')
        }

        return response.toString()
    }

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
    }
}
