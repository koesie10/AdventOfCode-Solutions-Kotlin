package com.koenv.adventofcode

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

object Day5 {
    fun getPassword(input: String): String {
        val matchedString = "0".repeat(5)

        val password = CharArray(8)
        var count = 0

        val md5 = MessageDigest.getInstance("MD5")
        for (i in 0..Integer.MAX_VALUE) {
            val str = input + i.toString()
            val digest = md5.digest(str.toByteArray()).toHexString()
            if (digest.startsWith(matchedString)) {
                password[count++] = digest[5].toLowerCase()
                if (count > 7) {
                    return String(password)
                }
            }
        }

        throw IllegalStateException("Not found")
    }

    fun ByteArray.toHexString(): String {
        return DatatypeConverter.printHexBinary(this)
    }
}