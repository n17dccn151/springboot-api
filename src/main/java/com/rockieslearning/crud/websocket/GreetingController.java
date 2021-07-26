package com.rockieslearning.crud.websocket;

import com.rockieslearning.crud.payload.request.LoginRequest;
import com.rockieslearning.crud.payload.response.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class GreetingController {



	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting( HtmlUtils.htmlEscape(message.getName()));
	}

}
