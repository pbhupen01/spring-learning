
# Example
```
House
Person
Builder
Many to many relationship between Person and House
One to one relationship between Person and House
House is main class
Person is dependent class
Builder is dependent class
```

### Many to Many
1. Define fields in tables to specify mapping.
   - Define persons field in House.
   - Define houses field in Person.

2. Simply specifying @ManyToMany will create two join (mapping) tables for the both tables. These mapping tables will have primary keys of both tables.
className_fieldName

   House Class. Following code creates house_persons table.
```java
public class House{
@ManyToMany
Set<Person> persons
}
```

   Person class. Following code creates create person_houses table.
```java
public class Person{
@ManyToMany
Set<House> houses
}
```

3. But there should be only one join table. To avoid creation of two mapping table use mappedBy in the **dependent table**. Provide field name of the main table to which it is mapped to.
```
Define in Person class.
skip creating person_houses table
only house_persons is created due to main table mapping
```

```java
public class Person{
@ManyToMany(mappedBy = "persons")
Set<House> houses
}
```

4. You can provide custom name for join (mapping) table and fields in join table used for mapping in the **main table**.
   
   Define in House class.

```java
@JoinTable(name = "house_person", joinColumns = @JoinColumn(name = "house_id"),
inverseJoinColumns = @JoinColumn(name = "person_id"))
```
   
### One to One
1. Adding One to one relationship. Add dependent class in the main class with @OneToOne annotation. A new column with dependent class id is created in main class table.
   
   Add in House class
```java
@OneToOne
private Publisher builder;
```

