package net.cfauto.cypress_lib_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.crafting.CraftingManagerComparator;
import net.minecraft.crafting.Recipe;

@Mixin(CraftingManagerComparator.class)
public abstract class CraftingManagerComparatorMixin {
	@Shadow
	public abstract int compareImpl(Recipe recipe1, Recipe recipe2);

	/**
	 * @author FMG793
	 * @reason
	 */
	@Overwrite
	public int compare(Recipe object1, Recipe object2) {
		return this.compareImpl(object1, object2);
	}

}
