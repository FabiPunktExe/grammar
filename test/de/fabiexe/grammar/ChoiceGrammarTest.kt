package de.fabiexe.grammar

import kotlin.test.Test
import kotlin.test.assertFailsWith

class ChoiceGrammarTest {
    @Test
    fun testEmptyChoice() {
        assertFailsWith<IllegalArgumentException> { Grammar.choice<Any>() }
    }

    @Test
    fun testSingleGrammar() {
        val grammar = Grammar.choice(Grammar.symbol('a'))
        assertToken('a', 1, grammar.parse("abc"))
    }

    @Test
    fun testFirstMatchingGrammarWins() {
        val grammar = Grammar.choice(Grammar.symbol("ab"), Grammar.symbol('a'))
        assertToken("ab", 2, grammar.parse("ab"))
        assertToken('a', 1, grammar.parse("ac"))
    }

    @Test
    fun testReturnsBestFailureWhenAllFail() {
        val grammar = Grammar.choice(
            Grammar.sequence(Grammar.symbol("ab"), Grammar.symbol('c')),
            Grammar.symbol('z'),
            Grammar.symbol("de")
        )
        assertFailure("Expected a symbol", 2, grammar.parse("abx"))
    }
}