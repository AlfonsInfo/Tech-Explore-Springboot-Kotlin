package group.learn.querymethod.common.seeder

import com.github.javafaker.Faker
import group.learn.querymethod.domain.entity.EmployeeEntity2
import group.learn.querymethod.repository.DepartmentRepository
import group.learn.querymethod.repository.EmployeeRepository
import group.learn.querymethod.repository.EmployeeRepository2
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class SeederService(
    private val departmentRepository: DepartmentRepository,
    private val employeeRepository2: EmployeeRepository2
) {
    fun seedEmployee(){
        val departmentSort = Sort.by(Sort.Direction.ASC, "name")
        val departmentList = departmentRepository.findAllByFlagActiveAndFlagDeleted(sort = departmentSort)
        val faker = Faker()
        val listEmployee2 = mutableListOf<EmployeeEntity2>()
        for( i in 1..10000){
            val randomPickDepartment = departmentList.random()
            val employeeEntity2 = EmployeeEntity2()
            employeeEntity2.name = faker.name().fullName()
            employeeEntity2.department = randomPickDepartment
            employeeEntity2.createdBy = "alfons"
            employeeEntity2.updatedBy = "alfons"
            listEmployee2.add(employeeEntity2)
        }
        employeeRepository2.saveAll(listEmployee2)
    }
    @PostConstruct
    fun seedDatabase(){
//        employeeRepository2.deleteAll()
//        seedEmployee()
    }

}