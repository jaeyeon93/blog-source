package me.jimmy.blogsource.web.storage

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
        val testString = "testString"
        val entity = BlogTestEntity(
            testString = testString,
            testBoolean = null,
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
        val resultList = assertDoesNotThrow { sut.findByTestString(testString) }
        assertTrue(resultList.isNotEmpty())

        sut.deleteById(resultList.first().id!!)
    }

    @Test
    fun saveTest2() {
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
    }
}