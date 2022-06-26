package pl.teob.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.teob.comment.CommentRepository;
import pl.teob.detail.UserDetailRepository;
import pl.teob.interest.InterestRepository;
import pl.teob.post.PostRepository;
import pl.teob.user.token.ConfirmationTokenRepository;
import pl.teob.user.token.ConfirmationTokenService;

class UserControllerTest {
    /**
     * Method under test: {@link UserController#addUser(User)}
     */
    @Test
    void testAddUser() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.springframework.security.core.GrantedAuthority]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.security.core.GrantedAuthority` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (PushbackInputStream); line: 1, column: 150] (through reference chain: pl.teob.user.User["authorities"]->java.util.Collections$SingletonList[1])
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:386)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:342)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:186)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:158)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:131)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:170)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1060)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:962)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:183)
        //   In order to prevent addUser(User)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addUser(User).
        //   See https://diff.blue/R013 to resolve this issue.

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
        UserController userController = new UserController(new UserService(userRepository, objectMapper,
                confirmationTokenRepository, userDetailRepository, postRepository, interestRepository, commentRepository,
                bCryptPasswordEncoder, new ConfirmationTokenService(mock(ConfirmationTokenRepository.class))));

        User user3 = new User();
        user3.setAppUserRole(AppUserRole.USER);
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setId(123L);
        user3.setLocked(true);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        ResponseEntity actualAddUserResult = userController.addUser(user3);
        assertNull(actualAddUserResult.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualAddUserResult.getStatusCode());
        assertTrue(actualAddUserResult.getHeaders().isEmpty());
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserController#addUser(User)}
     */
    @Test
    void testAddUser2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.springframework.security.core.GrantedAuthority]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.security.core.GrantedAuthority` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (PushbackInputStream); line: 1, column: 150] (through reference chain: pl.teob.user.User["authorities"]->java.util.Collections$SingletonList[1])
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:386)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:342)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:186)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:158)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:131)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:170)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1060)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:962)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:183)
        //   In order to prevent addUser(User)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addUser(User).
        //   See https://diff.blue/R013 to resolve this issue.

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
        UserController userController = new UserController(new UserService(userRepository, objectMapper,
                confirmationTokenRepository, userDetailRepository, postRepository, interestRepository, commentRepository,
                bCryptPasswordEncoder, new ConfirmationTokenService(mock(ConfirmationTokenRepository.class))));

        User user2 = new User();
        user2.setAppUserRole(AppUserRole.USER);
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(123L);
        user2.setLocked(true);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        ResponseEntity actualAddUserResult = userController.addUser(user2);
        assertNull(actualAddUserResult.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualAddUserResult.getStatusCode());
        assertTrue(actualAddUserResult.getHeaders().isEmpty());
        verify(userRepository).findByEmail((String) any());
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserController#addUser(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.springframework.security.core.GrantedAuthority]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.security.core.GrantedAuthority` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (PushbackInputStream); line: 1, column: 150] (through reference chain: pl.teob.user.User["authorities"]->java.util.Collections$SingletonList[1])
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:386)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:342)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:186)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:158)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:131)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:170)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1060)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:962)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:183)
        //   In order to prevent addUser(User)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addUser(User).
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at pl.teob.user.UserController.addUser(UserController.java:25)
        //   In order to prevent addUser(User)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addUser(User).
        //   See https://diff.blue/R013 to resolve this issue.

        UserController userController = new UserController(null);

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        userController.addUser(user);
    }

    /**
     * Method under test: {@link UserController#addUser(User)}
     */
    @Test
    void testAddUser4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.springframework.security.core.GrantedAuthority]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.security.core.GrantedAuthority` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (PushbackInputStream); line: 1, column: 150] (through reference chain: pl.teob.user.User["authorities"]->java.util.Collections$SingletonList[1])
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:386)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:342)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:186)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:158)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:131)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:170)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:894)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1060)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:962)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:183)
        //   In order to prevent addUser(User)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addUser(User).
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.CONTINUE);
        when(userService.addUser((User) any())).thenReturn(responseEntity);
        UserController userController = new UserController(userService);

        User user = new User();
        user.setAppUserRole(AppUserRole.USER);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(123L);
        user.setLocked(true);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        assertSame(responseEntity, userController.addUser(user));
        verify(userService).addUser((User) any());
    }
}

