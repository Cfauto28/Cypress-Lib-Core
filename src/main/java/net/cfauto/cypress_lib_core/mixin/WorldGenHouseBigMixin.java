package net.cfauto.cypress_lib_core.mixin;

import ext.block.ExtBlock;
import ext.newblock.ExtNewBlock;
import ext.world.gen.WorldGenHouseBig;
import ext.world.gen.WorldGenHouseBigInterior;
import net.minecraft.world.World;
import net.minecraft.world.gen.noise.ImprovedNoise;
import org.spongepowered.asm.mixin.*;


@Mixin(WorldGenHouseBig.class)
public class WorldGenHouseBigMixin {

	enum EnumHouseBig {
		None,
		Door,
		PinkGlass,
		BlueGlass,
		LimeGlass,
		BlackGlass,
		WhiteGlass,
		Doorless,
		PlateDecor;

		EnumHouseBig() {
		}
	}

	@Shadow
	@Final
	private ImprovedNoise noiseMap2;
	@Shadow
	private void method_1_1435(World world, int i, int j, int k, int l, int m, int n, ExtBlock extBlock, int o) {}
	@Shadow
	private void method_1_1432(World world, int i, int j, int k) {}

	@Unique
	private EnumHouseBig method_1_1434(int i, int j, int k, int l) {
		int i5 = i * 5436345 + j * -12416265;
		int j6 = k * 4256285 + l * -82344232;
		double d = this.noiseMap2.getValue(i5 / 634.0, j6 / 634.0);
		if (d < 0.0) {
			d = -d;
		}

		if (d > 1.0) {
			d %= 1.0;
		}

		if (d < 0.3) {
			return EnumHouseBig.Door;
		} else if (d < 0.4) {
			return EnumHouseBig.BlackGlass;
		} else if (d < 0.5) {
			return EnumHouseBig.WhiteGlass;
		} else if (d < 0.6) {
			return EnumHouseBig.BlueGlass;
		} else if (d < 0.7) {
			return EnumHouseBig.LimeGlass;
		} else if (d < 0.8) {
			return EnumHouseBig.PlateDecor;
		} else {
			return d < 0.9 ? EnumHouseBig.Doorless : EnumHouseBig.Door;
		}
	}
	@Unique
	private EnumHouseBig[] method_1_1437(int i, int j, int k) {
		return new EnumHouseBig[]{this.method_1_1434(i, j, k, 0), this.method_1_1434(i, j, k, 1), this.method_1_1434(i, j, k, 2), this.method_1_1434(i, j, k, 3)};
	}
	/**
	 * @author Cfauto
	 * @reason Make the game generate a world
	 */
	@Overwrite
	private void method_1_1429(World world, int i, int j, int k, WorldGenHouseBigInterior worldGenHouseBigInterior) {
		if (!worldGenHouseBigInterior.method_1_1805(WorldGenHouseBig.class, i, k)) {
		}

		int i6 = i * 16;
		int j7 = k * 16;

		for (int k8 = 7; k8 > 0; k8--) {
			world.setBlockQuietly(i6 + 2, j + k8, j7 + 2, ExtNewBlock.SLATE_PILLAR.id);
			world.setBlockQuietly(i6 + 2, j + k8, j7 + 13, ExtNewBlock.SLATE_PILLAR.id);
			world.setBlockQuietly(i6 + 13, j + k8, j7 + 2, ExtNewBlock.SLATE_PILLAR.id);
			world.setBlockQuietly(i6 + 13, j + k8, j7 + 13, ExtNewBlock.SLATE_PILLAR.id);
			if (k8 != 7) {
				for (int l = 3; l <= 12; l++) {
					int p = l >= 6 && l <= 9 ? 1 : 0;
					if (p != 0 || k8 < 5) {
						world.setBlockQuietly(i6 + l, j + k8, j7 + 3 + p, ExtNewBlock.SLATE_BRICKS.id);
						world.setBlockQuietly(i6 + l, j + k8, j7 + 12 - p, ExtNewBlock.SLATE_BRICKS.id);
					}
				}

				for (int var20 = 4; var20 <= 11; var20++) {
					int q = var20 >= 6 && var20 <= 9 ? 1 : 0;
					if (q != 0 || k8 < 5) {
						world.setBlockQuietly(i6 + 3 + q, j + k8, j7 + var20, ExtNewBlock.SLATE_BRICKS.id);
						world.setBlockQuietly(i6 + 12 - q, j + k8, j7 + var20, ExtNewBlock.SLATE_BRICKS.id);
					}
				}
			}
		}

		for (int var13 = 2; var13 <= 13; var13++) {
			for (int m = 2; m <= 13; m++) {
				world.setBlockQuietly(i6 + var13, j, j7 + m, ExtNewBlock.SMOOTH_STONE.id);
			}
		}

		for (int var14 = 1; var14 < 15; var14++) {
			for (int n = 7; n <= 8; n++) {
				world.setBlockQuietly(i6 + var14, j + 7, j7 + n, ExtNewBlock.FLAMEWOOD_PILLAR.id);
				world.setBlockQuietly(i6 + n, j + 7, j7 + var14, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			}
		}

		for (int var15 = 5; var15 <= 10; var15++) {
			for (int o = 5; o <= 10; o++) {
				world.setBlockQuietly(i6 + var15, j + 7, j7 + o, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			}
		}

		for (int var16 = 0; var16 < 4; var16++) {
			world.setBlockQuietly(i6 + 4 - var16, j + 6, j7 + 6, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 4 - var16, j + 6, j7 + 9, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 11 + var16, j + 6, j7 + 6, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 11 + var16, j + 6, j7 + 9, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 6, j + 6, j7 + 4 - var16, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 9, j + 6, j7 + 4 - var16, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 6, j + 6, j7 + 11 + var16, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 9, j + 6, j7 + 11 + var16, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		}

		world.setBlockQuietly(i6 + 4, j + 6, j7 + 4, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 4, j + 6, j7 + 5, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 5, j + 6, j7 + 4, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 11, j + 6, j7 + 11, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 10, j + 6, j7 + 11, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 11, j + 6, j7 + 10, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 11, j + 6, j7 + 4, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 10, j + 6, j7 + 4, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 11, j + 6, j7 + 5, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 4, j + 6, j7 + 11, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 5, j + 6, j7 + 11, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		world.setBlockQuietly(i6 + 4, j + 6, j7 + 10, ExtNewBlock.FLAMEWOOD_PILLAR.id);

		for (int var17 = 0; var17 < 3; var17++) {
			world.setBlockQuietly(i6 + 3 - var17, j + 5, j7 + 5, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3 - var17, j + 5, j7 + 10, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12 + var17, j + 5, j7 + 5, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12 + var17, j + 5, j7 + 10, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 5, j + 5, j7 + 3 - var17, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 10, j + 5, j7 + 3 - var17, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 5, j + 5, j7 + 12 + var17, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 10, j + 5, j7 + 12 + var17, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		}

		for (int var18 = 0; var18 < 2; var18++) {
			world.setBlockQuietly(i6 + 4, j + 5, j7 + 3 - var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3, j + 5, j7 + 3 - var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3 - var18, j + 5, j7 + 4, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3 - var18, j + 5, j7 + 3, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 11, j + 5, j7 + 12 + var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12, j + 5, j7 + 12 + var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12 + var18, j + 5, j7 + 11, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12 + var18, j + 5, j7 + 12, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 4, j + 5, j7 + 12 + var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3, j + 5, j7 + 12 + var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3 - var18, j + 5, j7 + 11, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 3 - var18, j + 5, j7 + 12, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 11, j + 5, j7 + 3 - var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12, j + 5, j7 + 3 - var18, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12 + var18, j + 5, j7 + 4, ExtNewBlock.FLAMEWOOD_PILLAR.id);
			world.setBlockQuietly(i6 + 12 + var18, j + 5, j7 + 3, ExtNewBlock.FLAMEWOOD_PILLAR.id);
		}

		int var19 = j / 8;
		EnumHouseBig[] enumHouseBigs = this.method_1_1437(i, var19, k);

		for (int r = 0; r < 4; r++) {
			switch (enumHouseBigs[r]) {
				case WhiteGlass:
					this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtBlock.GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 0, ExtBlock.GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 0, ExtBlock.GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtBlock.GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 1, ExtBlock.GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 1, ExtBlock.GLASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, -1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 0, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 2, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 0, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 3, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					break;
				case BlueGlass:
					this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtNewBlock.BLUE_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 0, ExtNewBlock.BLUE_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 0, ExtNewBlock.BLUE_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtNewBlock.BLUE_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 1, ExtNewBlock.BLUE_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 1, ExtNewBlock.BLUE_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, -1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 0, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 2, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 0, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 3, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					break;
				case PinkGlass:
					this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtNewBlock.MAGENTA_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 0, ExtNewBlock.MAGENTA_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 0, ExtNewBlock.MAGENTA_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtNewBlock.MAGENTA_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 1, ExtNewBlock.MAGENTA_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 1, ExtNewBlock.MAGENTA_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, -1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 0, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 2, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 0, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 3, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					break;
				case PlateDecor:
					this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtNewBlock.SMOOTH_STONE, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 0, ExtNewBlock.SMOOTH_STONE, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 0, ExtNewBlock.SMOOTH_STONE, r);
					this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtNewBlock.SMOOTH_STONE, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 1, ExtNewBlock.SMOOTH_STONE, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 1, ExtNewBlock.SMOOTH_STONE, r);
					this.method_1_1435(world, i6, j, j7, 1, 1, 0, ExtNewBlock.SOLAR_PLATE, r);
					this.method_1_1435(world, i6, j, j7, 1, 2, 0, ExtNewBlock.DENIAL_PLATE, r);
					this.method_1_1435(world, i6, j, j7, 1, 3, 0, ExtNewBlock.SWITCH_PLATE, r);
					this.method_1_1435(world, i6, j, j7, 1, 1, 1, ExtNewBlock.LOOP_PLATE, r);
					this.method_1_1435(world, i6, j, j7, 1, 2, 1, ExtNewBlock.PART_PLATE, r);
					this.method_1_1435(world, i6, j, j7, 1, 3, 1, ExtNewBlock.ASSOCIATION_PLATE, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, -1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 0, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 2, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 0, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 3, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					break;
				case LimeGlass:
					this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtNewBlock.GREEN_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 0, ExtNewBlock.GREEN_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 0, ExtNewBlock.GREEN_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtNewBlock.GREEN_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 1, ExtNewBlock.GREEN_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 1, ExtNewBlock.GREEN_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, -1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 0, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 2, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 0, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 3, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					break;
				case BlackGlass:
					this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtNewBlock.BLACK_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 0, ExtNewBlock.BLACK_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 0, ExtNewBlock.BLACK_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtNewBlock.BLACK_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 2, 1, ExtNewBlock.BLACK_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 0, 3, 1, ExtNewBlock.BLACK_GLASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, -1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 0, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 1, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 1, 0, 2, ExtBlock.GRASS, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, -1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 0, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 1, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 2, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 0, 3, ExtNewBlock.SLATE_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, 3, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 1, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 2, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					this.method_1_1435(world, i6, j, j7, 2, 3, -2, ExtNewBlock.FLAMEWOOD_PILLAR, r);
					break;
				case Door:
					int s = r == 0 ? 1 : (r == 2 ? -1 : 0);
					int u = r == 1 ? 1 : (r == 3 ? -1 : 0);
					s += i;
					u += k;
					if (this.method_1_1434(s, var19, u, (r + 2) % 4) == EnumHouseBig.Door && worldGenHouseBigInterior.method_1_1808(s, u) instanceof WorldGenHouseBig) {
						this.method_1_1435(world, i6, j, j7, 0, 0, 0, ExtNewBlock.WIREFRAME_BLOCK, r);
						this.method_1_1435(world, i6, j, j7, 0, 0, 1, ExtNewBlock.WIREFRAME_BLOCK, r);
						this.method_1_1435(world, i6, j, j7, 0, 1, 0, ExtNewBlock.WIREFRAME_BLOCK, r);
						this.method_1_1435(world, i6, j, j7, 0, 1, 1, ExtNewBlock.WIREFRAME_BLOCK, r);
						this.method_1_1435(world, i6, j, j7, 3, -1, 0, ExtNewBlock.SMOOTH_STONE, r);
						this.method_1_1435(world, i6, j, j7, 3, -1, 1, ExtNewBlock.SMOOTH_STONE, r);
						this.method_1_1435(world, i6, j, j7, 4, -1, 0, ExtNewBlock.SMOOTH_STONE, r);
						this.method_1_1435(world, i6, j, j7, 4, -1, 1, ExtNewBlock.SMOOTH_STONE, r);
					}
				case Doorless:
					int t = r == 0 ? 1 : (r == 2 ? -1 : 0);
					int v = r == 1 ? 1 : (r == 3 ? -1 : 0);
					t += i;
					v += k;
					if (this.method_1_1434(t, var19, v, (r + 2) % 4) == EnumHouseBig.Door && worldGenHouseBigInterior.method_1_1808(t, v) instanceof WorldGenHouseBig) {
						this.method_1_1435(world, i6, j, j7, 3, -1, 0, ExtNewBlock.SMOOTH_STONE, r);
						this.method_1_1435(world, i6, j, j7, 3, -1, 1, ExtNewBlock.SMOOTH_STONE, r);
						this.method_1_1435(world, i6, j, j7, 4, -1, 0, ExtNewBlock.SMOOTH_STONE, r);
						this.method_1_1435(world, i6, j, j7, 4, -1, 1, ExtNewBlock.SMOOTH_STONE, r);
					}
			}
		}

		this.method_1_1432(world, i6 + 4, j + 1, j7 + 4);
		this.method_1_1432(world, i6 + 11, j + 1, j7 + 4);
		this.method_1_1432(world, i6 + 4, j + 1, j7 + 11);
		this.method_1_1432(world, i6 + 11, j + 1, j7 + 11);
	}
}
