@file:JvmName("GrammarJvm")

package de.fabiexe.grammar

import java.util.*
import java.util.function.Function
import java.util.function.Supplier

fun empty(): Grammar<Void?> = JvmEmptyGrammar

fun <T> transform(grammar: Grammar<Void?>, transform: Supplier<T>): Grammar<T> {
    return JvmTransformingGrammar(grammar) { _ -> transform.get() }
}

fun <T, U> transform(grammar: Grammar<T>, transform: Function<T, U>): Grammar<U> {
    return JvmTransformingGrammar(grammar, transform)
}

fun <T> recursive(definition: Function<Grammar<T>, Grammar<T>>): Grammar<T> {
    return JvmRecursiveGrammar(definition)
}

fun <T : Any> optional(grammar: Grammar<T>): Grammar<Optional<T>> {
    return OptionalGrammar(grammar)
}

fun <T : Any> optional(grammar: Grammar<T>, defaultValue: T): Grammar<T> {
    return OptionalGrammar(grammar).transform { it.orElse(defaultValue)!! }
}