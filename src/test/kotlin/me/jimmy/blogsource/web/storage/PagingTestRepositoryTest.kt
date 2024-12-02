package me.jimmy.blogsource.web.storage

import me.jimmy.blogsource.ComparisonUtils
import me.jimmy.blogsource.IntegrationTest
import me.jimmy.blogsource.web.storage.entity.pagingtest.PagingTestEntity
import me.jimmy.blogsource.web.storage.entity.pagingtest.PagingTestRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.time.LocalDate
import java.time.LocalDateTime

@IntegrationTest
internal class PagingTestRepositoryTest(
    private val sut: PagingTestRepository,
) {

    @Test
    fun `정렬조건이 없으면 쿼리에 order by가 없는걸 확인하는 테스트`() {
        val requestDateTime = LocalDateTime.of(2024, 12, 3, 0, 3, 0)
        val title = "saveTest"
        val entity = PagingTestEntity(
            title = title, requestDateTime = requestDateTime,
        )

        sut.save(entity)

         val result = sut.findByRequestDateTimeBetween(
             startDateTime = LocalDateTime.of(2024, 12, 3, 0, 2, 0),
             endDateTime = LocalDateTime.of(2024, 12, 3, 0, 4, 0),
             pageable = PageRequest.of(0, 10, Sort.by("requestDateTime").descending()),
         ).content
        assertTrue(result.isNotEmpty())
        assertEquals("saveTest", result.first().title)
    }
}