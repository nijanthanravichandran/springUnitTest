# Package Structure & Course Flow:
1. Tried a simple unit test on sum application by getting data as function input

    -- src/main/java/com/learn/spring/UnitTest/BusinessLayer/TestBusinessAppT1.java
    -- src/test/java/com/learn/spring/UnitTest/SomeBusinessTestT2.java

2. Added a level of complexity by getting data from data layer, as it is the actual real world scenario
    * Added a data layer service as INTERFACE, to generate data (Realtime, it could be RDBMS system data)
    * In the unit test, it is not adivisable to get data from backend data system itseems (Say RDBMS as per our example), hence created a STUB class to redirect the data layer call to dumy function.
    * STUB class extends the data layer interface and we override methods as per each test cases
    * but using STUB is not a efficient of way unit testing, as we need to create separate method of each cases, also if there is any change in interface leads to change the complete implementation of all STUB classes as it extends from it. 
    * To overcome this problem, mockito comes into the picture, lets explore that next

    -- src/main/java/com/learn/spring/UnitTest/BusinessLayer/SomeBusinessImplT2.java
    -- src/test/java/com/learn/spring/UnitTest/SomeBusinessTestT2.java

3. Mockito "org.mockito.Mockito.mock" made the mocking job very easy for us. We just need to create a mock object on top of the class we need to mock
    -- SomeDataService dataServiceMock = mock(SomeDataService.class);
    
    * Using this mock object we can define if a particular method from the mockobject (class its created from) is called, what should be retruned as value
    -- when(dataServiceMock.retriveAllData()).thenReturn(new int[] {1,2,3});

    * Here, when retirveAllData() method is called, itll return the array {1,2,3}. So just a single line of code helped us to mock the data as per our unit test

    -- src/test/java/com/learn/spring/UnitTest/SomeBusinessTestMockT3.java

4. Cleaned & organized the code better
    * read the comments in the code

    -- src/test/java/com/learn/spring/UnitTest/SomeBusinessTestMockCleanT3.java

5. Inject mocks using Mock Annotations

    -- src/test/java/com/learn/spring/UnitTest/SomeBusinessTestMockAnnotationsT3_3.java

6. Mockito tips & tricks
    * Multiple return values & special arguments
    * Arguments matcher
    * Verify method calls
    * Verify arguments to method calls
    * Verify list of arguments passed to method via multiple calls
    * Mocking & Spying

7. Mokito MockMVC framework to test controller
    * Mockito provides a MOckMVC framework which can do web request on our behalf, recives the response and validate it against the actual value.
    * We need to generate request object for mockMvc Framework
        * Request object can be created using RequestBuilder module which requires:
            * URL to hit
            * Method type - GET/POST/DELETE etc..
            * Accepted Method body - JSON
    
    -- src/main/java/com/learn/spring/UnitTest/Controller/HelloWorldController.java
    -- src/test/java/com/learn/spring/UnitTest/Controller/HelloWorldControllerTest.java

8. MockMVC to test return value from a model
    * Created a dummy model item with 4 members
    * Created a controller which can return the objects of Item whem we hit the URL "/hello-item"
    * Createa a unit test to validate the controller using mockMVC
        * MockMVC can also validate the expected response code for the request made
        * It can also accept different data format for data validation, we know item controller returns the object in JSON format, we can use content type as JSON in mockMVC to validate it.

    -- src/main/java/com/learn/spring/UnitTest/Controller/ItemController.java
    -- src/test/java/com/learn/spring/UnitTest/Controller/ItemControllerTest.java
    
    ![unittest_request_post](https://user-images.githubusercontent.com/108142931/195043723-621cd26a-ef93-4b4f-aa8d-abc8c95e8fc0.png)

9. MockMVS to test method in service layer
    * Create a service called ItemBusinessService in service layer with retriveHardCoded method to return an item object.
    * Created a controller to hit the method in business layer and retrun object
    * In order to unit test this, first we need to create a mock for the service layer using @MockBean, this will create a dummy mock bean for IteamBusinessService
    * Then using mocktio's "when" to set dummy response for the call to "retriveHardCoded" method
    * Then the request & response is generated & validated by mockMvc 

    -- src/main/java/com/learn/spring/UnitTest/ServiceLayer/ItemBusinessService.java
    -- src/main/java/com/learn/spring/UnitTest/Controller/ItemController.java
    -- src/test/java/com/learn/spring/UnitTest/Controller/ItemControllerTest.java


10. Now added data layer to the project
    * Created a entity ItemJPA , used MYSQL database to store the data
    -- src/main/java/com/learn/spring/UnitTest/Entity/ItemJPA.java

    * Added a controller to retrive all data from RDBMS and return it
    -- src/main/java/com/learn/spring/UnitTest/Controller/ItemController.java

    * Plan to test
        * First test controller layer where controller retruns collection of items from JPA to frontend
        * Next test ServiceLayer, where JPA repository fetched data from RDBMS ,executing some business logic and retruning list of items
        * finally test datalayer

    * Controller layer unit test
    -- src/test/java/com/learn/spring/UnitTest/Controller/ItemControllerTest.java

    * Business layer unit test
    -- src/test/java/com/learn/spring/UnitTest/ServiceLayer/ItemBusinessServiceTest.java

    * Data Layer Unit test
        * For data layer test, spring provides a annotation called @DataJpaTest, By default , it tries to look for inmemory default database for executing test case, we need to override that behaviour by adding another annotation @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    -- src/test/java/com/learn/spring/UnitTest/ServiceLayer/ItemJPALayerTest.java

11. Integration testing
    * Integration testing is to basically test the integration between web, business and data layer. Unit test to make sure all 
    three layers are insync to generate expected outcome

    -- src/test/java/com/learn/spring/UnitTest/Controller/IntegrationTest.java

    * Springboot provides an option to create a web environment for mocking web layer , it could start on any random port
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

    * Module "TestRestTemplate" is used to make rest call on the mock web layer created by spring boot
    @Autowired
    TestRestTemplate testRestTemplate;
    This will act like postman, using which we post request to endpoint and get response back from it

    * Using jsonAssert we can validate the response from the weblayer

    * This test ensured my weblayer is taking to business and data layer to get me the expected response.


# course github reference : 

https://github.com/in28minutes/spring-unit-testing-with-junit-and-mockito

JUnit 5 Changes: 

https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/junit5.md

TroubleshootingGuide:

https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/quick-start.md
