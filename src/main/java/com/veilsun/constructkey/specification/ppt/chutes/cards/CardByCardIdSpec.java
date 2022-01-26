package com.veilsun.constructkey.specification.ppt.chutes.cards;

import com.veilsun.constructkey.domain.Card;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "cardId", spec = Equal.class)
public interface CardByCardIdSpec extends Specification<Card> {
}
