package com.veilsun.constructkey.specification.project.organization;

import com.veilsun.constructkey.domain.ProjectOrganization;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "projectOrganizationId", spec = Equal.class)
public interface ProjectOrganizationByProjectOrganizationIdSpec extends Specification<ProjectOrganization> {
}
