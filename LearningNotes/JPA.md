# Spring Data JPA


## Database Relationships


## Different Cascade Types in Hibernate


### What is Cascading?

Entity relationships often depend on the existence of another entity, for example the Person–Address relationship. Without the Person, the Address entity doesn’t have any meaning of its own. When we delete the Person entity, our Address entity should also get deleted.

Cascading is the way to achieve this. When we perform some action on the target entity, the same action will be applied to the associated entity.

### Cascade Types

There are 6 types of cascade types in JPA. They are as follows:

- [CascadeType.ALL](#cascadetypeall)
- [CascadeType.PERSIST](#cascadetypepersist)
- [CascadeType.REMOVE](#cascadetyperemove)
- CascadeType.MERGE
- CascadeType.REFRESH
- CascadeType.DETACH


#### CascadeType.ALL

This cascade type specifies that all state transitions (create, update, delete, and refresh) should be cascaded from the parent entity to the child entities.

E.g. If you have a customer entity with a one-to-many relationship to Order entity then all operations on the Customer entity should be cascaded to the Order entity. This means that when a Customer entity is saved, updated, or deleted, all of its associated Order entities will also be saved, updated, or deleted accordingly.

```java
@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
private Set<Order> orders;
```

#### CascadeType.PERSIST

This cascade type specifies that the persist operation should be cascaded to the child entities. This means that whenever a persist operation is executed on the parent entity, the persist operation is also cascaded to the child entities. However, updates or deletions made to the parent entity will not be cascaded to the child entities.

E.g. If you have a customer entity with a one-to-many relationship to Order entity then the persist operation on the Customer entity will be cascaded to the Order entity automatically. However, updates or deletions made to the Customer entity will not be cascaded to the associated Order entities.


```java
@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
private Set<Order> orders;
```

#### CascadeType.REMOVE

This cascade type specifies that the remove operation should be cascaded to the child entities. This means that whenever a remove operation is executed on the parent entity, the remove operation is also cascaded to the child entities. However, updates made to the parent entity will not be cascaded to the child entities.

```java
@OneToMany(mappedBy="customer", cascade=CascadeType.REMOVE)
private Set<Order> orders;
```

--------------------------------------------