package me.jimmy.blogsource.web.storage

import me.jimmy.blogsource.ComparisonUtils
import me.jimmy.blogsource.IntegrationTest
import me.jimmy.blogsource.web.storage.entity.blog.BlogTestEntity
import me.jimmy.blogsource.web.storage.entity.blog.BlogTestJdbcRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

@IntegrationTest
internal class BlogTestJdbcRepositoryTest(
    private val sut: BlogTestJdbcRepository,
) {
    @Test
    fun saveTest() {
        val entity = BlogTestEntity(
            testString = null,
            testBoolean = true,
            testInt = null,
            testLong = null,
            testBigDecimal = null,
            testDouble = null,
            testFloat = null,
            testByte = null,
            testShort = null
        )

        assertDoesNotThrow { sut.save(entity) }

        // then
        val resultList = assertDoesNotThrow { sut.findAll() }
        assertTrue(resultList.isNotEmpty())
        val result = resultList.first()

        ComparisonUtils.isEqualsTo(
            result,
            BlogTestEntity(
                testString = null,
                testBoolean = false,
                testInt = 0,
                testLong = 0L,
                testBigDecimal = null,
                testDouble = 0.0,
                testFloat = 0F,
                testByte = 0,
                testShort = 0
            ),
            ignoreFields = listOf("id")
        )


        sut.deleteById(result.id!!)
    }
}