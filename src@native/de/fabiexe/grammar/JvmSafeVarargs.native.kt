package de.fabiexe.grammar

@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal actual annotation class JvmSafeVarargs