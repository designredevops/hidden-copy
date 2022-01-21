package com.veilsun.constructkey.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Sequence;
import com.veilsun.constructkey.domain.SequenceItem;
import com.veilsun.constructkey.service.SequenceService;

@RestController
@RequestMapping("/sequence")
public class SequenceController {
    @Autowired
    private SequenceService sequenceService;

    @GetMapping("/{secId}")
    public ResponseEntity<?> getSequence(@PathVariable() UUID secId) {
        return new ResponseEntity<Sequence>(sequenceService.findSequenceById(secId), HttpStatus.OK);
    }

    @PutMapping("/{secId}")
    public ResponseEntity<?> updateSequence(
            @PathVariable() UUID secId, @Valid @RequestBody Sequence sequence ) {
        return new ResponseEntity<Sequence>(sequenceService.updateSequence(secId, sequence), HttpStatus.OK);
    }

    @DeleteMapping("/{secId}")
    public ResponseEntity<?> deleteSequence(@PathVariable() UUID secId) {
        return new ResponseEntity<Boolean>(sequenceService.deleteSequence(secId), HttpStatus.OK);
    }

    @GetMapping("/{secId}/item")
    public ResponseEntity<?> getSequenceItems(@PathVariable() UUID secId, Pageable page) {
        return new ResponseEntity<Page<SequenceItem>>(sequenceService.findAllSequenceItemsBySequenceId(
                secId, page), HttpStatus.OK);
    }

    @PostMapping("/{secId}/item")
    public ResponseEntity<?> createSequenceItem(
            @PathVariable() UUID secId, @Valid @RequestBody SequenceItem sequenceItem) {
        return new ResponseEntity<SequenceItem>(sequenceService.createSequenceItem(secId, sequenceItem), HttpStatus.CREATED);
    }

    @PutMapping("/{secId}/item/{itemId}")
    public ResponseEntity<?> updateSequenceItem(
            @PathVariable() UUID secId, @Valid @RequestBody SequenceItem sequenceItem, @PathVariable() UUID itemId ) {
        return new ResponseEntity<SequenceItem>(sequenceService.updateSequenceItem(secId, sequenceItem, itemId), HttpStatus.OK);
    }

    @DeleteMapping("/{secId}/item/{itemId}")
    public ResponseEntity<?> deleteSequenceItem(@PathVariable() UUID itemId) {
        return new ResponseEntity<Boolean>(sequenceService.deleteSequenceItem(itemId), HttpStatus.OK);
    }
}
