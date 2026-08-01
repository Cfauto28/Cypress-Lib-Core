package net.cfauto.cypress_lib_core.mixin;

import net.minecraft.client.resource.ResourceDownloader;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Overwrite;
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
	public void method_1_2275(Path path1) throws IOException {
		SimpleFileVisitor simpleFileVisitor2 = new SimpleFileVisitor() {
			public @NotNull FileVisitResult postVisitDirectory(@NotNull Object filePath, IOException iOException2) throws IOException {
				if(iOException2 != null) {
					throw iOException2;
				} else {
					Files.delete(path1);
					return FileVisitResult.CONTINUE;
				}
			}

			public @NotNull FileVisitResult visitFileFailed(@NotNull Object filePath, @NotNull IOException iOException) throws IOException {
				Files.delete(path1);
				return FileVisitResult.CONTINUE;
			}

			public @NotNull FileVisitResult visitFile(@NotNull Object filePath, @NotNull BasicFileAttributes fileAttributes) throws IOException {
				Files.delete(path1);
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(path1, simpleFileVisitor2);
	}
}
