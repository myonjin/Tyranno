package com.tyranno.ssg.event.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_content")
    private String eventContent;

    @Column(name = "event_image_url", nullable = false)
    private String eventImageUrl;
}
