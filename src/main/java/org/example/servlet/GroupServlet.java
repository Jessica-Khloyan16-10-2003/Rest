package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.Interfaces.GroupService;
import org.example.service.impl.GroupServiceImpl;
import org.example.servlet.dto.GroupIncomingDto;
import org.example.servlet.dto.GroupUpdateDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@WebServlet(urlPatterns = {"/group/*"})
public class GroupServlet extends HttpServlet {
    private final transient GroupService groupService;
    private final ObjectMapper objectMapper;

    public GroupServlet() {
        this.groupService = GroupServiceImpl.getInstance();
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String responseAnswer = "";
        try {
            String[] pathPart = req.getPathInfo().split("/");
            Long groupId = Long.parseLong(pathPart[1]);
            if (groupService.delete(groupId)) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Bad request.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = null;
        Optional<GroupIncomingDto> groupIncomingDto;
        try {
            groupIncomingDto = Optional.ofNullable(objectMapper.readValue(json, GroupIncomingDto.class));
            GroupIncomingDto group = groupIncomingDto.orElseThrow(IllegalArgumentException::new);
            responseAnswer = objectMapper.writeValueAsString(groupService.save(group));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect role Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setJsonHeader(resp);
        String json = getJson(req);

        String responseAnswer = "";
        Optional<GroupUpdateDto> groupResponse;
        try {
            groupResponse = Optional.ofNullable(objectMapper.readValue(json, GroupUpdateDto.class));
            GroupUpdateDto groupUpdateDto = groupResponse.orElseThrow(IllegalArgumentException::new);
            groupService.update(groupUpdateDto);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseAnswer = "Incorrect role Object.";
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseAnswer);
        printWriter.flush();
    }
}
