package schwarzsword.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schwarzsword.entity.CourtEntity;
import schwarzsword.entity.DocumentEntity;
import schwarzsword.repository.CourtRepository;
import schwarzsword.repository.DocumentRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@RestController
public class ActionController {

    private final DocumentRepository documentRepository;
    private final CourtRepository courtRepository;

    @Autowired
    public ActionController(DocumentRepository documentRepository, CourtRepository courtRepository) {
        this.documentRepository = documentRepository;
        this.courtRepository = courtRepository;
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity delete(@RequestParam String id) {
        Optional<DocumentEntity> optional = documentRepository.findById(Integer.parseInt(id));
        optional.ifPresent(documentRepository::delete);
        return ResponseEntity.ok(true);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity edit(@RequestParam String idStr, @RequestParam String receivingDateStr,
                               @RequestParam String info, @RequestParam String court,
                               @RequestParam String judge, @RequestParam String caseNumber,
                               @RequestParam String plaintiff, @RequestParam String defendant,
                               @RequestParam String executionDateStr, @RequestParam String closingNumber,
                               @RequestParam String closingDateStr, @RequestParam String sendingDateStr,
                               @RequestParam String status) {

        DocumentEntity newDocument;
        Date receivingDate = null;
        Date executingDate = null;
        try {
            receivingDate = new SimpleDateFormat("yyyy-MM-dd").parse(receivingDateStr);
            executingDate = new SimpleDateFormat("yyyy-MM-dd").parse(executionDateStr);
        } catch (ParseException e) {
            System.out.println("одна или несколько дат не указаны");
        }
        Optional<CourtEntity> optionalCourtEntity = courtRepository.findByName(court);
        CourtEntity courtEntity;
        if (optionalCourtEntity.isPresent()) {
            courtEntity = optionalCourtEntity.get();
        } else {
            courtEntity = new CourtEntity(court);
            courtRepository.save(courtEntity);
        }
        if (!idStr.equals("")) {
            int id = Integer.parseInt(idStr);
            Optional<DocumentEntity> optional = documentRepository.findById(id);
            newDocument = optional.get();

            Date closingDate = null;
            Date sendingDate = null;
            try {
                closingDate = new SimpleDateFormat("yyyy-MM-dd").parse(closingDateStr);
                sendingDate = new SimpleDateFormat("yyyy-MM-dd").parse(sendingDateStr);
            } catch (ParseException e) {
                System.out.println("одна или несколько дат не указаны");
            }


            if (sendingDate != null)
                newDocument.setIsExecuted(true);
            newDocument.setReceivingDate(receivingDate);
            newDocument.setInfo(info);
            newDocument.setCourtByCourtName(courtEntity);
            newDocument.setJudgeName(judge);
            newDocument.setCaseNumber(caseNumber);
            newDocument.setPlaintiff(plaintiff);
            newDocument.setDefendant(defendant);
            newDocument.setExecutionDate(executingDate);
            newDocument.setClosingNumber(closingNumber);
            newDocument.setClosingDate(closingDate);
            newDocument.setSendingDate(sendingDate);
            newDocument.setStatus(status);
            documentRepository.save(newDocument);
        } else {
            newDocument = new DocumentEntity(receivingDate, info, courtEntity,
                    judge, plaintiff, defendant, executingDate, status);
            documentRepository.save(newDocument);
        }
        return ResponseEntity.ok(newDocument);
    }
}