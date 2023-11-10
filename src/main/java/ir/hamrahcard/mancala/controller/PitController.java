package ir.hamrahcard.mancala.controller;

import ir.hamrahcard.mancala.enumeration.PitName;
import ir.hamrahcard.mancala.enumeration.PitSide;
import ir.hamrahcard.mancala.mapper.PitMapper;
import ir.hamrahcard.mancala.service.facade.PitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/game")
@RequiredArgsConstructor
public class PitController {

    private final PitService service;
    private final PitMapper mapper;

    @GetMapping("v1/new_game")
    public ResponseEntity<String> createGame() {
        service.createNewGame();
        return ResponseEntity.ok(service.showBoard());
    }

    @GetMapping("v1/show_board")
    public ResponseEntity<String> showBoard() {
        return ResponseEntity.ok(service.showBoard());
    }

    @GetMapping("v1/player/{side}/move/{name}")
    public ResponseEntity<String> move(@PathVariable("side") PitSide side, @PathVariable("name") PitName name) {
        return ResponseEntity.ok(service.move(side, name));
    }
}
