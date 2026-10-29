package de.fabiexe.grammar

import kotlin.test.Test

class CharSymbolGrammarTest {
    val grammar = Grammar.symbol(setOf('a', 'b', 'c'))

    @Test
    fun testExpectedTokens() {
        assertToken('a', 1, grammar.parse("a"))
        assertToken('b', 1, grammar.parse("b"))
        assertToken('c', 1, grammar.parse("c"))
    }

    @Test
    fun testConsumesOnlyFirstCharacter() {
        assertToken('a', 1, grammar.parse("aaa"))
        assertToken('a', 1, grammar.parse("abc"))
        assertToken('b', 1, grammar.parse("bcd"))
        assertToken('c', 1, grammar.parse("cba"))
    }

    @Test
    fun testExpectedFailures() {
        assertFailure("Expected one of ['a', 'b', 'c']", 0, grammar.parse("def"))
        assertFailure("Expected one of ['a', 'b', 'c']", 0, grammar.parse("dabc"))
        assertFailure("Expected one of ['a', 'b', 'c']", 0, grammar.parse(" abc"))
        assertFailure("Expected one of ['a', 'b', 'c']", 0, grammar.parse(""))
    }
}