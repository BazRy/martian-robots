package com.lme.martian.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

public class InstructionsUtil {
	
	private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^[" + Instruction.getValidInstructionString() +"]+$");

	public static List<Instruction> parseInstructionString (String instructionString) {
		Matcher matcher = INSTRUCTION_PATTERN.matcher(instructionString);
        if (matcher.matches()) {
			List<Character> characters = new ArrayList<>(instructionString.length());	
	    	characters.addAll(Arrays.asList(ArrayUtils.toObject(instructionString.toCharArray())));
	    	return characters.stream().map(character -> Instruction.valueOfInitial(character.toString())).collect(Collectors.toList());
        }
        return Collections.emptyList();
	}
}
