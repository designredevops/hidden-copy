package com.veilsun.constructkey.specification.project;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.Project;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;


@And({
	@Spec(path = "organization.id", pathVars = "orgId", spec = Equal.class),
	@Spec(path = "name", params = "name", spec = Like.class),
	@Spec(path = "status", params = "status", paramSeparator = ',', spec = In.class)
})
public interface ProjectByOrganizationSpec extends Specification<Project> {

}
