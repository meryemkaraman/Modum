package modum.service;

import modum.entity.Comment;
import modum.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPlace(Long placeId) {
        return commentRepository.findByPlaceId(placeId);
    }
}