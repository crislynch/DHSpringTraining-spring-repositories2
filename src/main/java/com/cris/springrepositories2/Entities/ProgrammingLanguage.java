package com.cris.springrepositories2.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String name;

    @Setter
    @Getter
    private OffsetDateTime firstAppearance;

    @Setter
    @Getter
    @Column(nullable = false)
    private String inventor;

}