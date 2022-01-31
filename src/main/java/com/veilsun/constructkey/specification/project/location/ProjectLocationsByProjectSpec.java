package com.veilsun.constructkey.specification.project.location;

import com.veilsun.constructkey.domain.ProjectLocation;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "project.id", pathVars = "projectId", spec = Equal.class),
        @Spec(path = "name", params = "name", spec = Like.class)
})
public interface ProjectLocationsByProjectSpec extends Specification<ProjectLocation> {
}
