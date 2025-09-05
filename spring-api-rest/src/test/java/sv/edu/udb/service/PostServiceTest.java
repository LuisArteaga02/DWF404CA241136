package sv.edu.udb.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.udb.repository.PostRepository;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.controller.request.PostRequest;
import sv.edu.udb.controller.responce.PostResponce;
import sv.edu.udb.service.implementation.PostServiceImpl;
import sv.edu.udb.service.mapper.PostMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @InjectMocks //Se utiliza esta anotacion para el bean que pondremos a prueba
    private PostServiceImpl postService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostMapper postMapper;
    private Post post;
    @BeforeEach
    void init() {
        this.post = Post
                .builder()
                .id(1L)
                .title("testing post")
                .postDate(LocalDate.of(2024, 9, 28))
                .build();
    }
    @Test
    @DisplayName("Find all the post when we have data")
    void shouldGetAllPostResponse_When_FindAllThePost() {
//Inicializamos los metodos que vamos a utilizar
//de las dependencias del servicio en este caso
//son el findAll del repositorio y la conversion del mapper
        when(postRepository.findAll()).thenReturn(List.of(post));
        when(postMapper.toPostResponce(anyList()))
                .thenReturn(
                        List.of(
                                PostResponce
                                        .builder()
                                        .title(this.post.getTitle())
                                        .postDate(this.post.getPostDate())
                                        .build()));
        final List<PostResponce> postResponseList = postService.findAll();
        assertNotNull(postResponseList);
        assertEquals(1, postResponseList.size());
        verify(postRepository, times(1))
                .findAll();
        verifyNoMoreInteractions(postRepository);
        verify(postMapper, times(1))
                .toPostResponce(anyList());
        verifyNoMoreInteractions(postMapper);
    }
    @Test
    @DisplayName("Find Post by id")
    void shouldGetPost_When_ExistPostWithId() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(this.post));
        when(postMapper.toPostResponce(any(Post.class))).thenReturn(PostResponce
                .builder()
                .title(this.post.getTitle())
                .postDate(this.post.getPostDate())
                .build());
        final PostResponce postResponse = postService.findById(1L);
        final String expectedTitle = "testing post";
        assertNotNull(postResponse);
        assertEquals(expectedTitle, postResponse.getTitle());
        verify(postRepository, times(1))
                .findById(anyLong());
        verifyNoMoreInteractions(postRepository);
        verify(postMapper, times(1))
                .toPostResponce(any(Post.class));
        verifyNoMoreInteractions(postMapper);

    }
    @Test
    @DisplayName("Find a no existed id then throws an exception")
    void shouldGetEntityNotFoundException_When_NoExistPostWithId() {
        when(postRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> {
            postService.findById(2L);
        });
        verify(postMapper, never())
                .toPostResponce(any(Post.class));
        verifyNoMoreInteractions(postMapper);
    }
    @Test
    @DisplayName("Save Post when the Post request is valid")
    void shouldSavePostEntity_When_PostRequestIsValid() {
        when(postMapper.toPost(any(PostRequest.class))).thenReturn(this.post);
        when(postRepository.save(any(Post.class))).thenReturn(this.post);
        when(postMapper.toPostResponce(any(Post.class)))
                .thenReturn(PostResponce
                        .builder()
                        .title(this.post.getTitle())
                        .postDate(this.post.getPostDate())
                        .build());
        final PostResponce postResponse = postService.save(PostRequest
                .builder()
                .title(this.post.getTitle())
                .postDate(this.post.getPostDate())
                .build());
        final String expectedTitle = "testing post";
        assertNotNull(postResponse);
        assertEquals(expectedTitle, postResponse.getTitle());
        verify(postMapper, times(1))
                .toPost(any(PostRequest.class));
        verify(postMapper, times(1))
                .toPostResponce(any(Post.class));
        verifyNoMoreInteractions(postMapper);
        verify(postRepository, times(1))
                .save(any(Post.class));
        verifyNoMoreInteractions(postRepository);
    }
    @Test
    @DisplayName("Should update Post When Post Request and Id are valid")
    void shouldUpdatePost_When_PostRequestAndIdAreValid() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(this.post));
        final String newTitle = "";
        final LocalDate newDate = LocalDate.of(2024, 10, 27);
        final Post newPost = Post
                .builder()
                .title(newTitle)
                .postDate(newDate)
                .build();
        when(postRepository.save(any(Post.class))).thenReturn(newPost);
        when(postMapper.toPostResponce(any(Post.class)))
                .thenReturn(PostResponce
                        .builder()
                        .title(newTitle)
                        .postDate(newDate)
                        .build());
        final PostRequest postRequest = PostRequest
                .builder()
                .title(newTitle)
                .postDate(newDate)
                .build();
        final PostResponce postResponse = postService.update(1L, postRequest);
        assertNotNull(postResponse);
        assertEquals(newTitle, postResponse.getTitle());
        assertEquals(newDate, postResponse.getPostDate());
        verify(postRepository, times(1))
                .findById(anyLong());
        verify(postRepository, times(1))
                .save(any(Post.class));
        verifyNoMoreInteractions(postRepository);
        verify(postMapper, times(1))
                .toPostResponce(any(Post.class));
        verifyNoMoreInteractions(postMapper);
    }
    @Test
    @DisplayName("Delete Post when post id exist")
    void shouldDeletePost_When_PostIdExist() {
//Cuando no haya necesidad de devolver objetos podemos
//usar el metodo de doNothing
        doNothing().when(postRepository).deleteById(anyLong());
        postService.delete(1L);
        verify(postRepository, times(1))
                .deleteById(anyLong());
        verifyNoMoreInteractions(postRepository);
    }
}
