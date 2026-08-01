package net.cfauto.cypress_lib_core.mixin;

import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import ext.client.InputHandler;
import ext.client.entity.player.EntityPlayerZombieModeClient;
import ext.newblock.BlockVendingMachine;
import ext.newblock.EnumVendingMachine;
import ext.phys.XYZv;
import net.cfauto.cypress_lib_core.util.VendingMachineEnum;

@Mixin(BlockVendingMachine.class)
public class BlockVendingMachineMixin {

	@Shadow
	public EnumVendingMachine field_1_795;

	/**
	 * @author FMG793
	 * @reason Make interacting with the vending machine not crash the game
	 */
	@Overwrite
	public void method_1_2459(EntityPlayerZombieModeClient player, XYZv xYZv2) {
	    if(!player.vendingMachine.method_1_1511(this.field_1_795)) {
			int i3 = player.zmWorld.method_1_875(this.field_1_795);
			if(player.vendingMachine.method_1_1513() >= (long)i3) {
				player.vendingMachine.givePoints((long)(-i3));
				VendingMachineEnum newEVM = VendingMachineEnum.origEVM(field_1_795);
				switch(newEVM) {
				case Armor:
					player.vendingMachine.field_1_1993 = true;
					break;
				case Dash:
					player.vendingMachine.field_1_1996 = true;
					break;
				case HealthBoost:
					player.vendingMachine.field_1_1992 = true;
					break;
				case QuickRevive:
					player.vendingMachine.field_1_1994 = true;
				}
			} else {
				InputHandler.minecraft.gui.printChatMessage("Not enough points!");
			}
		}

	}

	/**
	 * @author FMG793
	 * @reason Make interacting with the vending machine not crash the game
	 */
	@Overwrite
	public String method_1_2460(EntityPlayerZombieModeClient player, XYZv xYZv2) {
		String string3 = Keyboard.getKeyName(InputHandler.minecraft.options.inventoryKey.keyCode);
		if(player.vendingMachine.method_1_1511(this.field_1_795)) {
			return "You already have this perk.";
		} else {
			VendingMachineEnum newEVM = VendingMachineEnum.origEVM(field_1_795);
			switch(newEVM) {
			case Armor:
				return "[" + string3 + "] buy Armor [" + player.zmWorld.method_1_875(this.field_1_795) + " points]";
			case Dash:
				return "[" + string3 + "] buy Dash [" + player.zmWorld.method_1_875(this.field_1_795) + " points]";
			case HealthBoost:
				return "[" + string3 + "] buy Berzerkola [" + player.zmWorld.method_1_875(this.field_1_795) + " points]";
			case QuickRevive:
				return "[" + string3 + "] buy Revive [" + player.zmWorld.method_1_875(this.field_1_795) + " points]";
			default:
				return "--";
			}
		}
	}

	/**
	 * @author FMG793
	 * @reason Make interacting with the vending machine not crash the game
	 */
	@Overwrite
	public int method_1_2461() {
		VendingMachineEnum newEVM = VendingMachineEnum.origEVM(field_1_795);
		switch(newEVM) {
		case Armor:
			return 2;
		case Dash:
			return 3;
		case HealthBoost:
			return 1;
		case QuickRevive:
			return 4;
		default:
			return -1;
		}
	}
}
