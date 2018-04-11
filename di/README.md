
# Dependency Injection
```
There are three ways of dependency injection:
Constructor
Property
Setter method
```
**Note**

Classes in which these annotations are used should be Component/Service/Controller

Beans for which these annotations are used should also be Component/Service/Controller

### Using Autowired
Define autowiring for Constructor, property and setter method
Autowired annotation also accepts a field named "required".
If it is set to true then Spring throws error if Autowired bean is set

##### Single implementation

```
If there is only one implementation of interface.
Simply using Autowired for contructor, property and setter method will inject bean by type at runtime.
```

##### Multiple implementation with Qualifier
```
If there are multiple implementation then provide Qualifier("beanName") in Constructor, property, and setter method will inject bean at run time.

@Autowired
@Qualifier("setterSampleServiceImpl")
public void setSampleService(SampleService sampleService)
{
   this.sampleService = sampleService;
}

You can also define qualifier for implementation class and use that defined name to inject bean at run time.

@Qualifier("constructorImpl")
public class ConstructorSampleServiceImpl implements SampleService

```

##### Multiple implementation with bean name
```
If there are multiple implementation then specifying parameter/property name as bean name in Constructor, property, and setter method will inject bean at run time.
Provided only one injection is not specified.

@Autowired
public void setSampleService(SampleService setterSampleServiceImpl)
{
   this.sampleService = setterSampleServiceImpl;
}
```

### Using Inject
Inject is java annotation which is same as Autowired annotation.
Only difference is that it doesn't contain required field.

### Using Primary Bean
If there are more than one qualifying bean in spring context we can specify one bean as primary bean.

```
Specify one of the qualifying bean as primary by @Primary annotation

Only one Primary bean is allowed
```
