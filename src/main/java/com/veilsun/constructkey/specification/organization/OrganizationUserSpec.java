package com.veilsun.constructkey.specification.organization;

import javax.persistence.criteria.JoinType;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.OnTypeMismatch;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Join(path="memberTeam", alias="mt", type = JoinType.LEFT)
@Join(path="mt.members", alias="mtm", type = JoinType.LEFT)
@Or({
	@Spec(path = "mtm.user.id", constVal = "#{@authenticationService.userId}", valueInSpEL = true, spec = Equal.class, onTypeMismatch=OnTypeMismatch.EXCEPTION)
})
public interface OrganizationUserSpec extends OrganizationSpec {

}
