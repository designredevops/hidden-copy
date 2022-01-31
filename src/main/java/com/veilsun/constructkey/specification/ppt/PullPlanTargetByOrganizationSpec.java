package com.veilsun.constructkey.specification.ppt;

import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.PullPlanTarget;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Join(path = "project", alias = "p")
@And({
        @Spec(path = "p.organization.id", pathVars = "orgId", spec = Equal.class),
        @Spec(path = "name", params = "name", spec = Like.class),
        @Spec(path = "status", params = "status", paramSeparator = ',', spec = In.class)

})
public interface PullPlanTargetByOrganizationSpec extends Specification<PullPlanTarget> {

}
