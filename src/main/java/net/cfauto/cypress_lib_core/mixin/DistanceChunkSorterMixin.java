package net.cfauto.cypress_lib_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.render.world.DistanceChunkSorter;
import net.minecraft.client.render.world.RenderChunk;

@Mixin(DistanceChunkSorter.class)
public abstract class DistanceChunkSorterMixin {

	@Shadow
	public abstract int compareImpl(RenderChunk chunk1, RenderChunk chunk2);

	/**
	 * @author FMG793
	 * @reason
	 */
	@Overwrite
	public int compare(Object object1, Object object2) {
		return this.compareImpl((RenderChunk)object1, (RenderChunk)object2);
	}
}
