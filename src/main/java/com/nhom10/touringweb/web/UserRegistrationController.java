package com.nhom10.touringweb.web;


import com.nhom10.touringweb.model.user.User;
import com.nhom10.touringweb.repository.UserRepository;
import com.nhom10.touringweb.service.UserService;
import com.nhom10.touringweb.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;
	@Autowired
	UserRepository userRepository;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		User user =userRepository.findByEmail(registrationDto.getEmail());
		if(user!=null) {
			return "redirect:/registration";
		}else {
			userService.save(registrationDto);
		}
		return "redirect:/";
	}


}
