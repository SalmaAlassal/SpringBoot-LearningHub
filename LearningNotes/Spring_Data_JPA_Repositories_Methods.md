## Saving Entities

- `save()` method is dual purpose method. It can be used to save new entity or update existing entity.

- There are different mechanisms used by Spring to decide if it must use `persist()` (for new entity) or `merge()` (for existing entity) method of `EntityManager`.

### Entity State Detection Strategies

- **@Id-Property inspection (the default)**: By default, Spring Data inspects the identifier property of the given entity. If the identifier property is null or 0 in case of primitive types, then the entity is assumed to be new. Otherwise, it is assumed to not be new.

- **@Version-Property inspection**: If a property annotated with `@Version` is present and null, or in case of a version property of primitive type 0 the entity is considered new. If the version property is present but has a different value, the entity is considered to not be new. If no version property is present Spring Data falls back to inspection of the identifier property.
    > If the entity has a version property, Spring Data JPA will use the version property to determine if the entity is new or not even if the entity has an identifier property.

- **Implementing Persistable**: If the entity implements the `Persistable` interface, the `isNew()` method is used to determine if the entity is new or not.

- **Providing a custom EntityInformation implementation**: If none of the above strategies apply, you can provide a custom `EntityInformation` implementation that determines the state of the entity.

----------------------------------------------------------------

## Different Query Types in Spring Data Repositories

You can define queries in Spring Data JPA repositories using the following methods:

- **Derived Query Methods**: It allows you to create queries based on method names. 
    ```java
    List<Student> findByCoursesId(Long courseId);
    ```
    > [Derived Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)

- **JPQL Queries**: It allows you to write queries using entity classes and fields. 
    ```java
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
    ```

- **Native Queries**: It permits the execution of SQL queries directly.
    ```java
    @Query(value = "SELECT * FROM student_course WHERE course_id = :courseId", nativeQuery = true)
    List<Map<String, Object>> findStudentCourseMappings(@Param("courseId") Long courseId);
    ```
