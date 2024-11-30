package me.jimmy.blogsource.web.storage.entity.blog

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository

@Repository
internal class BlogTestJdbcRepository(
    @Qualifier("coreJdbcTemplate") private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val blogTestMapper: BlogTestMapper,
) {
    fun save(entity: BlogTestEntity) {
        val query = """
            insert into blog_test (
                test_string,
                test_boolean,
                test_int,
                test_long,
                test_big_decimal,
                test_double,
                test_float,
                test_byte,
                test_short
            ) values (
                :testString,
                :testBoolean,
                :testInt,
                :testLong,
                :testBigDecimal,
                :testDouble,
                :testFloat,
                :testByte,
                :testShort
            )
        """.trimIndent()

        jdbcTemplate.update(query, entity.sqlParameterSource())
    }


    fun findByTestString(testString: String): List<BlogTestEntity> {
        val query = """
             select
                 id,
                 test_string,
                 test_boolean,
                 test_int,
                 test_long,
                 test_big_decimal,
                 test_double,
                 test_float,
                 test_byte,
                 test_short
             from blog_test where 1=1
             and test_string = :testString
        """.trimIndent()

        return jdbcTemplate.query(
            query,
            mapOf(
                "testString" to testString
            ),
            blogTestMapper
        )
    }

    fun findAll(): List<BlogTestEntity> {
        val query = """
             select
                 id,
                 test_string,
                 test_boolean,
                 test_int,
                 test_long,
                 test_big_decimal,
                 test_double,
                 test_float,
                 test_byte,
                 test_short
             from blog_test
        """.trimIndent()

        return jdbcTemplate.query(
            query,
            emptyMap<String, String>(),
            blogTestMapper
        )
    }

    fun deleteById(id: Long) {
        val query = """
            delete from blog_test where id = :id
        """.trimIndent()

        jdbcTemplate.update(query, mapOf("id" to id))
    }

    private fun BlogTestEntity.sqlParameterSource(): SqlParameterSource = MapSqlParameterSource()
        .addValue("testString", this.testString)
        .addValue("testBoolean", this.testBoolean)
        .addValue("testInt", this.testInt)
        .addValue("testLong", this.testLong)
        .addValue("testBigDecimal", this.testBigDecimal)
        .addValue("testDouble", this.testDouble)
        .addValue("testFloat", this.testFloat)
        .addValue("testByte", this.testByte)
        .addValue("testShort", this.testShort)
}