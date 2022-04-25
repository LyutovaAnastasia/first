package com.company.persistence.entity;

import com.company.domain.model.enums.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesSeqGenerator")
    @SequenceGenerator(name = "rolesSeqGenerator", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    public RoleEntity(RoleEnum name) {
        this.name = name;
    }
}
