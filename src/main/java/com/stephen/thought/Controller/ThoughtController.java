package com.stephen.thought.Controller;

import com.stephen.thought.dto.ThoughtDto;
import com.stephen.thought.service.ThoughtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thoughts")
public class ThoughtController {

    private final ThoughtService thoughtService;

    public ThoughtController(ThoughtService thoughtService) {
        this.thoughtService = thoughtService;
    }
    @PostMapping
    public ResponseEntity<ThoughtDto> createThought(@RequestBody ThoughtDto thoughtDto){
        ThoughtDto savedThought = thoughtService.createThought(thoughtDto);
        return new ResponseEntity<>(savedThought, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThoughtDto> getThoughtById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(thoughtService.getThoughtById(id));
    }
}
