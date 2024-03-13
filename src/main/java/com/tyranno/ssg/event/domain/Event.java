package com.tyranno.ssg.event.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_content")
    private String eventContent;

    @Column(name = "event_image_url", nullable = false)
    private String eventImageUrl;
}
