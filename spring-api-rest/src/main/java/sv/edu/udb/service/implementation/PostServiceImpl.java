package sv.edu.udb.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import sv.edu.udb.controller.request.PostRequest;
import sv.edu.udb.controller.responce.PostResponce;
import sv.edu.udb.repository.PostRepository;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.service.PostService;
import sv.edu.udb.service.mapper.PostMapper;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @NonNull
    private final PostRepository postRepository;
    @NonNull
    private final PostMapper postMapper;
    @Override
    public List<PostResponce>findAll()
    {
        return postMapper.toPostResponce(postRepository.findAll());
    }

    @Override
    public PostResponce findById(final Long id)
    {
        return postMapper.toPostResponce(
                postRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Resource not found id " + id)));
    }
    @Override
    public PostResponce save(final PostRequest postRequest)
    {
        final Post post = postMapper.toPost(postRequest);
        return postMapper.toPostResponce(postRepository.save(post));
    }
    @Override
    public PostResponce update(final Long id, final PostRequest postRequest) {
        final Post postToUpdate = postRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Resource not found id " + id));
        postToUpdate.setTitle(postRequest.getTitle());
        postToUpdate.setPostDate(postRequest.getPostDate());
        postRepository.save(postToUpdate);
        return postMapper.toPostResponce(postToUpdate);
    }
    
    @Override
    public void delete(final Long id) {
        postRepository.deleteById(id);
    }
}
