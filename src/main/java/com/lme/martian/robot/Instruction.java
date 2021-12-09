package com.lme.martian.robot;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Instruction {
	LEFT ("L"),
	RIGHT ("R"),
	FORWARD ("F");
	
	private final String initial;
	
	private Instruction (final String initial) {
		this.initial = initial;
	}
	
	private static final String validInstructionsString = Arrays.asList(values()).stream().map(i -> i.initial).collect(Collectors.toList()).toString().replace(",", "");
	
	public static String getValidInstructionString() {
		return validInstructionsString;
	}
	
	public static Instruction valueOfInitial(final String initial) {
	    for (Instruction instruction : values()) {
	        if (instruction.initial.equals(initial)) {
	            return instruction;
	        }
	    }
	    return null;
	}
	
}
