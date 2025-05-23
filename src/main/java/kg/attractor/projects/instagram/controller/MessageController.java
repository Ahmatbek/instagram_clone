package kg.attractor.projects.instagram.controller;

import kg.attractor.projects.instagram.model.Greeting;
import kg.attractor.projects.instagram.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Model model, Message message) throws Exception {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.name()) + "!");
    }

    @GetMapping()
    public String greeting(Model model) {
        return "websocket";
    }

}
