package com.veilsun.constructkey.specification.project.location;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
        @Spec(path = "id", pathVars = "locationId", spec = Equal.class),
        @Spec(path = "name", params = "name", spec = Like.class)
})
public interface ProjectLocationsByIdSpec extends ProjectLocationsByProjectSpec {
}
