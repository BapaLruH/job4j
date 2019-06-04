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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class RoleServletTest {

    @Test
    public void whenAddUpdateDeleteRole() throws ServletException, IOException {
        Service service = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(service);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("action")).thenReturn("add");
        when(req.getParameter("name")).thenReturn("Admin");
        RoleServlet rs = new RoleServlet();
        rs.doPost(req, resp);
        assertThat(service.findAllRoles().iterator().next().getName(), is("Admin"));
        when(req.getParameter("action")).thenReturn("update");
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("New name");
        rs.doPost(req, resp);
        assertThat(service.findRoleById(0).getName(), is("New name"));
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameter("id")).thenReturn("0");
        rs.doPost(req, resp);
        assertTrue(service.findAllRoles().isEmpty());
    }
}