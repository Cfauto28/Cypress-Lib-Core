package net.cfauto.cypress_lib_core.mixin;

import net.cfauto.cypress_lib_core.ThreadDownloadResourcesFix;
import net.minecraft.client.resource.ResourceDownloader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.nio.file.FileVisitor;
import java.nio.file.Path;

@Mixin(ResourceDownloader.class)
public class ResourceDownloaderMixin {
	@Unique
	ThreadDownloadResourcesFix threadDownloadResourcesUnknownClass1 = new ThreadDownloadResourcesFix();

	@ModifyArg(method = "method_1_2275", at = @At(value = "INVOKE", target = "Ljava/nio/file/Files;walkFileTree(Ljava/nio/file/Path;Ljava/nio/file/FileVisitor;)Ljava/nio/file/Path;"), index = 1)
	private FileVisitor<? super Path> mixin(FileVisitor<? super @NotNull Path> visitor) {
		return threadDownloadResourcesUnknownClass1;
	}
}
