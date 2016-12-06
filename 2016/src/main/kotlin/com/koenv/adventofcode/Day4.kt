package com.koenv.adventofcode

import java.util.*
import java.util.regex.Pattern
import kotlin.comparisons.compareValues

object Day4 {
    val ROOM_NAME_PATTERN: Pattern = Pattern.compile("([a-z\\-]*)-(\\d+)\\[([a-z]{5})\\]")

    fun getRoomName(input: String): RoomName {
        val matcher = ROOM_NAME_PATTERN.matcher(input)

        if (!matcher.find()) {
            throw IllegalStateException("Invalid room name: $input")
        }

        return RoomName(
                matcher.group(1),
                matcher.group(2).toInt(),
                matcher.group(3).toCharArray().toList()
        )
    }

    fun getMostCommonLetters(encryptedName: String) = encryptedName.toCharArray()
            .filter { it != '-' }
            .groupBy { it }
            .values
            .sortedWith(Comparator { lhs, rhs ->
                if (lhs.size == rhs.size) {
                    return@Comparator compareValues(lhs[0], rhs[0])
                }

                compareValues(rhs.size, lhs.size)
            })
            .map { it[0] }
            .take(5)

    fun isChecksumValid(room: RoomName) = room.mostCommonLetters == getMostCommonLetters(room.encryptedName)

    fun getSectorIdSum(input: String) = input.lines()
            .filter(String::isNotBlank)
            .map { getRoomName(it) }
            .filter { isChecksumValid(it) }
            .sumBy { it.sectorId }

    fun decryptName(room: RoomName) = String(room.encryptedName
            .toCharArray()
            .map { shiftLetter(it, room.sectorId) }
            .toCharArray())

    fun shiftLetter(letter: Char, n: Int): Char {
        if (letter == '-') {
            return ' '
        }

        var new = letter

        for (i in 1..n) {
            new = shiftLetter(new)
        }

        return new
    }

    fun shiftLetter(letter: Char): Char {
        if (letter == 'z') {
            return 'a'
        }
        return letter + 1
    }

    fun getNorthPoleObjectStorageSectorId(input: String) = input.lines()
            .filter(String::isNotBlank)
            .map { getRoomName(it) }
            .filter { isChecksumValid(it) }
            .map { decryptName(it) to it.sectorId }
            .find { it.first == "northpole object storage" }!!
            .second

    data class RoomName(
            val encryptedName: String,
            val sectorId: Int,
            val mostCommonLetters: List<Char>
    )
}