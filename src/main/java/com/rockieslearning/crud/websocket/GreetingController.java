package com.rockieslearning.crud.websocket;

import com.google.gson.Gson;
import com.rockieslearning.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class GreetingController {


	@Autowired
	private OrderService orderService;

	@Autowired
	private EntityManager entityManager;





	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting( HtmlUtils.htmlEscape(message.getName()));
	}


	@MessageMapping("/hi")
	@SendTo("/topic/dasboards")
	public Greeting testDashboard(HelloMessage message) throws Exception {
//		List<OrderDto> orderDtos = new ArrayList<>();
//		orderDtos = orderService.retrieveOrders();
//		Thread.sleep(1000); // simulated delay
//
//		String json = new Gson().toJson(orderDtos);


		List<Object> objects = new ArrayList<>();
		Query query = entityManager.createNativeQuery("select f.food_name, subR.sum, subR.my_rank from ( select *, rank() over ( order by sub.sum desc) as my_rank from ( select food_id , sum(amount) from order_foods group by food_id )as sub) as subR , foods f where f.food_id = subR.food_id and subR.my_rank <= 10 order by subR.my_rank\n" );
		objects = query.getResultList();
		Thread.sleep(1000); // simulated delay

		String json = new Gson().toJson(objects);




		return new Greeting( json);
	}


}
