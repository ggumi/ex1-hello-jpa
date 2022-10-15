package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity // jpa가 관리, db테이블과 매핑
public class Member {

    @Id // jpa 에게 primary key 알려줌
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    private int age;

    @Enumerated(EnumType.ORDINAL) // 디폴트
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;
//    디비 연결 안 하고 임시로 메모리에만 넣어둠
    

    // 동적 개체 생성해서 기본생성자 있어야 해
    public Member() {
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
