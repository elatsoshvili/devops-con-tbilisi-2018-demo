package ge.devopsgeorgia.demo.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ge.devopsgeorgia.demo.exception.ResourceNotFoundException;
import ge.devopsgeorgia.demo.model.User;
import ge.devopsgeorgia.demo.persistence.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
	
	private final UserRepository repository;

	@Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }
	
	@GetMapping
    public List<User> listUsers() {
    	return repository.findAll();
    }
	
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
		return repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
    
 
    
    @PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
		
    	User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setMobile(userDetails.getMobile());
        
        // TODO add other fields
        
        return repository.save(user);
	}
    
	@PostMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public User register(@Valid @RequestBody User user, Errors errors) {
		generateErrorMessage(errors);
		String password = user.getPassword(); // TODO hash
		user.setPassword(password);
		User savedUser = repository.save(user);

		// TODO send activation email
		System.out.println("Sending mail: " + savedUser.getId());
		return savedUser;
	}
}
