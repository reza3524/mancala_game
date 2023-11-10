package ir.hamrahcard.mancala.service.impl.factory;

import ir.hamrahcard.mancala.domain.Pit;
import ir.hamrahcard.mancala.dto.PitDto;
import ir.hamrahcard.mancala.enumeration.PitName;

public class PitFactory extends AbstractFactory<Pit, PitDto, PitDto.Transfer> {
    @Override
    public Pit getEntity() {
        Pit pit = new Pit();
        pit.setId(1L);
        pit.setPosition(0);
        pit.setSize(6);
        pit.setName(PitName.A);
        pit.setMain(false);
        pit.setPlayer(new PlayerFactory().getEntity());
        return pit;
    }

    @Override
    public PitDto getDto() {
        return null;
    }

    @Override
    public PitDto.Transfer getTransferDto() {
        return null;
    }
}
