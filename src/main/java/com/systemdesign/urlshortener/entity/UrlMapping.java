package com.systemdesign.urlshortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@TableGenerator(
        name="ID_GEN",
        table="GENERATOR_TABLE",
        pkColumnName = "keyName",
        valueColumnName = "hi",
        pkColumnValue="ID",
        allocationSize=20
)
@Entity
@Table(name = "url_mapping")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="ID_GEN")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "big_url")
    private String bigUrl;

    @Column(name = "short_url")
    private String short_url;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    public UrlMapping(String bigUrl, LocalDate createdAt, LocalDate expiredAt) {
        this.bigUrl = bigUrl;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}
