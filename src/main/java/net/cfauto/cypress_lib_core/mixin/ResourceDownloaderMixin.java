package net.cfauto.cypress_lib_core.mixin;

import net.minecraft.client.resource.ResourceDownloader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.cfauto.cypress_lib_core.ThreadDownloadResourcesFix;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

@Mixin(ResourceDownloader.class)
public class ResourceDownloaderMixin {
	/**
	 * @author Cfauto
	 * @reason Fix ExtSounds
	 */
	@Overwrite
	public void method_1_2275(Path path) throws IOException {
		ThreadDownloadResourcesFix threadDownloadResourcesUnknownClass1 = new ThreadDownloadResourcesFix() {
			public @NotNull FileVisitResult visitFile(@NotNull Path path, @NotNull BasicFileAttributes basicFileAttributes) throws IOException {
				Files.delete(path);
				return FileVisitResult.CONTINUE;
			}

			public @NotNull FileVisitResult visitFileFailed(@NotNull Path path, @NotNull IOException iOException) throws IOException {
				Files.delete(path);
				return FileVisitResult.CONTINUE;
			}

			public @NotNull FileVisitResult postVisitDirectory(@NotNull Path path, IOException iOException) throws IOException {
				if (iOException != null) {
					throw iOException;
				}

				Files.delete(path);
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(path, threadDownloadResourcesUnknownClass1);
	}
}
