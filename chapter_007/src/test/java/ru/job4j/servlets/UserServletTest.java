package ru.job4j.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;
import ru.job4j.service.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletTest {

    @Test(expected = UnsupportedOperationException.class)
    public void whenExecuteDoPostWithoutNecessaryParametersThenException() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        new UserServlet().doPost(req, resp);
    }

    @Test
    public void whenAddUpdateDeleteUser() throws ServletException, IOException {
        Service service = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(service);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("action")).thenReturn("add");
        Map<String, String[]> users = new HashMap<>();
        users.put("name", new String[]{"Admin"});
        users.put("login", new String[]{"Admin"});
        users.put("email", new String[]{"Admin"});
        users.put("password", new String[]{"Admin"});
        users.put("1", new String[]{"Admin"});
        when(req.getParameterMap()).thenReturn(users);
        UserServlet us = new UserServlet();
        us.doPost(req, resp);
        assertThat(service.findAll().iterator().next().getName(), is("Admin"));
        when(req.getParameter("action")).thenReturn("update");
        when(req.getParameter("id")).thenReturn("0");
        users.put("name", new String[]{"First Admin"});
        when(req.getParameterMap()).thenReturn(users);
        us.doPost(req, resp);
        assertThat(service.findAll().iterator().next().getName(), is("First Admin"));
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameter("id")).thenReturn("0");
        us.doPost(req, resp);
        assertTrue(service.findAll().isEmpty());
    }

}