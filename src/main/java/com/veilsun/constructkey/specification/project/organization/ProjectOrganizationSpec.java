package com.veilsun.constructkey.specification.project.organization;

import com.veilsun.constructkey.domain.ProjectOrganization;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "project.id", pathVars = "projectId", spec = Equal.class),
        @Spec(path = "organization.id", params = "orgId", spec = Like.class),
        @Spec(path = "organization.name", params = "name", spec = Like.class)
})
public interface ProjectOrganizationSpec extends Specification<ProjectOrganization> {
}
