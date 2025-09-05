package sv.edu.udb.service;

import sv.edu.udb.controller.request.PostRequest;
import sv.edu.udb.controller.responce.PostResponce;
import sv.edu.udb.repository.domain.Post;

import java.util.List;
public interface PostService {

    List<PostResponce> findAll();
    PostResponce findById(final Long id);
    PostResponce save(final PostRequest postRequest);
    PostResponce update(final Long id, final PostRequest postRequest);
    void delete(final Long id);
}
