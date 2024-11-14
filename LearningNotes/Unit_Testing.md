# JUnit Testing

## Unit Testing

- Unit testing is a software testing technique where individual units or components of a software application are tested in isolation to verify that they perform as expected.

## Spring Boot Starter Test Dependency

- The Spring Boot Starter Test dependency provides essential libraries and utilities for testing Spring Boot applications, including **JUnit**, **Hamcrest**, **Mockito**, and **AssertJ**. 
- It simplifies the process of writing and executing tests for Spring Boot applications.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## JUnit

- JUnit is a popular open-source testing framework for Java. It provides annotations to define test methods, test classes, and test suites, along with assertions for verifying expected outcomes. 
- JUnit makes it easy to write and execute unit tests, ensuring the correctness of Java code.

## AssertJ

- AssertJ is a popular Java library that provides fluent assertions for writing clearer and more readable unit tests. And also It provides helpful error messages.

## Mockito

- Mockito is a popular Java mocking framework that allows you to create mock objects for testing. 
- Mockito can be used in unit tests to mock dependencies and isolate the code being tested.
    - Controllers often depend on service layer components to perform business logic or interact with the application’s data layer (e.g., repositories). Mock the service layer components using Mockito to isolate the controller being tested from the actual service layer implementation.

## MockMvc

- MockMvc is not part of Mockito.
- MockMvc is a class provided by the Spring Test framework specifically for testing Spring MVC controllers.

## Test Annotations

### `@DataJpaTest`

- It's used to load only the JPA components without loading the entire Spring ApplicationContext (e.g., controllers, services).
- It sets up a lightweight environment for testing JPA repositories.

## `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`

- Specify the order of test execution based on the order specified by the` @Order` annotation.

### `@Test`

- It's used to mark a method as a test method.

### `@DisplayName`

- It's used to define a custom display name for a test class or method.

### `@Order`

- It's used to define the execution order of test methods within a test class.

### `@Rollback`

- It's used to specify whether a transaction should be rolled back after a test method has been executed.
- By default, the transaction is rolled back after each test method `@Rollback(true)`. The transaction will be rolled back after the test method has been executed, ensuring that the changes made during the test method do not affect the database.
- If you specify `@Rollback(false)`, the transaction will not be rolled back after the test method has been executed and the changes made during the test method will be committed to the database.
    - You can use H2 in-memory database for testing to avoid affecting the production database.

### `@BeforeEach`

- It's used to indicate that a method should be executed before each test method in a test class.
- It's used to set up the common test data or resources required by multiple test methods.

### `@WebMvcTest`

- It's used to load only the web layer components (e.g., controllers) without loading the entire Spring ApplicationContext.

### `@MockBean`

- It's used to create a mock instance of a bean (e.g., service).
- It's used to isolate the controller being tested from the actual service layer implementation.

## JPA Repositories Testing

```java
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryUnitTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Test 1:Save Employee Test")
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        //Action
        Employee employee = Employee.builder()
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        employeeRepository.save(employee);

        //Verify
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){

        //Action
        Employee employee = employeeRepository.findById(1L).get();
        //Verify
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){
        //Action
        List<Employee> employees = employeeRepository.findAll();
        //Verify
        System.out.println(employees);
        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        //Action
        Employee employee = employeeRepository.findById(1L).get();
        employee.setEmail("samcurran@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);

        //Verify
        System.out.println(employeeUpdated);
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("samcurran@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        //Action
        employeeRepository.deleteById(1L);
        Optional<Employee> employeeOptional = employeeRepository.findById(1L);

        //Verify
        Assertions.assertThat(employeeOptional).isEmpty();
    }

}
```

## Contollers Testing

- `ObjectMapper` is used to convert Java objects to JSON and vice versa.
- `MockMvc` is used to perform HTTP requests and verify the response.
- `given`: This method is part of the Mockito framework. It is used to specify a precondition or setup for the test.



```java
@WebMvcTest(EmployeeController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    Employee employee;

    @BeforeEach
    public void setup(){

         employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();

    }

    //Post Controller
    @Test
    @Order(1)
    public void saveEmployeeTest() throws Exception{
        // precondition
        given(employeeService.saveEmployee(any(Employee.class))).willReturn(employee);

        // action
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // verify
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())));
    }

    //Get Controller
    @Test
    @Order(2)
    public void getEmployeeTest() throws Exception{
        // precondition
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);
        employeesList.add(Employee.builder().id(2L).firstName("Sam").lastName("Curran").email("sam@gmail.com").build());
        given(employeeService.getAllEmployees()).willReturn(employeesList);

        // action
        ResultActions response = mockMvc.perform(get("/api/employees"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(employeesList.size())));

    }

    //get by Id controller
    @Test
    @Order(3)
    public void getByIdEmployeeTest() throws Exception{
        // precondition
        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));

        // action
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employee.getId()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));

    }


    //Update employee
    @Test
    @Order(4)
    public void updateEmployeeTest() throws Exception{
        // precondition
        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));
        employee.setFirstName("Max");
        employee.setEmail("max@gmail.com");
        given(employeeService.updateEmployee(employee,employee.getId())).willReturn(employee);

        // action
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }


    // delete employee
    @Test
    public void deleteEmployeeTest() throws Exception{
        // precondition
        willDoNothing().given(employeeService).deleteEmployee(employee.getId());

        // action
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employee.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
```

---------------------

- Resource : [Spring boot Unit testing](https://medium.com/@Lakshitha_Fernando/spring-boot-unit-testing-for-repositories-controllers-and-services-using-junit-5-and-mockito-def3ff5891be)