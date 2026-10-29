package de.fabiexe.grammar

import kotlin.test.Test

class TransformingGrammarTest {
    @Test
    fun testTransformsToken() {
        val grammar = Grammar.symbol("ab").transform { it.uppercase() }
        assertToken("AB", 2, grammar.parse("abc"))
    }

    @Test
    fun testTransformsToDifferentType() {
        val grammar = Grammar.symbol('a').transform { it.code }
        assertToken(97, 1, grammar.parse("abc"))
    }

    @Test
    fun testPreservesFailure() {
        val grammar = Grammar.symbol("ab").transform { it.uppercase() }
        assertFailure("Expected 'ab'", 0, grammar.parse("xbc"))
    }

    @Test
    fun testTransformsSequenceResult() {
        val grammar = Grammar.sequence(
            Grammar.symbol("a"),
            Grammar.symbol('b'),
            Grammar.symbol("cd")
        ).transform { it.joinToString("-") }
        assertToken("a-b-cd", 4, grammar.parse("abcd"))
    }

    @Test
    fun testChainedTransforms() {
        val grammar = Grammar.symbol("ab")
            .transform { it.length }
            .transform { it * 2 }
        assertToken(4, 2, grammar.parse("ab"))
    }
}
