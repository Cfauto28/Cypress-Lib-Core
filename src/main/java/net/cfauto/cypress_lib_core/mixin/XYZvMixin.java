package net.cfauto.cypress_lib_core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import ext.phys.Direction;
import ext.phys.XYZv;
import net.cfauto.cypress_lib_core.util.DirectionEnum;

@Mixin(XYZv.class)
public class XYZvMixin {

	@Shadow
	public long x;

	@Shadow
	public long y;

	@Shadow
	public long z;

	@Shadow
	public Direction dir;

	/**
	 * @author FMG793
	 * @reason
	 */
	@Overwrite
	public XYZv rotateAroundXP(Direction dir) {
		DirectionEnum newDE = DirectionEnum.origDE(dir);
		switch(newDE) {
		case XM:
			return new XYZv(-this.x, this.y, -this.z, this.dir);
		case XP:
			return new XYZv(this.x, this.y, this.z, this.dir);
		case ZM:
			return new XYZv(-this.z, this.y, -this.x, this.dir);
		case ZP:
			return new XYZv(this.z, this.y, this.x, this.dir);
		default:
			return null;
		}
	}
}
