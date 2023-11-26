import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bsuir.by.nastassia.yankova.nfilm.commands.implementation.AuthorizationCommand;
import com.bsuir.by.nastassia.yankova.nfilm.commands.implementation.LogoutCommand;
import com.bsuir.by.nastassia.yankova.nfilm.commands.implementation.RegistrationCommand;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.AuthorizationService;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
public class AuthorizationTest {
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpSession session;
	
	@Mock
	AuthorizationService service;
	
	@Mock
	User user;

	@Test
	public void processRegistration() {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("password")).thenReturn("01");
		when(request.getParameter("login")).thenReturn("test_user");
		
		RegistrationCommand commandTest = new RegistrationCommand();
		try {
			String result = commandTest.execute(request);
			assertEquals(result, PageURLMapper.REGISTER);
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void processSignin() {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("password")).thenReturn("001");
		when(request.getParameter("login")).thenReturn("test_user");
		
		AuthorizationCommand commandTest = new AuthorizationCommand();
		try {
			String result = commandTest.execute(request);
			assertEquals(result, PageURLMapper.AUTHENTICATE);
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void processLogout() {
		when(request.getSession()).thenReturn(session);
		try {
			LogoutCommand logout = new LogoutCommand();
			String result = logout.execute(request);
			assertEquals(result, PageURLMapper.INDEX);
		} catch (CommandException e) {}
		verify(session, times(1)).setAttribute("user", null);
	}
		
}
