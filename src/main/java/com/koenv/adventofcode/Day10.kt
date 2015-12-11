package com.koenv.adventofcode

object Day10 {
    fun lookAndSay(input: String): String {
        var lastCharacter: Char? = null
        var characterCount = 0
        var result = StringBuilder()

        input.forEach {
            if (it != lastCharacter) {
                if (lastCharacter != null) {
                    result.append(characterCount)
                    result.append(lastCharacter!!)
                }
                lastCharacter = it
                characterCount = 0
            }
            characterCount++
        }
        
        result.append(characterCount)
        result.append(lastCharacter!!)

        return result.toString()
    }
}