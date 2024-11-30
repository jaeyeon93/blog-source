package me.jimmy.blogsource.web.storage.entity.blog

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "blog_test")
data class BlogTestEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val testString: String?,
    val testBoolean: Boolean?,
    val testInt: Int?,
    val testLong: Long?,
    val testBigDecimal: BigDecimal?,
    val testDouble: Double?,
    val testFloat: Float?,

    // etc type
    val testByte: Byte?,
    val testShort: Short?,

)