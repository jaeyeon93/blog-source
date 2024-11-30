package me.jimmy.blogsource.web.storage.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
internal class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "blog-source.datasource")
    fun coreHikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    fun coreDataSource(@Qualifier("coreHikariConfig") config: HikariConfig): HikariDataSource {
        return HikariDataSource(config)
    }

    @Bean
    fun coreJdbcTemplate(@Qualifier("coreDataSource") dataSource: HikariDataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }
}