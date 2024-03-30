package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResGetMotorDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import group.learn.springboot.domain.entity.MotorEntity
import group.learn.springboot.domain.repository.MotorRepository
import group.learn.springboot.exception.DataNotFoundException
import group.learn.springboot.service.MotorService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

private const val ERROR = "Motor tidak terdaftar"

@Service
class MotorServiceImpl (
    val motorRepository: MotorRepository
) : MotorService {
    override fun insert(request: ReqUpsertDto): ResMessageDto<String> {
            val dataForInsert = MotorEntity(
                name =  request.name,
                merk = request.merk
            )
            motorRepository.save(dataForInsert)

        return  ResMessageDto()
    }

    override fun update(id : UUID , request: ReqUpsertDto): ResMessageDto<String> {

        val dataForUpdate = motorRepository.findById(id)
        if(dataForUpdate.isEmpty){
            throw DataNotFoundException( message = ERROR)
        }
        dataForUpdate.get().name = request.name
        dataForUpdate.get().merk = request.merk
        motorRepository.save(dataForUpdate.get())
        return ResMessageDto()
    }

    override fun detail(id: UUID): ResMessageDto<ResGetMotorDto> {
        val getData = motorRepository.findById(id)
        if(getData.isEmpty){
            throw DataNotFoundException(ERROR)
        }
        val data  = ResGetMotorDto(
            id = getData.get().id!!,
            name = getData.get().name!!,
            merk = getData.get().merk!!
        )
        return ResMessageDto(data = data)
    }

    override fun list(): ResMessageDto<List<ResGetMotorDto>> {
        val allData = motorRepository.findAll()
        val resData= arrayListOf<ResGetMotorDto>()
        for(motor in allData){
            val data = ResGetMotorDto(
                id = motor.id!!,
                name = motor.name!!,
                merk = motor.merk!!
            )
            resData.add(data)
        }
        return ResMessageDto(data = resData)
    }

    override fun delete(id: UUID): ResMessageDto<String> {
        //val data = motorRepository.findById(id)
        if(!motorRepository.existsById(id)){
            throw DataNotFoundException(ERROR)
        }
        motorRepository.deleteById(id)
        return ResMessageDto()
    }
}