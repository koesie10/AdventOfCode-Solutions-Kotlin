package com.koenv.adventofcode

import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun part1SatisfiesRequirementsExample1() {
        assertEquals(false, Day11.satisfiesRequirements("hijklmmn"))
    }

    @Test
    fun part1SatisfiesRequirementsExample2() {
        assertEquals(false, Day11.satisfiesRequirements("abbceffg"))
    }

    @Test
    fun part1SatisfiesRequirementsExample3() {
        assertEquals(false, Day11.satisfiesRequirements("abbcegjk"))
    }

    @Test
    fun part1SatisfiesRequirementsExample4() {
        assertEquals(true, Day11.satisfiesRequirements("abcdffaa"))
    }

    @Test
    fun part1SatisfiesRequirementsExample5() {
        assertEquals(true, Day11.satisfiesRequirements("ghjaabcc"))
    }

    @Test
    fun part1SatisfiesRequirementsExtraExample6() {
        assertEquals(false, Day11.satisfiesRequirements("abcegjkaa"))
    }

    @Test
    fun part1NextPasswordExample1() {
        assertEquals("ab", Day11.nextPassword("aa"))
    }

    @Test
    fun part1NextPasswordExample2() {
        assertEquals("ba", Day11.nextPassword("az"))
    }

    @Test
    fun part1Example1() {
        assertEquals("abcdffaa", Day11.findNextPasswordSatisfyingRequirements("abcdefgh"))
    }

    @Test
    fun part1Example2() {
        assertEquals("ghjaabcc", Day11.findNextPasswordSatisfyingRequirements("ghijklmn"))
    }

    @Test
    fun part1Real() {
        assertEquals("vzbxxyzz", Day11.findNextPasswordSatisfyingRequirements("vzbxkghb"))
    }

    @Test
    fun part2Real() {
        assertEquals("vzcaabcc", Day11.findNextPasswordSatisfyingRequirements("vzbxxyzz"))
    }
}