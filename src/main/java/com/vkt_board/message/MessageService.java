package com.vkt_board.message;

import com.vkt_board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public Message get (Integer id) {
        return messageRepository.findById(id)
                                .orElse(null);
    }

    public Message create (Message message, Integer userId) {

        if (!userService.isExist(userId)) {
            // creating is only for registered users
            return null;
        }

        message.setUserId(userId);
        message.setRating(0);
        message.setVotedUserId("");

        return messageRepository.save(message);
    }

    public Message update (Message message, Integer userId) {

        Message messageFromDB = messageRepository.findById(message.getId())
                                                 .orElse(null);

        if (messageFromDB == null || messageFromDB.getUserId() != userId) {
            // updating is only for owner of message
            return null;
        }

        // TODO: TBD behaviors - update rating/voteduserid to empty???
        messageFromDB.setText(message.getText());

        return messageRepository.save(messageFromDB);
    }

    public void delete (Integer messageId, Integer userId) {

        Message message = messageRepository.findById(messageId)
                                           .orElse(null);

        if (message == null || message.getUserId() != userId) {
            // deleting is only for owner of message
            return;
        }

        messageRepository.deleteById(messageId);
    }

    public List<Message> getAll () {
        return (List<Message>) messageRepository.findAll();
    }

    public Page<Message> getTopLit (Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return messageRepository.getTopList(pageable);
    }

    public Message vote (Integer messageId, Integer userId, Boolean isLike) {

        if (!userService.isExist(userId)) {
            // voting is only for registered users
            return null;
        }

        Message message = messageRepository.findById(messageId)
                                           .orElse(null);

        if (message == null
                    || message.getUserId() == userId
                    || (message.getVotedUserId() != null && message.getVotedUserId().contains(userId.toString()))) {
            // there is no message or user has voted this message before or it's owner of the message
            return null;
        }

        message.setRating(isLike ? message.getRating() + 1 : message.getRating() - 1);
        message.setVotedUserId(String.format("%s_%s", message.getVotedUserId(), userId));

        return messageRepository.save(message);
    }


}
