package de.fabiexe.grammar

import kotlin.test.Test

class StringSymbolGrammarTest {
    val grammar = Grammar.symbol("hello")

    @Test
    fun testExpectedTokens() {
        assertToken(Unit, 5, grammar.parse("hello"))
        assertToken(Unit, 5, grammar.parse("hello world"))
        assertToken(Unit, 0, StringSymbolGrammar("").parse("abc"))
        assertToken(Unit, 1, StringSymbolGrammar("a").parse("abc"))
    }

    @Test
    fun testExpectedFailures() {
        assertFailure("Expected 'hello'", 0, grammar.parse("world"))
        assertFailure("Expected 'hello'", 0, grammar.parse("help"))
        assertFailure("Expected 'hello'", 0, grammar.parse("hell"))
        assertFailure("Expected 'hello'", 0, grammar.parse("Hello"))
        assertFailure("Expected 'hello'", 0, grammar.parse(""))
    }
}