package com.veilsun.constructkey.specification.project.location;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "id", pathVars = "locationId", spec = Equal.class)
public interface ProjectLocationsByIdSpec extends ProjectLocationsByProjectSpec {
}
