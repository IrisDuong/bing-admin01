package com.bh.admin.util;

import java.util.Optional;
import java.util.StringJoiner;

public class CustomStringUtils {

	public static String buildJoinnerField(FieldDef[] fields) {
		StringJoiner joiner = new StringJoiner(", ");
		for(FieldDef field:fields) {
			joiner.add(String.format("%s is %s", field.getFieldName(), field.getValue()));
		}
		return joiner.toString();
	}
}
