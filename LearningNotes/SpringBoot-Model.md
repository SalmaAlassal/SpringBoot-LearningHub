# Spring Boot Model

## MVC

**MVC (Model-View-Controller)** is a software architecture pattern, which separates application into three parts: model, view, and controller. The model represents a Java object carrying data. The view visualizes the data that the model contains. The controller manages the data flow into model object and updates the view whenever data changes; it keeps view and model separate.

## Spring MVC

Spring MVC is the original web framework built on the Servlet API. It is build on the MVC design pattern. 

### View

The **View** in Spring is responsible for rendering the HTML or other output that is presented to the user. It defines how the data from the model is displayed.

### Model

In Spring, the **Model** represents the data or attributes that you want to pass from the controller to the view. It's essentially a way to make data available to the view layer.

When using just a **Model**, you typically add attributes (key-value pairs) to it using `model.addAttribute("key", value)`. This represents the data part.

### ViewResolver

The **ViewResolver** is responsible for mapping the view names to actual views (templates).

--------------------------------------------

# Model, ModelMap, ModelAndView

- Model, ModelMap, and ModelAndView are used to define a model in a Spring MVC application.

- Model defines a holder for model attributes and is primarily designed for adding attributes to the model.

- ModelMap is an extension of Model with the ability to store attributes in a map and chain method calls. 

- ModelAndView is a holder for a model and a view; it allows to return both model and view in one return value.


## ModelAndView

- ModelAndView is a holder for both Model and View in the web MVC framework. These two classes are distinct; ModelAndView merely holds both to make it possible for a controller to return both model and view in a single return value.

- The view is resolved by a ViewResolver object; the model is data stored in a Map.

```java
@Controller
public class MyController {
    @GetMapping("/example")
    public ModelAndView example() {
        // view name is a template file
        ModelAndView modelAndView = new ModelAndView("exampleView");
        
        // The model is a Map object.
        // Key : attributeName, Value : value
        // attributeName is the variable name that you can use in the template file and value is the value that you want to assign to that variable.
        modelAndView.addObject("attributeName", "value");
        
        return modelAndView;
    }
}
```

## Model

- Instead of bundling the model data and view name together, it allows you to add model attributes and return a view name as a separate part of the method return value.

- You return the view name as a String, and Spring automatically associates it with a view.

```java
@Controller
public class MyController {
    @GetMapping("/example")
    // Spring will automatically create a model object and pass it to the function.
    public String example(Model model) {
        // This is adding to the view model which will be specified in the return statement.
        model.addAttribute("attributeName", "value");

        // The view name is a template file.
        return "exampleView";
    }
}
```

---------------------------------------------------