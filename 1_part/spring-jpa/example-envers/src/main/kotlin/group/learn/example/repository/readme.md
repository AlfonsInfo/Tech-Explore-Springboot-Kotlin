# list study case 
1. findDistinct
2. IgnoreCase
3. OrderBy
4. Containing
5. asynchronous query
6. findFirst10
7. findTop10
8. IsStartingWith
9. QueryRewriter?
10. Modifying
11. Projections -> ambil nama aja, atau ambil nomor telepon aja
12. Query By Example


findBy - List<Employee> findByLastName(String lastName);
findAllBy - List<Employee> findAllByDepartment(Department department);
findOneBy - Optional<Employee> findOneByEmail(String email);
existsBy - boolean existsByEmail(String email);
countBy - long countByDepartment(Department department);
deleteBy - void deleteByLastName(String lastName);
findBy...And... - List<Employee> findByLastNameAndFirstName(String lastName, String firstName);
findBy...Or... - List<Employee> findByLastNameOrFirstName(String lastName, String firstName);
findBy...Like - List<Employee> findByLastNameLike(String lastNamePattern);
findBy...StartingWith - List<Employee> findByLastNameStartingWith(String prefix);
findBy...EndingWith - List<Employee> findByLastNameEndingWith(String suffix);
findBy...Containing - List<Employee> findByLastNameContaining(String substring);
findBy...Between - List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary);
findBy...IsNull - List<Employee> findByManagerIsNull();
findBy...IsNotNull - List<Employee> findByManagerIsNotNull();
findBy...OrderBy...Asc/Desc - List<Employee> findByDepartmentOrderByLastNameAsc(Department department);
@Query - @Query("SELECT e FROM Employee e WHERE e.lastName = :lastName") List<Employee> findEmployeesByLastName(@Param("lastName") String lastName);
@Modifying - @Modifying @Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id") int updateEmployeeSalary(@Param("id") Long id, @Param("salary") BigDecimal salary);