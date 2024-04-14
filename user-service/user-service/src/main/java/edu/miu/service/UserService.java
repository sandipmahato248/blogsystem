package edu.miu.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import edu.miu.dto.UserRequest;
import edu.miu.dto.UserResponse;
import edu.miu.model.User;
import edu.miu.repository.UserRepository;
import edu.miu.response.CustomResponseMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public String addUser(UserRequest userRequest) {

//		if (userRequest.getUsername().isEmpty() || userRequest.getPassword().isEmpty()
//				|| userRequest.getEmail().isEmpty()) {
//			throw new EmptyFieldException("", HttpStatus.BAD_REQUEST);
//		}

//		CharSequence chars = "!@#$%";
//		if (userRequest.getPassword().length() < 5 || userRequest.getPassword().length() > 11
//				|| !userRequest.getPassword().contains(chars) 
//				|| userRequest.getPassword().matches("(?=.*[0-9])")) {
//			throw new PasswordValidationException("", HttpStatus.BAD_REQUEST);
//		}
		User user = buildUser(userRequest);
		userRepository.save(user);
		CustomResponseMessage responseMessage = new CustomResponseMessage(
				"User with username '" + user.getUsername() + "' successfully added!");

		return responseMessage.getResponse();
	}

	public UserResponse findUserById(Long userId) {
		User foundUser = userRepository.findById(userId).get();
		UserResponse userResponse = new UserResponse();

		if (foundUser != null) {
			BeanUtils.copyProperties(foundUser, userResponse);
			return userResponse;
		} else
			throw new NoSuchElementException();
	}

	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapToUserResponse).toList();

	}

	public User updateUser(User user) {
		User userExist = userRepository.findById(user.getId()).orElse(null);

		if (userExist != null) {
			userExist.setUsername(user.getUsername());
			userExist.setPassword(user.getPassword());
			userExist.setEmail(user.getEmail());
			userExist.setUpdated_at(new Date());

			userRepository.save(userExist);
		} else
			throw new NoSuchElementException();

		return userExist;
	}

	public String deleteUserById(Long userId) {
		userRepository.deleteById(userId);
		return "User successfully deleted";
	}

	public User buildUser(UserRequest userRequest) {
		return User.builder().username(userRequest.getUsername()).password(userRequest.getPassword())
				.email(userRequest.getEmail()).created_at(new Date()).updated_at(new Date()).build();
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().id(user.getId()).username(user.getUsername()).password(user.getPassword())
				.email(user.getEmail()).created_at(user.getCreated_at()).updated_at(user.getUpdated_at()).build();
	}
}
