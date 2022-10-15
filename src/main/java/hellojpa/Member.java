package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // jpa가 관리, db테이블과 매핑
public class Member {

    @Id // jpa 에게 primary key 알려줌, 직접할당 시(자동할당은 @GeneratedValue )
    private Long id; // 나중생각해서 크게 잡아(10억 넘어갈 때 타입integer>long이 더 힘들어) , 주민번호 pk로 쓰지마! 조인으로 곳곳에 퍼져있어 건드리기 어려워

    @Column(name = "name", nullable = false)
    private String username;

    private int age;

    @Enumerated(EnumType.STRING) // (XXX)ORDINAL 쓰면 enum에 값 추가할 때 디비 엉켜서 안 돼
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate; // 최신버전,년월 date 타입
    private LocalDateTime testLocalDateTime;//최신버전,년월일 timestamp 타입

    @Lob // 지정 속성 없음
    private String description;

    @Transient // 필드매핑x, 디비 연결 안 하고 임시로 메모리에만 넣어둠
    private Integer temp;

    

    // 동적 개체 생성해서 기본생성자 있어야 해
    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
