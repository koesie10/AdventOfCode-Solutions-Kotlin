package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day4Test {
    @Test
    fun getRoomNameExample1() {
        assertEquals(
                Day4.RoomName("not-a-real-room", 404, listOf('o', 'a', 'r', 'e', 'l')),
                Day4.getRoomName("not-a-real-room-404[oarel]")
        )
    }

    @Test
    fun getMostCommonLetters() {
        assertEquals(
                listOf('a', 'b', 'x', 'y', 'z'),
                Day4.getMostCommonLetters("aaaaa-bbb-z-y-x")
        )
    }

    @Test
    fun isChecksumValid() {
        assertEquals(
                true,
                Day4.isChecksumValid(Day4.RoomName("aaaaa-bbb-z-y-x", 123, listOf('a', 'b', 'x', 'y', 'z')))
        )

        assertEquals(
                true,
                Day4.isChecksumValid(Day4.RoomName("a-b-c-d-e-f-g-h", 987, listOf('a', 'b', 'c', 'd', 'e')))
        )

        assertEquals(
                false,
                Day4.isChecksumValid(Day4.RoomName("totally-real-room", 200, listOf('d', 'e', 'c', 'o', 'y')))
        )
    }

    @Test
    fun part1Example1() {
        assertEquals(
                1514,
                Day4.getSectorIdSum("""
aaaaa-bbb-z-y-x-123[abxyz]
a-b-c-d-e-f-g-h-987[abcde]
not-a-real-room-404[oarel]
totally-real-room-200[decoy]
""")
        )
    }

    @Test
    fun part1Real() {
        assertEquals(245102, Day4.getSectorIdSum(getInput(4)))
    }

    @Test
    fun decryptName() {
        assertEquals("very encrypted name", Day4.decryptName(Day4.RoomName("qzmt-zixmtkozy-ivhz", 343, listOf())))
    }

    @Test
    fun part2Real() {
        assertEquals(324, Day4.getNorthPoleObjectStorageSectorId(getInput(4)))
    }
}