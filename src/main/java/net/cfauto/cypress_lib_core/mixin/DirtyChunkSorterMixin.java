package net.cfauto.cypress_lib_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.render.world.DirtyChunkSorter;
import net.minecraft.client.render.world.RenderChunk;

@Mixin(DirtyChunkSorter.class)
public abstract class DirtyChunkSorterMixin {
	@Shadow
	public abstract int compareImpl(RenderChunk chunk1, RenderChunk chunk2);

	/**
	 * @author FMG793
	 * @reason
	 */
	@Overwrite
	public int compare(RenderChunk object1, RenderChunk object2) {
		return this.compareImpl(object1, object2);
	}
}
