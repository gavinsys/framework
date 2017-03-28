package com.easytonent.tools;

import java.util.UUID;

public class ToolUUID {
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
