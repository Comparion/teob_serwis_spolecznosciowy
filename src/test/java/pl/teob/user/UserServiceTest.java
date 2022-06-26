package pl.teob.user;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.teob.comment.CommentRepository;
import pl.teob.detail.UserDetail;
import pl.teob.detail.UserDetailRepository;
import pl.teob.interest.InterestRepository;
import pl.teob.post.Post;
import pl.teob.post.PostRepository;
import pl.teob.user.token.ConfirmationToken;
import pl.teob.user.token.ConfirmationTokenRepository;
import pl.teob.user.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    /**
     * Method under test: {@link UserService#addUser(User)}
     */

    @Test
    void testAddUser() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        Optional<User> ofResult1 = Optional.of(user2);
        UserRepository userRepository = mock(UserRepository.class);


        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult1);
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));


        User user3 = new User();
        user3.setAppUserRole(AppUserRole.USER);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(123L);
        user3.setLocked(true);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");

        ResponseEntity actualAddUserResult = userService.addUser(user3);

        assertNull(actualAddUserResult.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualAddUserResult.getStatusCode());
        Assertions.assertTrue(actualAddUserResult.getHeaders().isEmpty());
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).findByUsername((String) any());

    }

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser2() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        when(userRepository.findByUsername((String) any())).thenReturn(Optional.empty());
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        ResponseEntity actualAddUserResult = userService.addUser(user2);
        assertNull(actualAddUserResult.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualAddUserResult.getStatusCode());
        assertTrue(actualAddUserResult.getHeaders().isEmpty());
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser3() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenThrow(new UsernameNotFoundException("U@U.UUUU"));
        when(userRepository.findByEmail((String) any())).thenThrow(new UsernameNotFoundException("U@U.UUUU"));
        when(userRepository.findByUsername((String) any())).thenThrow(new UsernameNotFoundException("U@U.UUUU"));
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> userService.addUser(user));
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#validate(String)}
     */
    @Test
    void testValidate() {
        assertTrue(UserService.validate("jane.doe@example.org"));
        assertFalse(UserService.validate("Email Str"));
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user1);
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        UserDetail userDetail = new UserDetail();
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user2);
        Optional<UserDetail> ofResult2 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult2);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ResponseEntity actualDeleteUserResult = (new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo");
        assertNull(actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(userRepository).findByUsername((String) any());
        verify(userRepository).deleteById((Long) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(confirmationTokenRepository).deleteById((Long) any());
        verify(userDetailRepository).findByUserId(anyLong());
        verify(userDetailRepository).deleteById((Long) any());
        verify(postRepository).findAllByUserId(anyLong());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser2() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user1);
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        UserDetail userDetail = new UserDetail();
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user2);
        Optional<UserDetail> ofResult2 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult2);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByUserId(anyLong())).thenThrow(new IllegalStateException("foo"));
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository, userDetailRepository,
                        postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo"));
        verify(userRepository).findByUsername((String) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(confirmationTokenRepository).deleteById((Long) any());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser3() {
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(Optional.empty());

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user);
        Optional<ConfirmationToken> ofResult = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        UserDetail userDetail = new UserDetail();
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user1);
        Optional<UserDetail> ofResult1 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult1);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ResponseEntity actualDeleteUserResult = (new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo");
        assertNull(actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser4() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        UserDetail userDetail = new UserDetail();
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user1);
        Optional<UserDetail> ofResult1 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult1);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ResponseEntity actualDeleteUserResult = (new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo");
        assertNull(actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(userRepository).findByUsername((String) any());
        verify(userRepository).deleteById((Long) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(userDetailRepository).findByUserId(anyLong());
        verify(userDetailRepository).deleteById((Long) any());
        verify(postRepository).findAllByUserId(anyLong());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser5() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user1);
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        UserDetail userDetail = mock(UserDetail.class);
        when(userDetail.getId()).thenReturn(123L);
        doNothing().when(userDetail).setDescription((String) any());
        doNothing().when(userDetail).setFirstName((String) any());
        doNothing().when(userDetail).setId(anyLong());
        doNothing().when(userDetail).setInterests((String) any());
        doNothing().when(userDetail).setNumberPhone((String) any());
        doNothing().when(userDetail).setSecondName((String) any());
        doNothing().when(userDetail).setUser((User) any());
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user2);
        Optional<UserDetail> ofResult2 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult2);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ResponseEntity actualDeleteUserResult = (new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo");
        assertNull(actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(userRepository).findByUsername((String) any());
        verify(userRepository).deleteById((Long) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(confirmationTokenRepository).deleteById((Long) any());
        verify(userDetailRepository).findByUserId(anyLong());
        verify(userDetailRepository).deleteById((Long) any());
        verify(userDetail).getId();
        verify(userDetail).setDescription((String) any());
        verify(userDetail).setFirstName((String) any());
        verify(userDetail).setId(anyLong());
        verify(userDetail).setInterests((String) any());
        verify(userDetail).setNumberPhone((String) any());
        verify(userDetail).setSecondName((String) any());
        verify(userDetail).setUser((User) any());
        verify(postRepository).findAllByUserId(anyLong());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser6() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        UserDetail userDetail = mock(UserDetail.class);
        when(userDetail.getId()).thenReturn(123L);
        doNothing().when(userDetail).setDescription((String) any());
        doNothing().when(userDetail).setFirstName((String) any());
        doNothing().when(userDetail).setId(anyLong());
        doNothing().when(userDetail).setInterests((String) any());
        doNothing().when(userDetail).setNumberPhone((String) any());
        doNothing().when(userDetail).setSecondName((String) any());
        doNothing().when(userDetail).setUser((User) any());
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user2);
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult1);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(Optional.empty());
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ResponseEntity actualDeleteUserResult = (new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo");
        assertNull(actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(userRepository).findByUsername((String) any());
        verify(userRepository).deleteById((Long) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(confirmationTokenRepository).deleteById((Long) any());
        verify(userDetailRepository).findByUserId(anyLong());
        verify(userDetail).setDescription((String) any());
        verify(userDetail).setFirstName((String) any());
        verify(userDetail).setId(anyLong());
        verify(userDetail).setInterests((String) any());
        verify(userDetail).setNumberPhone((String) any());
        verify(userDetail).setSecondName((String) any());
        verify(userDetail).setUser((User) any());
        verify(postRepository).findAllByUserId(anyLong());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser7() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user1);
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        UserDetail userDetail = mock(UserDetail.class);
        when(userDetail.getId()).thenReturn(123L);
        doNothing().when(userDetail).setDescription((String) any());
        doNothing().when(userDetail).setFirstName((String) any());
        doNothing().when(userDetail).setId(anyLong());
        doNothing().when(userDetail).setInterests((String) any());
        doNothing().when(userDetail).setNumberPhone((String) any());
        doNothing().when(userDetail).setSecondName((String) any());
        doNothing().when(userDetail).setUser((User) any());
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user2);
        Optional<UserDetail> ofResult2 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult2);

        User user3 = new User();
        user3.setAppUserRole(AppUserRole.USER);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(123L);
        user3.setLocked(true);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setId(123L);
        post.setSubject("Hello from the Dreaming Spires");
        post.setTown("Oxford");
        post.setUser(user3);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        PostRepository postRepository = mock(PostRepository.class);
        doNothing().when(postRepository).deleteById((Long) any());
        when(postRepository.findAllByUserId(anyLong())).thenReturn(postList);
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ResponseEntity actualDeleteUserResult = (new UserService(userRepository, objectMapper, confirmationTokenRepository,
                userDetailRepository, postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo");
        assertNull(actualDeleteUserResult.getBody());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(userRepository).findByUsername((String) any());
        verify(userRepository).deleteById((Long) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(confirmationTokenRepository).deleteById((Long) any());
        verify(userDetailRepository).findByUserId(anyLong());
        verify(userDetailRepository).deleteById((Long) any());
        verify(userDetail).getId();
        verify(userDetail).setDescription((String) any());
        verify(userDetail).setFirstName((String) any());
        verify(userDetail).setId(anyLong());
        verify(userDetail).setInterests((String) any());
        verify(userDetail).setNumberPhone((String) any());
        verify(userDetail).setSecondName((String) any());
        verify(userDetail).setUser((User) any());
        verify(postRepository).findAllByUserId(anyLong());
        verify(postRepository).deleteById((Long) any());
        verify(interestRepository).findAllByPostId(anyLong());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByPostId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser8() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).deleteById((Long) any());
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user1);
        Optional<ConfirmationToken> ofResult1 = Optional.of(confirmationToken);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        doNothing().when(confirmationTokenRepository).deleteById((Long) any());
        when(confirmationTokenRepository.findByUserId(anyLong())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        UserDetail userDetail = mock(UserDetail.class);
        when(userDetail.getId()).thenReturn(123L);
        doNothing().when(userDetail).setDescription((String) any());
        doNothing().when(userDetail).setFirstName((String) any());
        doNothing().when(userDetail).setId(anyLong());
        doNothing().when(userDetail).setInterests((String) any());
        doNothing().when(userDetail).setNumberPhone((String) any());
        doNothing().when(userDetail).setSecondName((String) any());
        doNothing().when(userDetail).setUser((User) any());
        userDetail.setDescription("The characteristics of someone or something");
        userDetail.setFirstName("Jane");
        userDetail.setId(123L);
        userDetail.setInterests("Interests");
        userDetail.setNumberPhone("4105551212");
        userDetail.setSecondName("Second Name");
        userDetail.setUser(user2);
        Optional<UserDetail> ofResult2 = Optional.of(userDetail);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        doNothing().when(userDetailRepository).deleteById((Long) any());
        when(userDetailRepository.findByUserId(anyLong())).thenReturn(ofResult2);

        User user3 = new User();
        user3.setAppUserRole(AppUserRole.USER);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(123L);
        user3.setLocked(true);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setId(123L);
        post.setSubject("Hello from the Dreaming Spires");
        post.setTown("Oxford");
        post.setUser(user3);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        PostRepository postRepository = mock(PostRepository.class);
        doNothing().when(postRepository).deleteById((Long) any());
        when(postRepository.findAllByUserId(anyLong())).thenReturn(postList);
        InterestRepository interestRepository = mock(InterestRepository.class);
        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
        when(interestRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        CommentRepository commentRepository = mock(CommentRepository.class);
        when(commentRepository.findAllByPostId(anyLong())).thenThrow(new IllegalStateException("foo"));
        when(commentRepository.findAllByUserId(anyLong())).thenReturn(new ArrayList<>());
        ObjectMapper objectMapper = new ObjectMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository, userDetailRepository,
                        postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("foo"));
        verify(userRepository).findByUsername((String) any());
        verify(confirmationTokenRepository).findByUserId(anyLong());
        verify(confirmationTokenRepository).deleteById((Long) any());
        verify(userDetail).setDescription((String) any());
        verify(userDetail).setFirstName((String) any());
        verify(userDetail).setId(anyLong());
        verify(userDetail).setInterests((String) any());
        verify(userDetail).setNumberPhone((String) any());
        verify(userDetail).setSecondName((String) any());
        verify(userDetail).setUser((User) any());
        verify(postRepository).findAllByUserId(anyLong());
        verify(interestRepository).findAllByPostId(anyLong());
        verify(interestRepository).findAllByUserId(anyLong());
        verify(commentRepository).findAllByPostId(anyLong());
        verify(commentRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link UserService#deleteUser(String)}
     */
    @Test
    void testDeleteUser9() {
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsername((String) any())).thenThrow(new IllegalStateException("foo"));
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository, userDetailRepository,
                        postRepository, interestRepository, commentRepository, bCryptPasswordEncoder,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)))).deleteUser("janedoe"));
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#confirmToken(String)}
     */
    @Test
    void testConfirmToken() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        when(confirmationTokenRepository.findByToken((String) any())).thenReturn(Optional.of(confirmationToken));
        ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        UserRepository userRepository = mock(UserRepository.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository1 = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository1, userDetailRepository,
                        postRepository, interestRepository, commentRepository, new BCryptPasswordEncoder(),
                        confirmationTokenService)).confirmToken("foo"));
        verify(confirmationTokenRepository).findByToken((String) any());
    }

    /**
     * Method under test: {@link UserService#confirmToken(String)}
     */
    @Test
    void testConfirmToken2() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        ConfirmationToken confirmationToken = mock(ConfirmationToken.class);
        when(confirmationToken.getConfirmedAt()).thenReturn(null);
        when(confirmationToken.getExpiredAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(confirmationToken).setConfirmedAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setCreatedAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setExpiredAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setId(anyLong());
        doNothing().when(confirmationToken).setToken((String) any());
        doNothing().when(confirmationToken).setUser((User) any());
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        when(confirmationTokenRepository.findByToken((String) any())).thenReturn(Optional.of(confirmationToken));
        ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        UserRepository userRepository = mock(UserRepository.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository1 = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository1, userDetailRepository,
                        postRepository, interestRepository, commentRepository, new BCryptPasswordEncoder(),
                        confirmationTokenService)).confirmToken("foo"));
        verify(confirmationTokenRepository).findByToken((String) any());
        verify(confirmationToken).getConfirmedAt();
        verify(confirmationToken).getExpiredAt();
        verify(confirmationToken).setConfirmedAt((LocalDateTime) any());
        verify(confirmationToken).setCreatedAt((LocalDateTime) any());
        verify(confirmationToken).setExpiredAt((LocalDateTime) any());
        verify(confirmationToken).setId(anyLong());
        verify(confirmationToken).setToken((String) any());
        verify(confirmationToken).setUser((User) any());
    }

    /**
     * Method under test: {@link UserService#confirmToken(String)}
     */
    @Test
    void testConfirmToken3() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        ConfirmationToken confirmationToken = mock(ConfirmationToken.class);
        when(confirmationToken.getConfirmedAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(confirmationToken.getExpiredAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(confirmationToken).setConfirmedAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setCreatedAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setExpiredAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setId(anyLong());
        doNothing().when(confirmationToken).setToken((String) any());
        doNothing().when(confirmationToken).setUser((User) any());
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user);
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        when(confirmationTokenRepository.findByToken((String) any())).thenReturn(Optional.empty());
        ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
        UserRepository userRepository = mock(UserRepository.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository1 = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository1, userDetailRepository,
                        postRepository, interestRepository, commentRepository, new BCryptPasswordEncoder(),
                        confirmationTokenService)).confirmToken("foo"));
        verify(confirmationTokenRepository).findByToken((String) any());
        verify(confirmationToken).setConfirmedAt((LocalDateTime) any());
        verify(confirmationToken).setCreatedAt((LocalDateTime) any());
        verify(confirmationToken).setExpiredAt((LocalDateTime) any());
        verify(confirmationToken).setId(anyLong());
        verify(confirmationToken).setToken((String) any());
        verify(confirmationToken).setUser((User) any());
    }

    /**
     * Method under test: {@link UserService#confirmToken(String)}
     */
    @Test
    void testConfirmToken5() {
        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        ConfirmationToken confirmationToken = mock(ConfirmationToken.class);
        when(confirmationToken.getConfirmedAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        when(confirmationToken.getExpiredAt()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1));
        doNothing().when(confirmationToken).setConfirmedAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setCreatedAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setExpiredAt((LocalDateTime) any());
        doNothing().when(confirmationToken).setId(anyLong());
        doNothing().when(confirmationToken).setToken((String) any());
        doNothing().when(confirmationToken).setUser((User) any());
        confirmationToken.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setId(123L);
        confirmationToken.setToken("ABC123");
        confirmationToken.setUser(user);
        Optional.of(confirmationToken);

        User user1 = new User();
        user1.setAppUserRole(AppUserRole.USER);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setId(123L);
        user1.setLocked(true);
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");

        ConfirmationToken confirmationToken1 = new ConfirmationToken();
        confirmationToken1.setConfirmedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken1.setExpiredAt(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken1.setId(123L);
        confirmationToken1.setToken("ABC123");
        confirmationToken1.setUser(user1);
        ConfirmationTokenService confirmationTokenService = mock(ConfirmationTokenService.class);
        when(confirmationTokenService.getToken((String) any())).thenReturn(Optional.of(confirmationToken1));
        UserRepository userRepository = mock(UserRepository.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ConfirmationTokenRepository confirmationTokenRepository = mock(ConfirmationTokenRepository.class);
        UserDetailRepository userDetailRepository = mock(UserDetailRepository.class);
        PostRepository postRepository = mock(PostRepository.class);
        InterestRepository interestRepository = mock(InterestRepository.class);
        CommentRepository commentRepository = mock(CommentRepository.class);
        assertThrows(IllegalStateException.class,
                () -> (new UserService(userRepository, objectMapper, confirmationTokenRepository, userDetailRepository,
                        postRepository, interestRepository, commentRepository, new BCryptPasswordEncoder(),
                        confirmationTokenService)).confirmToken("foo"));
        verify(confirmationToken).setConfirmedAt((LocalDateTime) any());
        verify(confirmationToken).setCreatedAt((LocalDateTime) any());
        verify(confirmationToken).setExpiredAt((LocalDateTime) any());
        verify(confirmationToken).setId(anyLong());
        verify(confirmationToken).setToken((String) any());
        verify(confirmationToken).setUser((User) any());
        verify(confirmationTokenService).getToken((String) any());
    }
}

