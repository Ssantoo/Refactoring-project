package refactoring.semiproject.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import refactoring.semiproject.domain.status.PhotoStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="PHOTO")
@Cacheable
@ToString
public class Photo extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @NotNull
    private String photoTitle;

    @NotNull
    private String photoContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PhotoStatus photoStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "photo")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Photo(Long id, String photoTitle, String photoContent, User user, PhotoStatus status, List<Reply> replies) {
        this.id = id;
        this.photoTitle = photoTitle;
        this.photoContent = photoContent;
        this.user = user;
        this.photoStatus = status;
        this.replies = replies;
    }

    //사진 상태 변경
    public void changePhotoStatusNo(){
        this.photoStatus = PhotoStatus.NO;
    }

    






}
