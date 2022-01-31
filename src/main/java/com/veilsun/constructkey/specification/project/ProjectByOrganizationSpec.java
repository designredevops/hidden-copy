package com.veilsun.constructkey.specification.project;

import com.veilsun.constructkey.domain.Project;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;



@Spec(path = "organization.id", pathVars = "orgId", spec = Equal.class)
public interface ProjectByOrganizationSpec extends Specification<Project> {

}
