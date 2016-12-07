package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day7Test {
    @Test
    fun parsePartsExample1() {
        assertEquals(
                listOf(Day7.Part(Day7.Type.IP, "abba"), Day7.Part(Day7.Type.HYPERNET, "mnop"), Day7.Part(Day7.Type.IP, "qrst")),
                Day7.parseParts("abba[mnop]qrst")
        )
    }

    @Test
    fun parsePartsExample5() {
        assertEquals(
                listOf(Day7.Part(Day7.Type.IP, "ioxxoj"), Day7.Part(Day7.Type.HYPERNET, "asdfgh"), Day7.Part(Day7.Type.IP, "zxcvbn")),
                Day7.parseParts("ioxxoj[asdfgh]zxcvbn")
        )
    }

    @Test(expected = IllegalStateException::class)
    fun parsePartsInvalidExample() {
        Day7.parseParts("abcd[asdf")
    }

    @Test
    fun containsAbbaExample1() {
        assertTrue(Day7.containsAbba("abba"))
    }

    @Test
    fun containsAbbaExample2() {
        assertFalse(Day7.containsAbba("abcd"))
    }

    @Test
    fun containsAbbaExample3() {
        assertFalse(Day7.containsAbba("aaaa"))
    }

    @Test
    fun containsAbbaExample4() {
        assertTrue(Day7.containsAbba("ioxxoj"))
    }

    @Test
    fun supportsTlsExample1() {
        assertTrue(Day7.supportsTls("abba[mnop]qrst"))
    }

    @Test
    fun supportsTlsExample2() {
        assertFalse(Day7.supportsTls("abcd[bddb]xyyx"))
    }

    @Test
    fun supportsTlsExample3() {
        assertFalse(Day7.supportsTls("aaaa[qwer]tyui"))
    }

    @Test
    fun supportsTlsExample4() {
        assertTrue(Day7.supportsTls("ioxxoj[asdfgh]zxcvbn"))
    }

    @Test
    fun part1Example() {
        assertEquals(2, Day7.countIpsThatSupportTls("""
abba[mnop]qrst
abcd[bddb]xyyx
aaaa[qwer]tyui
ioxxoj[asdfgh]zxcvbn
        """))
    }

    @Test
    fun part1Real() {
        assertEquals(110, Day7.countIpsThatSupportTls(getInput(7)))
    }
}