package edu.miu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.miu.custom.exception.EmailValidationException;
import edu.miu.custom.exception.EmptyFieldException;
import edu.miu.custom.exception.PasswordValidationException;
import edu.miu.dto.UserRequest;
import edu.miu.dto.UserResponse;
import edu.miu.model.User;
import edu.miu.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	private String usernameError = "username is mandatory";
	private String passwordError = "must match \"((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@%#$]).{6,12})\"";
	private String emailError = "Please enter a correst email format";

	@PostMapping()
	public ResponseEntity<String> addUser(@RequestBody @Valid UserRequest userRequest, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.userRequest", attr);
			attr.addFlashAttribute("userRequest", userRequest);

			for (ObjectError err : result.getAllErrors()) {
				System.out.println(err.getDefaultMessage());
				if (err.getDefaultMessage().equals(usernameError))
					throw new EmptyFieldException("", HttpStatus.BAD_REQUEST);
				else if (err.getDefaultMessage().equals(passwordError)) {
					System.out.println("inside password error!");
					throw new PasswordValidationException("", HttpStatus.BAD_REQUEST);
				}else if (err.getDefaultMessage().equals(emailError))
					throw new EmailValidationException("", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Something went wrong!", HttpStatus.CREATED);
		} else {
			String response = userService.addUser(userRequest);
			return new ResponseEntity<String>(response, HttpStatus.CREATED);
		}
	}

	@PutMapping
	public ResponseEntity<String> editUser(@RequestBody @Valid User user, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.userRequest", attr);
			attr.addFlashAttribute("userRequest", user);

			for (ObjectError err : result.getAllErrors()) {
				System.out.println(err.getDefaultMessage());
				if (err.getDefaultMessage().equals(usernameError))
					throw new EmptyFieldException("", HttpStatus.BAD_REQUEST);
				else if (err.getDefaultMessage().equals(passwordError))
					throw new PasswordValidationException("", HttpStatus.BAD_REQUEST);
				else if (err.getDefaultMessage().equals(emailError))
					throw new EmailValidationException("", HttpStatus.BAD_REQUEST);
			}
		} else {
			User updatedUser = userService.updateUser(user);
			return new ResponseEntity<String>("User with username <" + updatedUser.getUsername() + "> successfully updated", HttpStatus.OK);
		}
		return null;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
		UserResponse user = userService.findUserById(userId);

		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping()
	public ResponseEntity<List<UserResponse>> getAll() {
		List<UserResponse> users = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.FOUND).body(users);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		String result = userService.deleteUserById(userId);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
