package com.lynhatkhanh.educationweb.educationweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "created_date")
    @CreatedDate
    Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    Date modifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    String modifiedBy;

}
