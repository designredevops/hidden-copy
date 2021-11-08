package com.veilsun.constructkey.utils;

import java.util.Arrays;

import com.cosium.spring.data.jpa.entity.graph.domain.DynamicEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;

public class EGUtils {
	/**
	   * @param attributePaths The attribute paths to be present in the result
	   * @return A {@link DynamicEntityGraph} with the path attributes passed in as arguments.
	   */
	  public static EntityGraph fromAttributePaths(String... attributePaths) {
		  if(attributePaths == null || attributePaths.length == 0) attributePaths = new String[]{"id"};
	    return new DynamicEntityGraph(Arrays.asList(attributePaths));
	  }
}
