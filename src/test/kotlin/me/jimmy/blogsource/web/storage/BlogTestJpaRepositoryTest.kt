package me.jimmy.blogsource.web.storage

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import me.jimmy.blogsource.ComparisonUtils
import me.jimmy.blogsource.IntegrationTest
import me.jimmy.blogsource.web.storage.entity.blog.BlogTestEntity
import me.jimmy.blogsource.web.storage.entity.blog.BlogTestJpaRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.data.repository.findByIdOrNull

@IntegrationTest
internal class BlogTestJpaRepositoryTest(
    private val sut: BlogTestJpaRepository,
    private val entityManager: EntityManager,
) {
    @Test
    fun `jpaSaveTest`() {
        val entity = BlogTestEntity(
            testString = null,
            testBoolean = null,
            testInt = null,
            testLong = null,
            testBigDecimal = null,
            testDouble = null,
            testFloat = null,
            testByte = null,
            testShort = null
        )

        val savedEntity = assertDoesNotThrow { sut.save(entity) }

        val result = sut.findByIdOrNull(savedEntity.id)

        ComparisonUtils.isEqualsTo(
            result,
            entity
        )

        sut.deleteById(savedEntity.id)
    }
}