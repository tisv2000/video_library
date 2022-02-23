package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

import static com.github.tisv2000.video_library.util.UrlPath.IMAGES;

@WebServlet(IMAGES + "/*")
public class ImageServlet extends HttpServlet {

    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestUri = req.getRequestURI();
        var imagePath = requestUri.replace(IMAGES, "");

        imageService.get(imagePath).ifPresentOrElse(
                image -> {
                    resp.setContentType("application/octet-stream");
                    writeImage(image, resp);
                }, () -> resp.setStatus(404)
        );
    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try(image; var outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}
