package org.dongguk.camputhon.domain;

import org.dongguk.camputhon.domain.common.BaseEntity;
import org.dongguk.camputhon.domain.enums.Gender;
import lombok.*;
import jakarta.persistence.*;
import org.dongguk.camputhon.domain.enums.ShortType;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // dbms
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name="id")
    private Long id;

    @Column(unique = true, nullable = false, name="uuid")
    private String uuid;

    @Column(name="typeImg")
    private String typeImg;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'NONE'", name="shortType")
    private ShortType shortType;

    @Column(name="typeSummary")
    private String typeSummary;

    @Column(name="advantage")
    private String advantage;

    @Column(name="develop")
    private String develop;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'MALE'", name="sex")
    private Gender sex;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Short> shorts;
}
