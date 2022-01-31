package com.veilsun.constructkey.specification.project.organization;

import com.veilsun.constructkey.domain.ProjectOrganization;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "project.id", pathVars = "projectId", spec = Equal.class)
public interface ProjectOrganizationSpec extends Specification<ProjectOrganization> {
}
