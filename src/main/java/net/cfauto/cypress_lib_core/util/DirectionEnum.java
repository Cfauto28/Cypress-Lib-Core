package net.cfauto.cypress_lib_core.util;

import ext.phys.Direction;

public enum DirectionEnum {
	XP(0),
	ZP(1),
	XM(2),
	ZM(3);
	
	public final int direction;

	private DirectionEnum(int i3) {
		this.direction = i3;
	}
	
	public static DirectionEnum origDE(Direction origEVM) {
		int ord = origEVM.ordinal();
		if (ord == Direction.XP.ordinal()) {
			return XP;
		} else if (ord == Direction.ZP.ordinal()) {
			return ZP;
		} else if (ord == Direction.XM.ordinal()) {
			return XM;
		} else if (ord == Direction.ZM.ordinal()) {
			return ZM;
		}
		return null;
	}
}
