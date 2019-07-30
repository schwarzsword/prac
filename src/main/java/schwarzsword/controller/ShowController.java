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
import schwarzsword.service.JsonService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShowController {

    private final DocumentRepository documentRepository;
    private final CourtRepository courtRepository;
    private final JsonService jsonService;

    @Autowired
    public ShowController(DocumentRepository documentRepository, CourtRepository courtRepository, JsonService jsonService) {
        this.documentRepository = documentRepository;
        this.courtRepository = courtRepository;
        this.jsonService = jsonService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ResponseEntity show() {
        List<DocumentEntity> list;
        list = documentRepository.findAll();
        return ResponseEntity.ok(jsonService.toJson(list));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/courts", method = RequestMethod.GET)
    public ResponseEntity getCourts() {
        List<String> courts = courtRepository.findAll().stream().map(CourtEntity::getName).collect(Collectors.toList());
        return ResponseEntity.ok(courts);
    }
}
