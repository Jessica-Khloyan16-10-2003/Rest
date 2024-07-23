package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.Interfaces.OptionalClassService;
import org.example.service.impl.OptionalClassServiceImpl;
import org.example.servlet.dto.OptionalClassIncomingDto;
import org.example.servlet.dto.OptionalClassUpdateDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@WebServlet(urlPatterns = {"/optionalClass/*"})
public class OptionalClassServlet extends HttpServlet {
    private final transient OptionalClassService optionalClassService = OptionalClassServiceImpl.getInstance();
    private final ObjectMapper objectMapper;

    public OptionalClassServlet() {
        this.objectMapper = new ObjectMapper();
    }

    private static void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private static String getJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader postData = req.getReader();
        String line;
        while ((line = postData.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = null;
        Optional<OptionalClassIncomingDto> optionalClassResponse;
        try {
            optionalClassResponse = Optional.ofNullable(objectMapper.readValue(json, OptionalClassIncomingDto.class));
            OptionalClassIncomingDto department = optionalClassResponse.orElseThrow(IllegalArgumentException::new);
            responseAnswer = objectMapper.writeValueAsString(optionalClassService.save(department));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect department Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
