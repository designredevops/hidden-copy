package com.veilsun.constructkey.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.veilsun.constructkey.specification.sequence.SequenceBySequenceIdSpec;
import com.veilsun.constructkey.specification.sequence.sequenceitem.SequenceItemBySequenceIdSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veilsun.constructkey.domain.Sequence;
import com.veilsun.constructkey.domain.SequenceItem;
import com.veilsun.constructkey.service.SequenceService;

@RestController
@RequestMapping("/sequence")
public class SequenceController {
    @Autowired
    private SequenceService sequenceService;

    @GetMapping("/{secId}")
    public ResponseEntity<?> getSequence(@PathVariable() UUID secId, SequenceBySequenceIdSpec spec,
                                         @RequestParam(name = "paths", required = false) String... paths) {
        return new ResponseEntity<Sequence>(sequenceService.findSequenceById(spec, paths), HttpStatus.OK);
    }

    @PutMapping("/{secId}")
    public ResponseEntity<?> updateSequence(
            @PathVariable() UUID secId,
            @Valid @RequestBody Sequence sequence) {
        return new ResponseEntity<Sequence>(sequenceService.updateSequence(secId, sequence), HttpStatus.OK);
    }

    @DeleteMapping("/{secId}")
    public ResponseEntity<?> deleteSequence(@PathVariable() UUID secId) {
        return new ResponseEntity<Boolean>(sequenceService.deleteSequence(secId), HttpStatus.OK);
    }

    @GetMapping("/{secId}/item")
    public ResponseEntity<?> getSequenceItems(@PathVariable() UUID secId,
                                              Pageable page,
                                              SequenceItemBySequenceIdSpec spec,
                                              @RequestParam(name = "paths", required = false) String... paths) {
        return new ResponseEntity<Page<SequenceItem>>(sequenceService.findAllSequenceItemsBySequenceId(
                spec, page, paths), HttpStatus.OK);
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
