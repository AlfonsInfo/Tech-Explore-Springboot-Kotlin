package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResGetMotorDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.entity.MotorEntity
import group.learn.springboot.domain.repository.MotorRepository
import group.learn.springboot.exception.DataNotFoundException
import group.learn.springboot.service.MotorService
import org.springframework.stereotype.Service
import java.util.UUID

private const val ERROR = "Motor tidak terdaftar"

@Service
class MotorServiceImpl (
    val motorRepository: MotorRepository
) : MotorService {
    override fun insert(request: ReqUpsertDto): BaseResponse<String> {
            val dataForInsert = MotorEntity(
                name =  request.name,
                merk = request.merk
            )
            motorRepository.save(dataForInsert)

        return  BaseResponse()
    }

    override fun update(id : UUID , request: ReqUpsertDto): BaseResponse<String> {

        val dataForUpdate = motorRepository.findById(id)
        if(dataForUpdate.isEmpty){
            throw DataNotFoundException( message = ERROR)
        }
        dataForUpdate.get().name = request.name
        dataForUpdate.get().merk = request.merk
        motorRepository.save(dataForUpdate.get())
        return BaseResponse()
    }

    override fun detail(id: UUID): BaseResponse<ResGetMotorDto> {
        val getData = motorRepository.findById(id)
        if(getData.isEmpty){
            throw DataNotFoundException(ERROR)
        }
        val data  = ResGetMotorDto(
            id = getData.get().id!!,
            name = getData.get().name!!,
            merk = getData.get().merk!!
        )
        return BaseResponse(data = data)
    }

    override fun list(): BaseResponse<List<ResGetMotorDto>> {
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
        return BaseResponse(data = resData)
    }

    override fun delete(id: UUID): BaseResponse<String> {
        //val data = motorRepository.findById(id)
        if(!motorRepository.existsById(id)){
            throw DataNotFoundException(ERROR)
        }
        motorRepository.deleteById(id)
        return BaseResponse()
    }
}