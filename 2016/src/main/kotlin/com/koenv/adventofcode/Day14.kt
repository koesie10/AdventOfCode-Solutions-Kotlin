package com.koenv.adventofcode

import com.koenv.adventofcode.Day5.toHexString
import java.security.MessageDigest

object Day14 {
    val md5 = MessageDigest.getInstance("MD5")

    fun findIndexOfOneTimePadKey(salt: String, padKey: Int, part2: Boolean): Int {
        val hashes = hashMapOf<Int, CharArray>()
        val keys = arrayListOf<CharArray>()

        var i = 0

        while (keys.size < padKey) {
            val digest: CharArray
            if (i in hashes) {
                digest = hashes[i]!!
            } else {
                digest = generateHash(salt, i, part2)
                hashes[i] = digest
            }

            check@ for (j in 2..digest.size - 1) {
                if (digest[j] == digest[j - 1] && digest[j] == digest[j - 2]) {
                    for (k in i + 1..i + 1000) {
                        val hash: CharArray
                        if (k in hashes) {
                            hash = hashes[k]!!
                        } else {
                            hash = generateHash(salt, k, part2)
                            hashes[k] = hash
                        }
                        for (l in 4..hash.size - 1) {
                            if (digest[j] == hash[l] && hash[l] == hash[l - 1] && hash[l] == hash[l - 2]
                                    && hash[l] == hash[l - 3] && hash[l] == hash[l - 4]) {
                                keys.add(digest)
                                break@check
                            }
                        }
                    }
                    break@check // only consider the first one
                }
            }

            i++
        }

        return i - 1
    }

    private fun generateHash(salt: String, i: Int, part2: Boolean): CharArray {
        var hashes = 1
        if (part2) {
            hashes = 2017
        }
        var digest = salt + i
        for (j in 1..hashes) {
            digest = md5.digest(digest.toByteArray()).toHexString().toLowerCase()
        }
        return digest.toCharArray()
    }
}