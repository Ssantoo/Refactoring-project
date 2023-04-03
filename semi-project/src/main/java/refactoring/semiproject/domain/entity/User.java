package refactoring.semiproject.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="PHOTO")
@Cacheable
@ToString
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String password;

    private String nickName;

    private String email;







}
