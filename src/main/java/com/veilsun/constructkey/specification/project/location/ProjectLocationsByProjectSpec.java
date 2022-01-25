package com.veilsun.constructkey.specification.project.location;

import com.veilsun.constructkey.domain.ProjectLocation;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "project.id", pathVars = "projectId", spec = Equal.class)
public interface ProjectLocationsByProjectSpec extends Specification<ProjectLocation> {
}
