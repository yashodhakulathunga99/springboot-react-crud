package com.simplecrud.crud.controller;
import com.simplecrud.crud.exception.UserNotFoundException;
import com.simplecrud.crud.model.User;
import com.simplecrud.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController //@RestController: This annotation indicates that this class serves as a REST controller. It tells Spring that the class will handle incoming HTTP requests and produce HTTP responses, typically in JSON or XML format.
@CrossOrigin("http://localhost:3000")

//connect frontend with the backend
public class UserController {



    @Autowired //@Autowired: This annotation tells Spring to automatically inject an instance of the UserRepository interface into this class. The UserRepository is typically an interface that extends Spring Data's JpaRepository or similar repository interfaces, allowing access to database operations for the User entity.
 private UserRepository userRepository;//This declares a private field named userRepository, which is an instance of the UserRepository interface. This field will be automatically initialized by Spring through dependency injection.

   @PostMapping("/user")//This annotation indicates that the following method will handle HTTP POST requests to the "/user" endpoint. When a POST request is sent to this endpoint, this method will be invoked.

   //User newUser(@RequestBody User newUser) {: This declares a method named newUser that accepts a User object as the request body (@RequestBody). When a POST request is made to the "/user" endpoint with a JSON representation of a User object in the request body, Spring will automatically deserialize the request body into a User object and pass it as an argument to this method.

    User newUser(@RequestBody User newUser){

       //This statement saves the new User object received in the request body to the database using the userRepository. It typically involves persisting the User entity to the database using Spring Data JPA's save method.
       return userRepository.save(newUser);
   }

   @GetMapping("/users") // specifically tells Spring that this method should handle HTTP GET requests to the specified endpoint ("/users").
    List<User> getAllUsers(){
       return userRepository.findAll();
   }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
       return userRepository.findById(id)
               .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
       return userRepository.findById(id)
               .map(user -> {
                   user.setUsername(newUser.getUsername());
                   user.setName(newUser.getName());
                   user.setEmail(newUser.getEmail());
                   return userRepository.save(user);
                       }).orElseThrow(()->new UserNotFoundException(id));

    }

  @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
       if(!userRepository.existsById(id)){
           throw new UserNotFoundException(id);
       }
     userRepository.deleteById(id);
       return "User with id" +id+ "has been deleted success";
  }

    @GetMapping("/users/search")
    List<User> searchUsers(@RequestParam("keyword") String keyword) {
        return userRepository.searchUsers(keyword);
    }
}


