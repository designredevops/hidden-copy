package com.veilsun.constructkey.specification.project;

import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.Project;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
        @Spec(path = "id", pathVars = "projectId", spec = Equal.class),
        @Spec(path = "organization.id", pathVars = "orgId", spec = Equal.class)
})
public interface ProjectIdSpec extends Specification<Project> {

}
