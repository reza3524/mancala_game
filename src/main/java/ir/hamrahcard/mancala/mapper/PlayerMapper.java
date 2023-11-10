package ir.hamrahcard.mancala.mapper;

import ir.hamrahcard.mancala.base.BaseMapper;
import ir.hamrahcard.mancala.domain.Player;
import ir.hamrahcard.mancala.dto.PlayerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper extends BaseMapper<Player, PlayerDto, PlayerDto.Transfer> {
}
