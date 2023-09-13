package project.music.spring.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;


import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6704984520692265949L;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_by")
    @GeneratedValue
    protected UUID createdBy;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP")
    protected LocalDateTime createdOn;

    @Column(name = "updated_by")
    @GeneratedValue
    protected UUID updatedBy;

    @Column(name = "updated_on", columnDefinition = "TIMESTAMP")
    protected LocalDateTime updatedOn;
}
