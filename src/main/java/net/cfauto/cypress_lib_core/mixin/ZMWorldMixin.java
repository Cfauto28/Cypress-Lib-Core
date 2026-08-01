package net.cfauto.cypress_lib_core.mixin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import ext.client.InputHandler;
import ext.newblock.EnumVendingMachine;
import ext.world.freerun.HandlerSetCheckpointUnknownClass2;
import ext.world.zm.WorldZMSpawn;
import ext.world.zm.ZMWorld;
import net.cfauto.cypress_lib_core.util.VendingMachineEnum;
import net.minecraft.client.entity.mob.player.ClientPlayerEntity;
import net.minecraft.world.World;

@Mixin(ZMWorld.class)
public class ZMWorldMixin extends World {

	@Shadow
	public List enemySpawnLocations;

	public ZMWorldMixin(File dir, String saveName) {
		super(dir, saveName);
	}

	/**
	 * @author FMG793
	 * @reason
	 */
	@Overwrite
	public WorldZMSpawn method_1_882(boolean z1) {
		ArrayList arrayList2 = new ArrayList();
		ClientPlayerEntity clientPlayerEntity3 = InputHandler.minecraft.player;
		final HandlerSetCheckpointUnknownClass2 handlerSetCheckpointUnknownClass24 = new HandlerSetCheckpointUnknownClass2(clientPlayerEntity3.x, clientPlayerEntity3.y, clientPlayerEntity3.z);
		Iterator iterator5 = this.enemySpawnLocations.iterator();

		while(true) {
			WorldZMSpawn worldZMSpawn6;
			double d8;
			do {
				if(!iterator5.hasNext()) {
					Collections.sort(arrayList2, new Comparator() {
						public int compare(Object worldZMSpawn1, Object worldZMSpawn2) {
							HandlerSetCheckpointUnknownClass2 handlerSetCheckpointUnknownClass23 = ((WorldZMSpawn)worldZMSpawn1).location.method_1_1185();
							HandlerSetCheckpointUnknownClass2 handlerSetCheckpointUnknownClass24x = ((WorldZMSpawn)worldZMSpawn2).location.method_1_1185();
							return new Double(handlerSetCheckpointUnknownClass24.method_1_948(handlerSetCheckpointUnknownClass23.field_1_1065, handlerSetCheckpointUnknownClass23.field_1_1066)).compareTo(handlerSetCheckpointUnknownClass24.method_1_948(handlerSetCheckpointUnknownClass24x.field_1_1065, handlerSetCheckpointUnknownClass24x.field_1_1066));
						}
					});
					return arrayList2.size() == 0 ? null : (arrayList2.size() == 1 ? (WorldZMSpawn)arrayList2.get(0) : (WorldZMSpawn)arrayList2.get(this.random.nextInt(Math.min(arrayList2.size(), 3))));
				}

				worldZMSpawn6 = (WorldZMSpawn)iterator5.next();
				HandlerSetCheckpointUnknownClass2 handlerSetCheckpointUnknownClass27 = worldZMSpawn6.location.method_1_1185();
				d8 = handlerSetCheckpointUnknownClass24.method_1_948(handlerSetCheckpointUnknownClass27.field_1_1065, handlerSetCheckpointUnknownClass27.field_1_1066);
			} while(d8 >= 48.0D && !z1);

			arrayList2.add(worldZMSpawn6);
		}
	}

	@Overwrite
	public int method_1_875(EnumVendingMachine enumVendingMachine1) {
		VendingMachineEnum newEVM = VendingMachineEnum.origEVM(enumVendingMachine1);
		switch(newEVM) {
		case Armor:
			return 1500;
		case Dash:
			return 7500;
		case HealthBoost:
			return 2500;
		case QuickRevive:
			return 600;
		default:
			return 1;
		}
	}
}
