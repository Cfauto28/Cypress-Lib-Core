package net.cfauto.cypress_lib_core.util;

import ext.newblock.EnumVendingMachine;

public enum VendingMachineEnum {
	HealthBoost,
	Armor,
	Dash,
	QuickRevive;
	
	public static VendingMachineEnum origEVM(EnumVendingMachine origEVM) {
		int ord = origEVM.ordinal();
		if (ord == EnumVendingMachine.Armor.ordinal()) {
			return Armor;
		} else if (ord == EnumVendingMachine.Dash.ordinal()) {
			return Dash;
		} else if (ord == EnumVendingMachine.HealthBoost.ordinal()) {
			return HealthBoost;
		} else if (ord == EnumVendingMachine.QuickRevive.ordinal()) {
			return QuickRevive;
		}
		return null;
	}
}


