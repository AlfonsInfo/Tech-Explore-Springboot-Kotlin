package group.learn.springboot.domain.entity

import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "mst_user")
data class UserEntity  (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_user")
    var id : UUID? = null,

    @Column(name="user_name", unique = true)
    var username : String ? = null,

    @Column(name="email", unique = true)
    var email : String ? = null,

    @Column(name="password")
    var password : String ? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    var type: UserTypeEntity? = null

)