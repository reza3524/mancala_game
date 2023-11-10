package ir.hamrahcard.mancala.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseRepository<E extends BaseEntity<? extends Serializable>, I extends Serializable> extends JpaRepository<E, I> {
}
