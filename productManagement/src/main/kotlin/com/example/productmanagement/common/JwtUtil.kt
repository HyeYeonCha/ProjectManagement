import com.example.productmanagement.user.persistence.JpaUserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil {
    private val SECRET_KEY = "test1234" // 환경변수필요

    private fun getSigningKey(): Key {
        val keyBytes = SECRET_KEY.toByteArray()
        return SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.jcaName)
    }

    fun extractUserId(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun generateToken(userId: Long): String {
        val claims = HashMap<String, Any>()
        return createToken(claims, userId.toString())
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 유효
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String, user: JpaUserRepository.User): Boolean {
        val userId = extractUserId(token).toLong()
        return (userId == user.id && !isTokenExpired(token))
    }
}
