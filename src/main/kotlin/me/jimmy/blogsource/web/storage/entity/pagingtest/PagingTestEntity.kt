package me.jimmy.blogsource.web.storage.entity.pagingtest

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "paging_test")
data class PagingTestEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val requestDateTime:  LocalDateTime,
)