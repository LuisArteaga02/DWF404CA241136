package sv.edu.udb.service.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Component;
import sv.edu.udb.controller.request.PostRequest;
import sv.edu.udb.controller.responce.PostResponce;
import sv.edu.udb.repository.domain.Post;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponce toPostResponce(final Post data);
    List<PostResponce> toPostResponce(final List<Post> postList);
    Post toPost(final PostRequest postRequest);
}
