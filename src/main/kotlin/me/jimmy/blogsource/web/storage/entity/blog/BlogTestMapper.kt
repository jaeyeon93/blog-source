package me.jimmy.blogsource.web.storage.entity.blog

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
internal class BlogTestMapper: RowMapper<BlogTestEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int) = BlogTestEntity(
        id = rs.getLong("id"),
        testString = rs.getString("test_string"),
        testBoolean = rs.getBoolean("test_boolean"),
        testInt = rs.getInt("test_int"),
        testLong = rs.getLong("test_long"),
        testBigDecimal = rs.getBigDecimal("test_big_decimal"),
        testDouble = rs.getDouble("test_double"),
        testFloat = rs.getFloat("test_float"),
        testByte = rs.getByte("test_byte"),
        testShort = rs.getShort("test_short")
    )
}