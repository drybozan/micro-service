package com.micro.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //proje genelinde bu classı entegre ettiğimizde bu classı ata class olarak işaretler.
@EntityListeners(AuditingEntityListener.class) //audit>AuditingEntityListener classını kullanabilmek için bu classı ata class olarak isaretler
@Getter
@Setter
@ToString // temel varlık nesnlerini string formatına dönüştürür.
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) //db deki kolon ismi ile eşleştiği için column içinde ekstra name eklenmez.
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false) //db ye kayıt eklendiğinde bu alana kayıt eklemenize izin vermez.
    private String updatedBy;
}