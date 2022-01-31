package com.veilsun.constructkey.specification.team;

import com.veilsun.constructkey.domain.TeamMember;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "team.id", pathVars = "teamId", spec = Equal.class)
public interface TeamMembersByTeamIdSpec extends Specification<TeamMember> {
}
