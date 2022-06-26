package pl.teob.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = Main.class)
//@AllArgsConstructor
//@ExtendWith(SeleniumJupiter.class)
@EnableAutoConfiguration
@EnableGlobalMethodSecurity
public class UserRepositoryTest {

    @Test
    public void whenUserIdIsProvided_thenRetrievedUser() {
        UserRepository userRepository = mock(UserRepository.class);

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

        when(userRepository.findById(anyLong())).thenReturn(ofResult);

        Optional<User> userMock = userRepository.findById(1234L);

        assertNotNull(userMock);
        assertEquals(user.getUsername(), userMock.get().getUsername());
    }

    @Test
    public void whenUserUsernameIsProvided_thenRetrievedUser() {
        UserRepository userRepository = mock(UserRepository.class);

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

        when(userRepository.findByUsername(anyString())).thenReturn(ofResult);

        Optional<User> userMock = userRepository.findByUsername("Marek");

        assertNotNull(userMock);
        assertEquals(user.getUsername(), userMock.get().getUsername());
    }

    @Test
    public void whenUserEmailIsProvided_thenRetrievedUser() {
        UserRepository userRepository = mock(UserRepository.class);

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

        when(userRepository.findByEmail(anyString())).thenReturn(ofResult);

        Optional<User> userMock = userRepository.findByEmail("patryk@o2.pl");

        assertNotNull(userMock);
        assertEquals(user.getUsername(), userMock.get().getUsername());
    }
}