package schwarzsword.entity;

import lombok.Data;


import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "document", schema = "public", catalog = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "receiving_date")
    private Date receivingDate;
    @Basic
    @Column(name = "info")
    private String info;
    @Basic
    @Column(name = "case_number")
    private String caseNumber;
    @Basic
    @Column(name = "judge_name")
    private String judgeName;
    @Basic
    @Column(name = "plaintiff")
    private String plaintiff;
    @Basic
    @Column(name = "defendant")
    private String defendant;
    @Basic
    @Column(name = "execution_date")
    private Date executionDate;
    @Basic
    @Column(name = "is_executed")
    private Boolean isExecuted;
    @Basic
    @Column(name = "closing_number")
    private String closingNumber;
    @Basic
    @Column(name = "closing_date")
    private Date closingDate;
    @Basic
    @Column(name = "sending_date")
    private Date sendingDate;
    @Basic
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "courtId")
    private CourtEntity courtByCourtName;

    protected DocumentEntity() {
    }

    public DocumentEntity(Date receivingDate, String info, CourtEntity court,
                          String judgeName, String plaintiff,
                          String defendant, Date executionDate, String status) {
        this.receivingDate = receivingDate;
        this.info = info;
        this.judgeName = judgeName;
        this.plaintiff = plaintiff;
        this.defendant = defendant;
        this.executionDate = executionDate;
        this.status = status;
        this.courtByCourtName = court;
        this.isExecuted = false;
    }

}
