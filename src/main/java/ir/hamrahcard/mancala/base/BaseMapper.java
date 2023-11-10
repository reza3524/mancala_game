package ir.hamrahcard.mancala.base;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<
        E extends BaseEntity<? extends Serializable>,
        D extends BaseDto<? extends Serializable>,
        T extends BaseTransfer<? extends Serializable>> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> entity);

    D toDto(T transfer);

    T toTransfer(D dto);

    List<T> toTransfer(List<D> dto);
}
