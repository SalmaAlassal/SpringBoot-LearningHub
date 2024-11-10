## Lombok Annotations

Lombok is a library that helps reduce boilerplate code in Java applications. It provides annotations that generate code automatically so you don't have to write it yourself.

### `@Data`

- Generates `getters` for all fields, a useful `toString` method, and `hashCode` and `equals` implementations that check all non-transient fields. Will also generate `setters` for all non-final fields, as well as a `constructor`.

- It's a convenient shortcut for 
   - `@ToString`
   - `@EqualsAndHashCode`
   - `@Getter`
   - `@Setter`
   - `@RequiredArgsConstructor`

- Example:
   ```java
   @Data
   public class User {
      private String name;
      private String email;
   }
   ```

- Example of generated code after this annotation, which we don’t need to write anymore with lombok’s `@Data` annotation.

   ```java
   public class User {
      private String name;
      private String email;

      public User(String name, String email) {
         this.name = name;
         this.email = email;
      }

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public String getEmail() {
         return this.email;
      }

      public void setEmail(String email) {
         this.email = email;
      }

      @Override
      public String toString() {
         return "User(name=" + this.getName() + ", email=" + this.getEmail() + ")";
      }

      @Override
      public boolean equals(Object o) {
         ...
         ...
      }

      @Override
      public int hashCode() {
         ...
         ...
      }
   }
   ```


### `@NoArgsConstructor`

- Generates a constructor with no arguments.

```java
@NoArgsConstructor
public class User {
   private String name;
   private String email;
}
```

- Example of generated code after this annotation:

   ```java
   public class User {
      private String name;
      private String email;

      public User() {
      }
   }
   ```

### `@AllArgsConstructor`

- Generates a constructor with all arguments.

```java
@AllArgsConstructor
public class User {
   private String name;
   private String email;
}
```

- Example of generated code after this annotation:

   ```java
   public class User {
      private String name;
      private String email;

      public User(String name, String email) {
         this.name = name;
         this.email = email;
      }
   }
   ```

### `@RequiredArgsConstructor`

- Generates a constructor with all `final` fields and all fields that are marked with `@NonNull`.

```java
@RequiredArgsConstructor
public class User {
   private final String name;
   private final String email;
   private int age;
}
```

- Example of generated code after this annotation:

   ```java
   public class User {
      private final String name;
      private final String email;
      private int age;

      public User(String name, String email) {
         this.name = name;
         this.email = email;
      }
   }
   ```

### `@Getter` / `@Setter`

- **Class Level**: Generates getters/setters for all fields in the class.
    
    ```java
    @Getter
    @Setter
    public class User {
       private String name;
       private String email;
    }
    ```
- **Field Level**: Generates a getter/setter for the annotated field.

    ```java
    public class User {
       @Getter
       @Setter
       private String name;
       private String email;
    }
    ```

### `@Builder`

- Generates a builder pattern for the class.

```java
@Builder
public class User {
   private String name;
   private String email;
}
```

- We can use the builder pattern to create objects like this:

   ```java
   User user = User.builder()
                    .name("Salma")
                    .email("salma@example.com")
                    .build();
    ```

---------------------