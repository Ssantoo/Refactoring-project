package refactoring.semiproject.domain.entity;


import com.sun.istack.NotNull;
import lombok.*;
import refactoring.semiproject.domain.Status;

import javax.persistence.*;

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
    private Status status;

    @Builder
    public Photo(Long id, String photoTitle, String photoContent, User user, Status status) {
        this.id = id;
        this.photoTitle = photoTitle;
        this.photoContent = photoContent;
        this.user = user;
        this.status = status;
    }


    //사진 상태 변경
    public void changePhotoStatus(){
        this.status = Status.NO;
    }








}
