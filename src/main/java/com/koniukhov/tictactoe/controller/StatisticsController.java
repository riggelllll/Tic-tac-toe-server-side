package com.koniukhov.tictactoe.controller;

import com.koniukhov.tictactoe.model.BaseRequest;
import com.koniukhov.tictactoe.model.StatisticsResponse;
import com.koniukhov.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StatisticsController {
    private static final String STATISTICS_DESTINATION = "/queue/statistics";
    private static final String STATISTICS_MAPPING = "/statistics";

    @Autowired
    private SimpMessagingTemplate msgTemplate;
    @Autowired
    private PlayerRepository playerRepository;

    @MessageMapping(STATISTICS_MAPPING)
    public void statistics(@RequestBody BaseRequest request){
        var player = playerRepository.findById(request.getPlayerId()).get();

        msgTemplate.convertAndSendToUser(
                String.valueOf(request.getPlayerId()),
                STATISTICS_DESTINATION,
                new StatisticsResponse(player.getName(), player.getNumberOfGames(), player.getNumberOfWins()));
    }
}
