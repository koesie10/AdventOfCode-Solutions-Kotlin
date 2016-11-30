package com.koenv.adventofcode

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter


object Day4 {
    fun getIndexOfZeroesAtStart(input: String, numberOfZeroes: Int): Int {
        val matchedString = "0".repeat(numberOfZeroes)

        val md5 = MessageDigest.getInstance("MD5")
        for (i in 0..Integer.MAX_VALUE) {
            val str = input + i.toString()
            if (md5.digest(str.toByteArray()).toHexString().startsWith(matchedString)) {
                return i
            }
        }

        throw IllegalStateException("No value found with $numberOfZeroes zeroes at start")
    }

    public fun ByteArray.toHexString(): String {
        return DatatypeConverter.printHexBinary(this)
    }
}