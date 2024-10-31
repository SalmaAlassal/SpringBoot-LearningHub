# Maven with Spring Boot

- Maven is powerful project management tool that is based on POM (project object model). It is used for projects build, dependency and documentation.

- It is most commonly used in Java-based frameworks.

- It is a build automation tool that automate everything related to building the software project. Building software includes one or more of the following activities:
    - Generating source code
        - For example, if you are using `Lombok` in your project, maven will generate the source code for the annotations you have used in your project.
    - Generating documentation from the source code
    - Compiling source code
    - Packaging compiled code into JAR, WAR, or ZIP files
    - Installing the packaged code on a server, in a repository, or on a client

## POM (Project Object Model)

- A POM is the basement of the Maven framework. It’s a type of XML file that contains information about the project and configuration information like project dependencies, source directory, plugin, goals that can be executed, the build profiles, and so on. Other information such as the project version, description, developers, mailing lists and such can also be specified.

- It contains default values for most projects. Examples for this is the **build directory**, which is `target`; the **source directory**, which is `src/main/java`; the **test source directory**, which is `src/test/java`; and so on.

- Maven uses `pom.xml` file to build the project.

- When executing a task or goal, Maven looks for the POM in the current directory. It reads the POM, gets the needed configuration information, then executes the goal.

### Example of a simple POM file

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
 
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>3.3.5</version>
      <relativePath/>
  </parent>

  <!-- Basic project information -->
  <groupId>com.example</groupId>
  <artifactId>my-app</artifactId>
  <version>1</version>
  <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <!-- Build configuration -->
  <build>
    <!-- Add build configuration here -->
  </build>
</project>
```

> We don’t need to specify the version of the dependencies in our configuration. Spring
Boot manages itself. Spring Boot automatically uses the version of the dependencies that are compatible with the version of Spring Boot specified in the parent tag.

> All spring boot apps should inherit from `spring-boot-starter-parent` giving you access to dependencyManagement and tons of auto included libraries.

### Some important tags in POM file

- `<project>` - It is the root element of pom.xml file.
- `<modelVersion>` - It specifies the version of the POM that Maven should use to interpret the structure and content of the `pom.xml` file.. It should be set to 4.0.0 as it is currently the only supported POM version.
- `<groupId>` - the id of the project's group or organization. It follows a reverse domain name convention (e.g., "`com.example`").
- `<artifactId>` - the id of the artifact (project). It is usually a descriptive name (e.g., "`my-app`"). An artifact is something that is either produced or used by a project. Examples of artifacts include JARs, binary and source distributions, and WARs.
- `<version>` - the version of the artifact under the specified group
- `<packaging>` - The packaging type of your project. Common values are `jar`, `war`, and `pom`.
- `<dependencies>` - It contains a list of dependencies that your project needs to build. Each dependency is described by a `<dependency>` tag, which contains the `<groupId>`, `<artifactId>`, and `<version>` of the dependency.
- `<build>` - It contains configuration settings for the build process. This includes settings for plugins and other build-related settings.

### Inheriting from Parent POM

Maven supports multi-module projects, where a parent POM can be used to share common configurations and dependencies among multiple submodules. To inherit from a parent POM, add a <parent> element to your POM file:

```xml
<parent>
  <groupId>com.example</groupId>
  <artifactId>parent-project</artifactId>
  <version>1.0.0</version>
</parent>
```

## Super POM

- It is the parent of all POMs.

- **All Maven project POMs extend the Super POM,** which defines a set of defaults shared by all projects.

- This Super POM is a part of the Maven installation.

- Depending on the Maven version it can be found in the `maven-x.y.z-uber.jar` or `maven-model-builder-xy.z.jar` file in `~.m2`. If you look in this JAR file, you will find a file named `pom-4.0.0.xml` under the `org.apache.maven.model` package. I

- The main advantage of the POM hierarchy in Maven is that we can extend and override the configuration inherited from the top. Therefore, to override the configuration of a given element or an artifact in the POM hierarchy, Maven should be able to uniquely identify the corresponding artifact.

> [Super POM](https://maven.apache.org/maven-model-builder/super-pom.html)

--------------------------------------------

## Maven Repositories

- Maven repository plays a crucial role in managing project dependencies. It is a centralized location where Maven project's jars, the plug-ins, or other materials related to the project are stored here.

- When a dependency has to be searched, it initially searches the local repository, then the central repository, and at last the remote repository.


### Local Repository

- Each developer has their own local repository, which is located in their home directory (`~/.m2/repository`). When you build a project for the first time, Maven downloads all required dependencies from remote repositories and stores them in your local repository.

### Central Repository

- Apache Maven group developed the central repository, and it is hosted on the web.

- The Central Repository is the default remote repository used by Maven. It contains a vast collection of open-source libraries and frameworks that can be used in your projects. When Maven needs to download a dependency, it first checks the local repository. If the dependency is not found locally, Maven searches the Central Repository for the artifact.

### Remote Repository

- Sometimes, Maven does not find a mentioned dependency in central repository as well. It then stops the build process and output error message to console. To prevent such situation, Maven provides concept of Remote Repository, which is developer's own custom repository containing required libraries or other project jars.

- For example, if you are using a library that is not available in the Central Repository, you can add a remote repository to your POM file to download the library from a different location.
    ```xml
    <repositories>
        <repository>
            <id>my-repo</id>
            <url>http://example.com/maven-repo</url>
        </repository>
    </repositories>
    ```
--------------------------------------------

## Maven Build Lifecycle

- Maven build lifecycle is a sequence of phases that a project goes through to build the project.

- Maven has three built-in build lifecycles:
    - **default** - handles project deployment
    - **clean** - clean the project and remove all files generated by the previous build
    - **site** - handles the creation of the project's site documentation

- Each lifecycle consists of a sequence of phases. Each phase is a sequence of goals, and each goal is responsible for a specific task.

- When we run a phase, all goals bound to this phase are executed in order.


### Default Lifecycle

- The default lifecycle handles your project deployment.

- Here are some of the most important phases in the default build lifecycle:
    - `validate`:
        - Validate the project is correct and all necessary information is available.
        - For example — It checks if all the dependencies have been downloaded and are available in the local repository.
    - `compile`:
        - Compile the source code, converting the `.java` files into `.class` files and stores them in the `target/classes` directory.
    - `test`:
        - Run unit tests for the project.
    - `package`:
        - Take the compiled code and package it in its distributable format, such as a JAR or WAR.
    - `verify`:
        - Run any checks to verify that the project is valid and meets the quality standards.
    - `install`:
        - install the packaged code to the local Maven repository.
    - `deploy`:
        - Copies the final package to the remote repository for sharing with other developers.

> Maven follows a sequential order to execute the commands where if you run step n, all steps preceding it (Step 1 to n-1) are also executed. For example — if we run the Installation step (Step 7), it will validate, compile, package and verify the project along with running unit and integration tests (Step 1 to 6) before installing the built package to the local repository.

-----------------------------------------------

## How does Maven Work?

- Maven reads the `pom.xml` file.
- Maven downloads the dependencies mentioned in the `pom.xml` file from the Maven repository to the local repository.
    - All jars in the Maven Repository are indexed by `artifact id` and `group id`. When we add a dependence to our `pom.xml`, maven queries the maven repository for jar dependencies, using the `artifact id` and `group id`.
    - The jar dependencies are saved on your system in the maven local repository folder. All of our projects would make use of the jars in the maven local repository.
- Maven executes lifecycle phases, goals and plugins to build the project.

-----------------------------------------------