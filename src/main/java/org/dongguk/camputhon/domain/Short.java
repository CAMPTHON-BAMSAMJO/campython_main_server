package org.dongguk.camputhon.domain;

import org.dongguk.camputhon.domain.common.BaseEntity;
import org.dongguk.camputhon.domain.enums.Activity;
import org.dongguk.camputhon.domain.enums.Gender;
import lombok.*;
import jakarta.persistence.*;
import org.dongguk.camputhon.domain.enums.Location;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // dbms
@AllArgsConstructor
public class Short extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="shortUrl")
    private String shortUrl;

    @Column(name="startAt")
    private LocalTime startAt;

    @Column(name="endAt")
    private LocalTime endAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'MALE'", name="activity")
    private Activity activity;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'MALE'", name="location")
    private Location location;

    @Column(name="content")
    private String content;


    public void updateUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
