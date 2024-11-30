package me.jimmy.blogsource

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration
import org.assertj.core.util.BigDecimalComparator
import java.math.BigDecimal

internal object ComparisonUtils {
    fun <T> isEqualsTo(
        result: T,
        expected: T,
        ignoreFields: List<String> = emptyList()
    ) {
        if (result is Unit || expected is Unit) {
            throw IllegalArgumentException("Unit Type is not supported")
        }

        assertThat(result).usingRecursiveComparison(recursiveComparisonConfiguration)
            .ignoringFields(*ignoreFields.toTypedArray())
            .isEqualTo(expected)
            .withFailMessage { "result : $result, expected : $expected" }
    }

    private val recursiveComparisonConfiguration =
        RecursiveComparisonConfiguration.builder()
            .withIgnoredFields("id")
            .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal::class.java)
            .build()
}