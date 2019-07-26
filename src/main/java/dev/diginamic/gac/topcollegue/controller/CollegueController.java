package dev.diginamic.gac.topcollegue.controller;

import dev.diginamic.gac.topcollegue.controller.DTO.CandidatVoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.diginamic.gac.topcollegue.controller.DTO.VoteDTO;
import dev.diginamic.gac.topcollegue.domain.Collegue;
import dev.diginamic.gac.topcollegue.domain.Vote;
import dev.diginamic.gac.topcollegue.exception.CollegueNotFound;
import dev.diginamic.gac.topcollegue.service.CollegueService;

import java.util.List;

@CrossOrigin(allowCredentials = "true")
@RestController
public class CollegueController {

    @Autowired
    CollegueService lesCollegues;

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Collegue rechercherParMatricule(@PathVariable String id) throws CollegueNotFound {
        return lesCollegues.rechercherById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/vote")
    public VoteDTO voter(@RequestBody VoteDTO vote) {
       return lesCollegues.voter(vote);
    }

    @RequestMapping(method = RequestMethod.GET, path = "votes")
    public List<CandidatVoteDto> getCandidats() {
        return lesCollegues.getCandidats(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
