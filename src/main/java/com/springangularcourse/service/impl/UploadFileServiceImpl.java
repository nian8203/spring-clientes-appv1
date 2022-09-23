package com.springangularcourse.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.springangularcourse.service.IUploadFileService;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String DIRECTORIO_PLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {

		Path rutaArchivo = getPath(nombreFoto);
		log.info(rutaArchivo.toString());

		Resource resource = new UrlResource(rutaArchivo.toUri());

		if (!resource.exists() && !resource.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/img").resolve("no-user.png").toAbsolutePath();

			resource = new UrlResource(rutaArchivo.toUri());

			log.error("No se ha podido cargar la imagen " + nombreFoto);
		}
		return resource;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		
		String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo);
		log.info(rutaArchivo.toString());

		Files.copy(archivo.getInputStream(), rutaArchivo);

		return nombreArchivo;
	}

	@Override
	public Boolean eliminar(String nombreFoto) {
		
		if (nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
			File archivoFotoAnteriror = rutaFotoAnterior.toFile();

			if (archivoFotoAnteriror.exists() && archivoFotoAnteriror.canRead()) {
				archivoFotoAnteriror.delete();
				return true;
			}
		}

		return false;
	}

	@Override
	public Path getPath(String foto) {
		// TODO Auto-generated method stub
		return Paths.get(DIRECTORIO_PLOAD).resolve(foto).toAbsolutePath();		
	}

}
