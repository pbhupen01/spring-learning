
# Spring MVC

## Web Jars
```
Add bootstrap and jquery jars from
https://www.webjars.org/

Include jquery and css in templates.
```

## MVC
```
Create controllers with Model parameter
Set attribute in the models
Return the attribute key

Create template in resources/templates folder of name  "attrbute.html"
```


## Exception Hanlding

#### @ResponseStatus
Define Custom Exception with @ResponseStatue(HTTPStatus.XXXXX).
Spring will send specified HTTP error code whenever custom exception is thrown.

```
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

```
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

```
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