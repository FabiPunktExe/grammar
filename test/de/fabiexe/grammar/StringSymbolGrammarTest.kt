package de.fabiexe.grammar

import kotlin.test.Test

class StringSymbolGrammarTest {
    val grammar = Grammar.symbol("hello")

    @Test
    fun testExpectedTokens() {
        assertToken("hello", 5, grammar.parse("hello"))
        assertToken("hello", 5, grammar.parse("hello world"))
        assertToken("", 0, StringSymbolGrammar("").parse("abc"))
        assertToken("a", 1, StringSymbolGrammar("a").parse("abc"))
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