package com.koniukhov.tictactoe.controller;

import com.koniukhov.tictactoe.model.Player;
import com.koniukhov.tictactoe.model.RegistrationRequest;
import com.koniukhov.tictactoe.model.RegistrationResponse;
import com.koniukhov.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {
    private static final String REGISTRATION_DESTINATION = "/queue/registration";
    private static final String REGISTRATION_MAPPING = "/registration";

    @Autowired
    private SimpMessagingTemplate msgTemplate;
    @Autowired
    private PlayerRepository playerRepository;

    @MessageMapping(REGISTRATION_MAPPING)
    public void registration(@RequestBody RegistrationRequest request){
        var alreadyExists = playerRepository.existsByName(request.getName());

        if (alreadyExists){
            msgTemplate.convertAndSendToUser(
                    request.getAndroidId(),
                    REGISTRATION_DESTINATION,
                    new RegistrationResponse(RegistrationResponse.Status.ALREADY_EXISTS, RegistrationResponse.ALREADY_EXISTS_MSG));
        }else {
            Player player = new Player();
            player.setName(request.getName());
            playerRepository.save(player);
            msgTemplate.convertAndSendToUser(
                    request.getAndroidId(),
                    REGISTRATION_DESTINATION,
                    new RegistrationResponse(
                            RegistrationResponse.Status.SUCCESS,
                            RegistrationResponse.SUCCESS_MSG,
                            player.getId()));
        }
    }
}
