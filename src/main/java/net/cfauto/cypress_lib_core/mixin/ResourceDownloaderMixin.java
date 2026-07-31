package net.cfauto.cypress_lib_core.mixin;

import net.cfauto.cypress_lib_core.ThreadDownloadResourcesFix;
import net.minecraft.client.resource.ResourceDownloader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@Mixin(ResourceDownloader.class)
public class ResourceDownloaderMixin {

	/**
	 * @author Cfauto
	 * @reason Fix sounds and resource extraction
	 */
	@Overwrite
	public void method_1_2275(Path path) throws IOException {
		ThreadDownloadResourcesFix threadDownloadResourcesUnknownClass1 = new ThreadDownloadResourcesFix() {
			public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
				Files.delete(path);
				return FileVisitResult.CONTINUE;
			}

			public FileVisitResult visitFileFailed(Path path, IOException iOException) throws IOException {
				Files.delete(path);
				return FileVisitResult.CONTINUE;
			}

			public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
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
