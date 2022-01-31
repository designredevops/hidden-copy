package com.veilsun.constructkey.specification.team;

import com.veilsun.constructkey.domain.Team;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "teamId", spec = Equal.class)
public interface TeamByTeamIdSpec extends Specification<Team> {
}
