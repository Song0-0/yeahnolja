package com.room.yeahnolja.domain.hotel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
@Getter
@Setter
@DynamicInsert
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    private int star;
    @Column(nullable = false)
    private String description;
    private String email;
    private int rooms;
    @CreationTimestamp
    private LocalDateTime regDt;
    @UpdateTimestamp
    private LocalDateTime modDt;
    private String delYn;

}
