package net.cfauto.cypress_lib_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import ext.inventory.InventoryVendingMachine;
import ext.newblock.EnumVendingMachine;
import net.cfauto.cypress_lib_core.util.VendingMachineEnum;

@Mixin(InventoryVendingMachine.class)
public class InventoryVendingMachineMixin {

	@Shadow
	public boolean field_1_1992;

	@Shadow
	public boolean field_1_1993;

	@Shadow
	public boolean field_1_1996;

	@Shadow
	public boolean field_1_1994;

	/**
	 * @author FMG793
	 * @reason
	 */
	@Overwrite
	public boolean method_1_1511(EnumVendingMachine vendingMachineEnum) {
		VendingMachineEnum newEVM = VendingMachineEnum.origEVM(vendingMachineEnum);
		switch(newEVM) {
		case Armor:
			return this.field_1_1993;
		case Dash:
			return this.field_1_1996;
		case HealthBoost:
			return this.field_1_1992;
		case QuickRevive:
			return this.field_1_1994;
		default:
			return false;
		}
	}
}
