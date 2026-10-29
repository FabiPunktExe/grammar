package de.fabiexe.grammar

import kotlin.test.Test
import kotlin.test.assertFailsWith

class SequenceGrammarTest {
    val abcdeGrammar = Grammar.sequence<Any>(
        Grammar.symbol("ab"),
        Grammar.symbol('c'),
        Grammar.symbol("de")
    )

    @Test
    fun testEmptySequence() {
        assertFailsWith<IllegalArgumentException> { Grammar.sequence<Any>() }
    }

    @Test
    fun testSingleGrammar() {
        val grammar = Grammar.sequence(Grammar.symbol('a'))
        assertToken(listOf('a'), 1, grammar.parse("abc"))
    }

    @Test
    fun testGrammarsAreParsedInOrder() {
        assertToken(listOf("ab", 'c', "de"), 5, abcdeGrammar.parse("abcde"))
    }

    @Test
    fun testStopsAtFirstFailure() {
        assertFailure("Expected one of ['c']", 2, abcdeGrammar.parse("abxde"))
        assertFailure("Expected 'de'", 3, abcdeGrammar.parse("abcdx"))
    }

    @Test
    fun testIgnoresTrailingInput() {
        val grammar = Grammar.sequence(Grammar.symbol("ab"), Grammar.symbol("cd"))
        assertToken(listOf("ab", "cd"), 4, grammar.parse("abcdef"))
    }
}