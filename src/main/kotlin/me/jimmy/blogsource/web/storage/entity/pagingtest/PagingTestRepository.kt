package me.jimmy.blogsource.web.storage.entity.pagingtest

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PagingTestRepository : JpaRepository<PagingTestEntity, Long> {
    fun findByRequestDateTimeBetween(startDateTime: LocalDateTime, endDateTime: LocalDateTime, pageable: Pageable) : Page<PagingTestEntity>
}