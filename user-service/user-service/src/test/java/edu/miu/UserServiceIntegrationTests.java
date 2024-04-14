package edu.miu;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import edu.miu.dto.UserRequest;
import edu.miu.dto.UserResponse;
import edu.miu.model.User;
import edu.miu.response.CustomResponseMessage;
import edu.miu.service.UserService;
import lombok.RequiredArgsConstructor;


@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserServiceIntegrationTests {

		private final MockMvc mockMvc;
		@MockBean
		private UserService userService;
		
		private ObjectMapper mapper = new ObjectMapper();
		private Long userId = 5L;

		@Test
		void addUserTest() throws Exception {
			UserRequest userRequest = buildUserRequest();
			
			String jsonUserString = mapper.writeValueAsString(userRequest);
			CustomResponseMessage expectedResponse = new CustomResponseMessage();
			expectedResponse.setResponse("User with username '" + userRequest.getUsername() + "' successfully added!");

			when(userService.addUser(any())).thenReturn(expectedResponse.getResponse());
			
			this.mockMvc.perform(post("/users").content(jsonUserString).contentType(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isCreated());
			
			
		}
		
		@Test
		void getUserTest() throws Exception {
			UserRequest userRequest = buildUserRequest();
			String jsonUserString = new Gson().toJson(userRequest);
			
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(buildUser(), userResponse);
			
			when(userService.findUserById(userId)).thenReturn(userResponse);
			
			this.mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", userResponse.getId()))
				.andDo(print())
				.andExpect(status().isFound());
		}
		
		public User buildUser() {
			return User.builder()
					.id(userId)
					.username("miaoubich")
					.password("Ali@23")
					.email("miaoubich@gmail.com")
					.created_at(new Date())
					.updated_at(new Date())
					.build();
		} 

		public UserRequest buildUserRequest() {
			return UserRequest.builder()
					.username("miaoubich")
					.password("Ali@23")
					.email("miaoubich@gmail.com")
					.created_at(new Date())
					.updated_at(new Date())
					.build();
		}
}
