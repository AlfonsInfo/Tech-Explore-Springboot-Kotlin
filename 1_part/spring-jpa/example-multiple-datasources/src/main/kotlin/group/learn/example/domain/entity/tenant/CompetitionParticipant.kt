package group.learn.example.domain.entity.tenant

import group.learn.example.domain.entity.BaseEntity
import jakarta.persistence.*
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*


@Entity
@Table(name = "competition_participant")
@EnableTransactionManagement
data class CompetitionParticipant (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var name: String? = null,

    var score : Int? = null
) : BaseEntity()