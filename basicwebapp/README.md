
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
   ```
   House class
   @ManyToMany
   persons
   creates house_persons
   ```
   ```
   Person class
   @ManyToMany
   houses
   create person_houses
   ```
3. But there should be only one join table. To avoid creation of two mapping table use mappedBy in the **dependent table**. Provide field name of the main table to which it is mapped to.
   ```
   Define in Person class.
   @ManyToMany(mappedBy = "persons")
   houses
   skip creating person_houses table
   only house_persons is created due to main table mapping
   ```
4. You can provide custom name for join (mapping) table and fields in join table used for mapping in the **main table**.
   ```
   Define in House class.
   @JoinTable(name = "house_person", joinColumns = @JoinColumn(name = "house_id"),
    inverseJoinColumns = @JoinColumn(name = "person_id"))
   persons
   ```
   
### One to One
1. Adding One to one relationship. Add dependent class in the main class with @OneToOne annotation. A new column with dependent class id is created in main class table.
   ```
   Add in House class
   @OneToOne
   private Publisher builder;
   ```

