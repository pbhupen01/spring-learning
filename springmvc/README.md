
# Spring MVC

## Web Jars
```
Add bootstrap and jquery jars from
https://www.webjars.org/

Include jquery and css in templates.
```

## MVC
1. Create controllers with Model or @ModelAttribute parameters.
2. Set attribute in models for methods with Model parameter.
3. Read values in methods for @ModelAttribute parameters.
4. Return template name

Controller request mapping method with Model. It is used to display on UI.
```java
@RequestMapping("/product/{id}/show")
public String showById(@PathVariable String id, Model model){

        model.addAttribute("product", productService.findProduct(Long.valueOf(id)));

        return "product/show";
}
```

Controller request mapping method with @ModelAttribute. It is used to read from UI.
```java
@PostMapping("product")
public String saveOrUpdate(@Valid @ModelAttribute("product") ProductCommand command, BindingResult bindingResult){
}
```

## Exception Hanlding

#### @ResponseStatus
Define Custom Exception with @ResponseStatue(HTTPStatus.XXXXX).
Spring will send specified HTTP error code whenever custom exception is thrown.

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String msg)
    {
        super(msg);
    }
}
```

#### @ExceptionHandler
Defining exception handler method in controller.
Error can be linked to a view.

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(ProductNotFoundException.class)
public ModelAndView handleNotFound(){

        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");

        return modelAndView;
}
```

#### Controller Advice
Create a single class with all the exception handler methods for all the controllers.

```java
@ControllerAdvice
public class ControllerExceptionHandle
{
}
```

#### HandlerExceptionResolver
HandlerExceptionResolver is an interface you can implement for custom exception handling

Spring MVC has 3 implementations of HandlerExceptionResolver
* ExceptionHandlerExceptionResolver - Matches uncaught exceptions to @ExceptionHandler
* ResponseStatusExceptionResolver - Looks for uncaught exceptions matching @ResponseStatus
* DefaultHandlerExceptionResolver - Converts standard Spring Exceptions to HTTP status codes (Internal to Spring MVC)

## Data validation with Spring
Java and Hibernate provide annotations to specify validators for object fields.
@Valid should be provided for fields in Controllers (method fields) along with validators in DTO, for which validation is required.

```java
@NotBlank
@Min
@Max
@Size(min = 3, max = 255)

@Email
@CreditCarNumber
@Url
```

To capture validation results use BindingResult in Controller methods.
```java
@PostMapping("product")
    public String saveOrUpdate(@Valid @ModelAttribute ProductCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors())
        {
            for(ObjectError error : bindingResult.getAllErrors())
            {
                log.debug(error.getDefaultMessage());
            }
            return PRODUCT + "/" + PRODUCT_FORM;
        }
}
```

Custom error messages can be displayed in Web page on validation failures.
Error message can be defined in message.properties file. Html pages should be modified to display error message.

```
# Set names of properties
product.description=Description

#Validaiton Messages
#Order of precedence
# 1 code.objectName.fieldName
# 2 code.fieldName
# 3 code.fieldType (Java data type)
# 4 code
NotBlank.product.description=Description Cannot Be Blank
Size.product.description={0} must be between {2} and {1} characters long.
```