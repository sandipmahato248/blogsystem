package miu.edu.upvote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpvoteService {

    @Autowired
    private IUpvoteDAO iUpvoteDAO;

    public ResponseEntity<List<Upvote>> getAllUpvotes(){
        List<Upvote> upvotes = iUpvoteDAO.findAll();
        return new ResponseEntity<>(upvotes, HttpStatus.OK);
    }

    public ResponseEntity<Object> saveUpvote(Upvote upvote){
        Upvote savedUpvote = iUpvoteDAO.save(upvote);
        return new ResponseEntity<>(savedUpvote, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUpvote(Long id){
        Optional<Upvote> upvoteOptional = iUpvoteDAO.findById(id);
        if(upvoteOptional.isEmpty()){
            return new ResponseEntity<>("Doesn't exist", HttpStatus.NOT_FOUND);
        }
        iUpvoteDAO.delete(upvoteOptional.get());
        return new ResponseEntity<>("Succesfull!", HttpStatus.OK);
    }

    public ResponseEntity<Object> getUpvoteForArticle(Long articleId){
        Optional<Upvote> optionalUpvote = iUpvoteDAO.findByArticleId(articleId);
        if(optionalUpvote.isEmpty()){
            return new ResponseEntity<>("No upvote for this article", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUpvote.get(), HttpStatus.OK);
    }



    public ResponseEntity<Object> getUpvoteForUser(Long userId){
        Optional<Upvote> optionalUpvote = iUpvoteDAO.findByArticleId(userId);
        if(optionalUpvote.isEmpty()){
            return new ResponseEntity<>("No upvote for this user", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUpvote.get(), HttpStatus.OK);
    }







}
