package me.jimmy.blogsource.web.storage.entity.blog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogTestJpaRepository : JpaRepository<BlogTestEntity, Long> {
}