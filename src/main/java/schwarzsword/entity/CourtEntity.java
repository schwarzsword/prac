package schwarzsword.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "court", schema = "public", catalog = "documents")
public class CourtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int courtId;
    @Basic
    @Column(name = "name")
    private String name;

    protected CourtEntity(){}

    public CourtEntity(String name){
        this.name = name;
    }
}
