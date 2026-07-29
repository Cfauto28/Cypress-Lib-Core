package net.cfauto.cypress_lib_core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class ThreadDownloadResourcesFix extends SimpleFileVisitor<Path> {
	protected ThreadDownloadResourcesFix() {
	}

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
		} else {
			Files.delete(path);
			return FileVisitResult.CONTINUE;
		}
	}
}

