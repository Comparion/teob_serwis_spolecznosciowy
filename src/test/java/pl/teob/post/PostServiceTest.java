//package pl.teob.post;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.anyLong;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.ArrayList;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Disabled;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import pl.teob.comment.CommentRepository;
//import pl.teob.interest.Interest;
//import pl.teob.interest.InterestRepository;
//import pl.teob.user.AppUserRole;
//import pl.teob.user.User;
//import pl.teob.user.UserRepository;
//
//class PostServiceTest {
//    /**
//     * Method under test: {@link PostService#addPost(PostDTO)}
//     */
//    @Test
//    void testAddPost() {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.save((Post) any())).thenReturn(post);
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        UserRepository userRepository = mock(UserRepository.class);
//        when(userRepository.findByUsername((String) any())).thenReturn(Optional.of(user1));
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        PostService postService = new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper());
//        ResponseEntity actualAddPostResult = postService.addPost(new PostDTO(123L, "janedoe", "Not all who wander are lost",
//                "Oxford", "Hello from the Dreaming Spires", 1L, 1L, true));
//        assertEquals("ok", actualAddPostResult.getBody());
//        assertEquals(HttpStatus.OK, actualAddPostResult.getStatusCode());
//        assertTrue(actualAddPostResult.getHeaders().isEmpty());
//        verify(postRepository).save((Post) any());
//        verify(userRepository).findByUsername((String) any());
//    }
//
//    /**
//     * Method under test: {@link PostService#addPost(PostDTO)}
//     */
//    @Test
//    void testAddPost2() {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.save((Post) any())).thenReturn(post);
//        User user1 = mock(User.class);
//        when(user1.getEnabled()).thenReturn(false);
//        doNothing().when(user1).setAppUserRole((AppUserRole) any());
//        doNothing().when(user1).setEmail((String) any());
//        doNothing().when(user1).setEnabled((Boolean) any());
//        doNothing().when(user1).setId(anyLong());
//        doNothing().when(user1).setLocked((Boolean) any());
//        doNothing().when(user1).setPassword((String) any());
//        doNothing().when(user1).setUsername((String) any());
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        UserRepository userRepository = mock(UserRepository.class);
//        when(userRepository.findByUsername((String) any())).thenReturn(Optional.of(user1));
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        PostService postService = new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper());
//        ResponseEntity actualAddPostResult = postService.addPost(new PostDTO(123L, "janedoe", "Not all who wander are lost",
//                "Oxford", "Hello from the Dreaming Spires", 1L, 1L, true));
//        assertNull(actualAddPostResult.getBody());
//        assertEquals(409, actualAddPostResult.getStatusCodeValue());
//        assertTrue(actualAddPostResult.getHeaders().isEmpty());
//        verify(userRepository).findByUsername((String) any());
//        verify(user1).getEnabled();
//        verify(user1).setAppUserRole((AppUserRole) any());
//        verify(user1).setEmail((String) any());
//        verify(user1).setEnabled((Boolean) any());
//        verify(user1).setId(anyLong());
//        verify(user1).setLocked((Boolean) any());
//        verify(user1).setPassword((String) any());
//        verify(user1).setUsername((String) any());
//    }
//
//    /**
//     * Method under test: {@link PostService#addPost(PostDTO)}
//     */
//    @Test
//    void testAddPost3() {
//        User user = mock(User.class);
//        when(user.getEnabled()).thenReturn(true);
//        doNothing().when(user).setAppUserRole((AppUserRole) any());
//        doNothing().when(user).setEmail((String) any());
//        doNothing().when(user).setEnabled((Boolean) any());
//        doNothing().when(user).setId(anyLong());
//        doNothing().when(user).setLocked((Boolean) any());
//        doNothing().when(user).setPassword((String) any());
//        doNothing().when(user).setUsername((String) any());
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user1);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.save((Post) any())).thenReturn(post);
//        UserRepository userRepository = mock(UserRepository.class);
//        when(userRepository.findByUsername((String) any())).thenReturn(Optional.empty());
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        PostService postService = new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper());
//        ResponseEntity actualAddPostResult = postService.addPost(new PostDTO(123L, "janedoe", "Not all who wander are lost",
//                "Oxford", "Hello from the Dreaming Spires", 1L, 1L, true));
//        assertNull(actualAddPostResult.getBody());
//        assertEquals(HttpStatus.UNAUTHORIZED, actualAddPostResult.getStatusCode());
//        assertTrue(actualAddPostResult.getHeaders().isEmpty());
//        verify(userRepository).findByUsername((String) any());
//        verify(user).setAppUserRole((AppUserRole) any());
//        verify(user).setEmail((String) any());
//        verify(user).setEnabled((Boolean) any());
//        verify(user).setId(anyLong());
//        verify(user).setLocked((Boolean) any());
//        verify(user).setPassword((String) any());
//        verify(user).setUsername((String) any());
//    }
//
//    /**
//     * Method under test: {@link PostService#addPost(PostDTO)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testAddPost4() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException: body is marked non-null but is null
//        //       at pl.teob.post.Post.<init>(Post.java:12)
//        //       at pl.teob.post.PostService.addPost(PostService.java:47)
//        //   In order to prevent addPost(PostDTO)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   addPost(PostDTO).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.save((Post) any())).thenReturn(post);
//        User user1 = mock(User.class);
//        when(user1.getEnabled()).thenReturn(true);
//        doNothing().when(user1).setAppUserRole((AppUserRole) any());
//        doNothing().when(user1).setEmail((String) any());
//        doNothing().when(user1).setEnabled((Boolean) any());
//        doNothing().when(user1).setId(anyLong());
//        doNothing().when(user1).setLocked((Boolean) any());
//        doNothing().when(user1).setPassword((String) any());
//        doNothing().when(user1).setUsername((String) any());
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        UserRepository userRepository = mock(UserRepository.class);
//        when(userRepository.findByUsername((String) any())).thenReturn(Optional.of(user1));
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        PostService postService = new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper());
//        postService.addPost(new PostDTO());
//    }
//
//    /**
//     * Method under test: {@link PostService#addPost(PostDTO)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testAddPost5() {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException
//        //       at pl.teob.post.PostService.addPost(PostService.java:40)
//        //   In order to prevent addPost(PostDTO)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   addPost(PostDTO).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.save((Post) any())).thenReturn(post);
//        User user1 = mock(User.class);
//        when(user1.getEnabled()).thenReturn(true);
//        doNothing().when(user1).setAppUserRole((AppUserRole) any());
//        doNothing().when(user1).setEmail((String) any());
//        doNothing().when(user1).setEnabled((Boolean) any());
//        doNothing().when(user1).setId(anyLong());
//        doNothing().when(user1).setLocked((Boolean) any());
//        doNothing().when(user1).setPassword((String) any());
//        doNothing().when(user1).setUsername((String) any());
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        UserRepository userRepository = mock(UserRepository.class);
//        when(userRepository.findByUsername((String) any())).thenReturn(Optional.of(user1));
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        (new PostService(postRepository, userRepository, interestRepository, commentRepository, new ObjectMapper()))
//                .addPost(null);
//    }
//
//    /**
//     * Method under test: {@link PostService#addPost(PostDTO)}
//     */
//    @Test
//    void testAddPost6() {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.save((Post) any())).thenReturn(post);
//        User user1 = mock(User.class);
//        when(user1.getEnabled()).thenReturn(true);
//        doNothing().when(user1).setAppUserRole((AppUserRole) any());
//        doNothing().when(user1).setEmail((String) any());
//        doNothing().when(user1).setEnabled((Boolean) any());
//        doNothing().when(user1).setId(anyLong());
//        doNothing().when(user1).setLocked((Boolean) any());
//        doNothing().when(user1).setPassword((String) any());
//        doNothing().when(user1).setUsername((String) any());
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        UserRepository userRepository = mock(UserRepository.class);
//        when(userRepository.findByUsername((String) any())).thenReturn(Optional.of(user1));
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        PostService postService = new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper());
//        PostDTO postDTO = mock(PostDTO.class);
//        when(postDTO.getBody()).thenReturn("Not all who wander are lost");
//        when(postDTO.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(postDTO.getTown()).thenReturn("Oxford");
//        when(postDTO.getUsername()).thenReturn("janedoe");
//        ResponseEntity actualAddPostResult = postService.addPost(postDTO);
//        assertEquals("ok", actualAddPostResult.getBody());
//        assertEquals(HttpStatus.OK, actualAddPostResult.getStatusCode());
//        assertTrue(actualAddPostResult.getHeaders().isEmpty());
//        verify(postRepository).save((Post) any());
//        verify(userRepository).findByUsername((String) any());
//        verify(user1).getEnabled();
//        verify(user1).setAppUserRole((AppUserRole) any());
//        verify(user1).setEmail((String) any());
//        verify(user1).setEnabled((Boolean) any());
//        verify(user1).setId(anyLong());
//        verify(user1).setLocked((Boolean) any());
//        verify(user1).setPassword((String) any());
//        verify(user1).setUsername((String) any());
//        verify(postDTO).getBody();
//        verify(postDTO).getSubject();
//        verify(postDTO).getTown();
//        verify(postDTO).getUsername();
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts() throws JsonProcessingException {
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(new ArrayList<>());
//        UserRepository userRepository = mock(UserRepository.class);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals("[]", actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts2() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals(
//                "[{\"id\":123,\"username\":\"janedoe\",\"body\":\"Not all who wander are lost\",\"town\":\"Oxford\",\"subject\":\"Hello"
//                        + " from the Dreaming Spires\",\"interests\":3,\"comments\":3,\"interestUser\":false}]",
//                actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts3() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        Post post = new Post();
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//
//        Post post1 = new Post();
//        post1.setBody("Not all who wander are lost");
//        post1.setId(123L);
//        post1.setSubject("Hello from the Dreaming Spires");
//        post1.setTown("Oxford");
//        post1.setUser(user1);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post1);
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals(
//                "[{\"id\":123,\"username\":\"janedoe\",\"body\":\"Not all who wander are lost\",\"town\":\"Oxford\",\"subject\":\"Hello"
//                        + " from the Dreaming Spires\",\"interests\":3,\"comments\":3,\"interestUser\":false},{\"id\":123,\"username\":"
//                        + "\"janedoe\",\"body\":\"Not all who wander are lost\",\"town\":\"Oxford\",\"subject\":\"Hello from the Dreaming"
//                        + " Spires\",\"interests\":3,\"comments\":3,\"interestUser\":false}]",
//                actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(interestRepository, atLeast(1)).countByPostId(anyLong());
//        verify(interestRepository, atLeast(1)).findAllByPostId(anyLong());
//        verify(commentRepository, atLeast(1)).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts4() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals(
//                "[{\"id\":123,\"username\":\"janedoe\",\"body\":\"Not all who wander are lost\",\"town\":\"Oxford\",\"subject\":\"Hello"
//                        + " from the Dreaming Spires\",\"interests\":3,\"comments\":3,\"interestUser\":false}]",
//                actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(post).getBody();
//        verify(post, atLeast(1)).getSubject();
//        verify(post, atLeast(1)).getTown();
//        verify(post, atLeast(1)).getId();
//        verify(post).getUser();
//        verify(post).setBody((String) any());
//        verify(post).setId(anyLong());
//        verify(post).setSubject((String) any());
//        verify(post).setTown((String) any());
//        verify(post).setUser((User) any());
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts5() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("foo");
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals("[]", actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(post).getSubject();
//        verify(post).getTown();
//        verify(post, atLeast(1)).getId();
//        verify(post).setBody((String) any());
//        verify(post).setId(anyLong());
//        verify(post).setSubject((String) any());
//        verify(post).setTown((String) any());
//        verify(post).setUser((User) any());
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testGetPosts6() throws JsonProcessingException {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException
//        //       at pl.teob.post.PostService.getPosts(PostService.java:70)
//        //   In order to prevent getPosts(String, String, String)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   getPosts(String, String, String).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn(null);
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        (new PostService(postRepository, userRepository, interestRepository, commentRepository, new ObjectMapper()))
//                .getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts7() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn("foo");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals("[]", actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(post).getTown();
//        verify(post, atLeast(1)).getId();
//        verify(post).setBody((String) any());
//        verify(post).setId(anyLong());
//        verify(post).setSubject((String) any());
//        verify(post).setTown((String) any());
//        verify(post).setUser((User) any());
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testGetPosts8() throws JsonProcessingException {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException
//        //       at pl.teob.post.PostService.getPosts(PostService.java:70)
//        //   In order to prevent getPosts(String, String, String)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   getPosts(String, String, String).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn(null);
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        (new PostService(postRepository, userRepository, interestRepository, commentRepository, new ObjectMapper()))
//                .getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts9() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//
//        User user2 = new User();
//        user2.setAppUserRole(AppUserRole.USER);
//        user2.setEmail("jane.doe@example.org");
//        user2.setEnabled(true);
//        user2.setId(123L);
//        user2.setLocked(true);
//        user2.setPassword("iloveyou");
//        user2.setUsername("janedoe");
//
//        Post post1 = new Post();
//        post1.setBody("Not all who wander are lost");
//        post1.setId(123L);
//        post1.setSubject("Hello from the Dreaming Spires");
//        post1.setTown("Oxford");
//        post1.setUser(user2);
//
//        User user3 = new User();
//        user3.setAppUserRole(AppUserRole.USER);
//        user3.setEmail("jane.doe@example.org");
//        user3.setEnabled(true);
//        user3.setId(123L);
//        user3.setLocked(true);
//        user3.setPassword("iloveyou");
//        user3.setUsername("janedoe");
//
//        Interest interest = new Interest();
//        interest.setId(123L);
//        interest.setPost(post1);
//        interest.setUser(user3);
//
//        ArrayList<Interest> interestList = new ArrayList<>();
//        interestList.add(interest);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(interestList);
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        UserRepository userRepository = mock(UserRepository.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, userRepository, interestRepository, commentRepository,
//                new ObjectMapper())).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals(
//                "[{\"id\":123,\"username\":\"janedoe\",\"body\":\"Not all who wander are lost\",\"town\":\"Oxford\",\"subject\":\"Hello"
//                        + " from the Dreaming Spires\",\"interests\":3,\"comments\":3,\"interestUser\":true}]",
//                actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(post).getBody();
//        verify(post, atLeast(1)).getSubject();
//        verify(post, atLeast(1)).getTown();
//        verify(post, atLeast(1)).getId();
//        verify(post).getUser();
//        verify(post).setBody((String) any());
//        verify(post).setId(anyLong());
//        verify(post).setSubject((String) any());
//        verify(post).setTown((String) any());
//        verify(post).setUser((User) any());
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testGetPosts10() throws JsonProcessingException {
//        // TODO: Complete this test.
//        //   Reason: R013 No inputs found that don't throw a trivial exception.
//        //   Diffblue Cover tried to run the arrange/act section, but the method under
//        //   test threw
//        //   java.lang.NullPointerException
//        //       at pl.teob.post.PostService.getPosts(PostService.java:82)
//        //   In order to prevent getPosts(String, String, String)
//        //   from throwing NullPointerException, add constructors or factory
//        //   methods that make it easier to construct fully initialized objects used in
//        //   getPosts(String, String, String).
//        //   See https://diff.blue/R013 to resolve this issue.
//
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        (new PostService(postRepository, mock(UserRepository.class), interestRepository, commentRepository, null))
//                .getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts11() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Class<Object> target = Object.class;
//        objectMapper.addMixIn(target, Object.class);
//        ResponseEntity actualPosts = (new PostService(postRepository, mock(UserRepository.class), interestRepository,
//                commentRepository, objectMapper)).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals(
//                "[{\"id\":123,\"username\":\"janedoe\",\"body\":\"Not all who wander are lost\",\"town\":\"Oxford\",\"subject\":\"Hello"
//                        + " from the Dreaming Spires\",\"interests\":3,\"comments\":3,\"interestUser\":false}]",
//                actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(post).getBody();
//        verify(post, atLeast(1)).getSubject();
//        verify(post, atLeast(1)).getTown();
//        verify(post, atLeast(1)).getId();
//        verify(post).getUser();
//        verify(post).setBody((String) any());
//        verify(post).setId(anyLong());
//        verify(post).setSubject((String) any());
//        verify(post).setTown((String) any());
//        verify(post).setUser((User) any());
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//    }
//
//    /**
//     * Method under test: {@link PostService#getPosts(String, String, String)}
//     */
//    @Test
//    void testGetPosts12() throws JsonProcessingException {
//        User user = new User();
//        user.setAppUserRole(AppUserRole.USER);
//        user.setEmail("jane.doe@example.org");
//        user.setEnabled(true);
//        user.setId(123L);
//        user.setLocked(true);
//        user.setPassword("iloveyou");
//        user.setUsername("janedoe");
//
//        User user1 = new User();
//        user1.setAppUserRole(AppUserRole.USER);
//        user1.setEmail("jane.doe@example.org");
//        user1.setEnabled(true);
//        user1.setId(123L);
//        user1.setLocked(true);
//        user1.setPassword("iloveyou");
//        user1.setUsername("janedoe");
//        Post post = mock(Post.class);
//        when(post.getBody()).thenReturn("Not all who wander are lost");
//        when(post.getUser()).thenReturn(user1);
//        when(post.getSubject()).thenReturn("Hello from the Dreaming Spires");
//        when(post.getTown()).thenReturn("Oxford");
//        when(post.getId()).thenReturn(123L);
//        doNothing().when(post).setBody((String) any());
//        doNothing().when(post).setId(anyLong());
//        doNothing().when(post).setSubject((String) any());
//        doNothing().when(post).setTown((String) any());
//        doNothing().when(post).setUser((User) any());
//        post.setBody("Not all who wander are lost");
//        post.setId(123L);
//        post.setSubject("Hello from the Dreaming Spires");
//        post.setTown("Oxford");
//        post.setUser(user);
//
//        ArrayList<Post> postList = new ArrayList<>();
//        postList.add(post);
//        PostRepository postRepository = mock(PostRepository.class);
//        when(postRepository.findAll()).thenReturn(postList);
//        InterestRepository interestRepository = mock(InterestRepository.class);
//        when(interestRepository.countByPostId(anyLong())).thenReturn(3);
//        when(interestRepository.findAllByPostId(anyLong())).thenReturn(new ArrayList<>());
//        CommentRepository commentRepository = mock(CommentRepository.class);
//        when(commentRepository.countByPostId(anyLong())).thenReturn(3);
//        ObjectMapper objectMapper = mock(ObjectMapper.class);
//        when(objectMapper.writeValueAsString((Object) any())).thenReturn("42");
//        ResponseEntity actualPosts = (new PostService(postRepository, mock(UserRepository.class), interestRepository,
//                commentRepository, objectMapper)).getPosts("janedoe", "Oxford", "Hello from the Dreaming Spires");
//        assertEquals("42", actualPosts.getBody());
//        assertEquals(HttpStatus.OK, actualPosts.getStatusCode());
//        assertTrue(actualPosts.getHeaders().isEmpty());
//        verify(postRepository).findAll();
//        verify(post).getBody();
//        verify(post, atLeast(1)).getSubject();
//        verify(post, atLeast(1)).getTown();
//        verify(post, atLeast(1)).getId();
//        verify(post).getUser();
//        verify(post).setBody((String) any());
//        verify(post).setId(anyLong());
//        verify(post).setSubject((String) any());
//        verify(post).setTown((String) any());
//        verify(post).setUser((User) any());
//        verify(interestRepository).countByPostId(anyLong());
//        verify(interestRepository).findAllByPostId(anyLong());
//        verify(commentRepository).countByPostId(anyLong());
//        verify(objectMapper).writeValueAsString((Object) any());
//    }
//}
//
