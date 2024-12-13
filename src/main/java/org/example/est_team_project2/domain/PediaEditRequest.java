package org.example.est_team_project2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class PediaEditRequest {

    @Id
    @Column(name = "pedia_edit_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedia_version_id")
    private PediaVersion pediaVersion;

    @ManyToOne
    @JoinColumn(name = "responded_member_id")
    private Member respondedMember;

    @ManyToOne
    @JoinColumn(name = "requested_member_id")
    private Member requestedMember;

    private LocalDateTime closedAt;
    private CommonStatus status;
}
