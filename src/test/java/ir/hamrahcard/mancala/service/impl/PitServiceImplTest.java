package ir.hamrahcard.mancala.service.impl;

import ir.hamrahcard.mancala.domain.Pit;
import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.enumeration.PitName;
import ir.hamrahcard.mancala.repository.PitRepository;
import ir.hamrahcard.mancala.service.impl.factory.PlayerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PitServiceImplTest {
    @InjectMocks
    private PitServiceImpl service;

    @Mock
    private PitRepository repository;

    @Mock
    private PlayerServiceImpl playerService;

    @Test
     void testPit_deleteAll() {
    }
}
