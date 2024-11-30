package me.jimmy.blogsource.web.storage.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["me.jimmy.blogsource.web.storage.entity"])
internal class JpaConfig {
}