package ir.hamrahcard.mancala.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
//TODO Add Audit to Base 9-nov-2023
public class BaseEntity<I extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2505435378398121286L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private I id;

    @Version
    @Column(name = "n_version", nullable = false)
    private Integer version;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
