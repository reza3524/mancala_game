package ir.hamrahcard.mancala.mapper;

import ir.hamrahcard.mancala.base.BaseMapper;
import ir.hamrahcard.mancala.domain.Pit;
import ir.hamrahcard.mancala.dto.PitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PitMapper extends BaseMapper<Pit, PitDto, PitDto.Transfer> {
}
