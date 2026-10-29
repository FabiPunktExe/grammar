package de.fabiexe.grammar

import kotlin.test.Test
import kotlin.test.assertFailsWith

class RepeatingGrammarTest {
    @Test
    fun testNegativeMinIsRejected() {
        assertFailsWith<IllegalArgumentException> {
            Grammar.symbol('a').repeat(-1)
        }
    }

    @Test
    fun testRepeatsUntilFailure() {
        val grammarMin0 = Grammar.symbol('a').repeat(0)
        assertToken(listOf('a', 'a', 'a', 'a'), 4, grammarMin0.parse("aaaab"))

        val grammarMin2 = Grammar.symbol('a').repeat(2)
        assertToken(listOf('a', 'a', 'a', 'a'), 4, grammarMin2.parse("aaaab"))
    }

    @Test
    fun testAllowsZeroRepetitions() {
        val grammar = Grammar.symbol('a').repeat(0)
        assertToken(listOf(), 0, grammar.parse("b"))
        assertToken(listOf(), 0, grammar.parse(""))
    }

    @Test
    fun testFailsBelowMin() {
        val grammar = Grammar.symbol('a').repeat(3)
        assertFailure("Expected a symbol", 2, grammar.parse("aab"))
        assertFailure("Expected a symbol", 0, grammar.parse(""))
    }
}