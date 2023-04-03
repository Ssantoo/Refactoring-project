package refactoring.semiproject.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import refactoring.semiproject.domain.dto.replyDto.ReplyReqDto;
import refactoring.semiproject.domain.status.PhotoStatus;
import refactoring.semiproject.domain.status.ReplyStatus;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name="Reply")
@Entity
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id; //댓글번호

    @NotNull
    private String replyContent; //댓글내용

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="photo_id")
    private Photo photo;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ReplyStatus replyStatus; // 댓글 status

    @Builder
    public Reply(Long id, String replyContent, Photo photo, User user, ReplyStatus replyStatus) {
        this.id = id;
        this.replyContent = replyContent;
        this.photo = photo;
        this.user = user;
        this.replyStatus = replyStatus;
    }



    //연관관계 편의 메서드

    public Reply addPhoto(Photo photo){
        this.photo = photo;
        photo.addReply(this);
        return this;
    }




    //댓글 상태 변경
    public void changeReplyStatusNo(){
        this.replyStatus = ReplyStatus.NO;
    }








}
