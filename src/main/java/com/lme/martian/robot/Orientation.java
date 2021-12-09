package com.lme.martian.robot;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

public enum Orientation {
	N,
	S,
	E,
	W;
	
	private static final Map<Orientation, Pair<Orientation, Orientation>> adjacentOrientations = new HashMap<Orientation, Pair<Orientation,Orientation>>();
	
	static {
		adjacentOrientations.put(N, Pair.of(W, E));
		adjacentOrientations.put(S, Pair.of(E, W));
		adjacentOrientations.put(E, Pair.of(N, S));
		adjacentOrientations.put(W, Pair.of(S, N)); 
	}

	public static Pair<Orientation, Orientation> getAdjacentOrientations(Orientation startingOrientation) {
		return adjacentOrientations.get(startingOrientation);
	}
	
}
