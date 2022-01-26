package com.veilsun.constructkey.service;

import java.util.UUID;

import com.veilsun.constructkey.specification.sequence.SequenceBySequenceIdSpec;
import com.veilsun.constructkey.specification.sequence.sequenceitem.SequenceItemBySequenceIdSpec;
import com.veilsun.constructkey.utils.EGUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Sequence;
import com.veilsun.constructkey.domain.SequenceItem;
import com.veilsun.constructkey.repository.SequenceItemRepository;
import com.veilsun.constructkey.repository.SequenceRepository;

@Service
public class SequenceService {

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private SequenceItemRepository sequenceItemRepository;

    public Sequence findSequenceById(UUID secId) {
        return sequenceRepository.findById(secId).orElseThrow();
    }

    public Sequence updateSequence(UUID secId, Sequence sequence) {
        Sequence originalSequence = sequenceRepository.findById(secId).orElseThrow();
        if (sequence.getName() != null) originalSequence.setName(sequence.getName());
        return sequenceRepository.save(originalSequence);
    }

    public Boolean deleteSequence(UUID secId) {
        sequenceRepository.deleteById(secId);
        return true;
    }

    public Page<SequenceItem> findAllSequenceItemsBySequenceId(UUID secId, Pageable page) {
        return sequenceItemRepository.findAllBySequenceId(secId, page);
    }

    public SequenceItem createSequenceItem(UUID secId, SequenceItem sequenceItem) {
        sequenceItem.setSequence(new Sequence(secId));
        return sequenceItemRepository.save(sequenceItem);
    }

    public SequenceItem updateSequenceItem(UUID secId, SequenceItem sequenceItem, UUID itemId) {
        SequenceItem originalSequenceItem = sequenceItemRepository.findById(itemId).orElseThrow();
        if(sequenceItem.getTitle() != null) originalSequenceItem.setTitle(sequenceItem.getTitle());
        if(sequenceItem.getRanking() != null) originalSequenceItem.setRanking(sequenceItem.getRanking());
        return sequenceItemRepository.save(originalSequenceItem);
    }

    public Boolean deleteSequenceItem(UUID itemId) {
        sequenceItemRepository.deleteById(itemId);
        return true;
    }

    public Sequence findSequenceById(SequenceBySequenceIdSpec spec, String[] paths) {
        return sequenceRepository.findOne(spec, EGUtils.fromAttributePaths(paths)).orElseThrow();
    }

    public Page<SequenceItem> findAllSequenceItemsBySequenceId(SequenceItemBySequenceIdSpec spec, Pageable page, String[] paths) {
        return sequenceItemRepository.findAll(spec, page, EGUtils.fromAttributePaths(paths));
    }
}
