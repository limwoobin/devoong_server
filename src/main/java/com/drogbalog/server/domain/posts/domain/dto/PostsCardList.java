package com.drogbalog.server.domain.posts.domain.dto;

import com.drogbalog.server.global.exception.DevoongException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.drogbalog.server.global.exception.messages.IllegalDataExceptionType.ILLEGAL_POSTS_CARD_DATA;

@NoArgsConstructor
@Getter
public class PostsCardList {
    private static final int PREVIOUS_INDEX = 0;
    private static final int NOW_INDEX = 1;
    private static final int NEXT_INDEX = 2;
    private static final int MAX_SIZE = 3;

    private List<PostsCard> postsCardList;
    private Long id;

    public PostsCardList(List<PostsCard> postsCardList , Long id) {
        if (postsCardList.size() > MAX_SIZE) {
            throw new DevoongException(ILLEGAL_POSTS_CARD_DATA.getMessage());
        }

        this.postsCardList = postsCardList;
        this.id = id;
    }

    public PostsCard getPreviousPostsCard() {
        if (postsCardList.isEmpty()) {
            return null;
        } else if (postsCardList.size() == 1) {
            return null;
        } else if (postsCardList.size() == 2 && postsCardList.get(PREVIOUS_INDEX).getId().equals(id)) {
            return null;
        }

        return postsCardList.get(PREVIOUS_INDEX);
    }

    public PostsCard getNextPostsCard() {
        if (postsCardList.isEmpty()) {
            return null;
        } else if (postsCardList.size() == 1) {
            return null;
        } else if (postsCardList.size() == 2 && postsCardList.get(NOW_INDEX).getId().equals(id)) {
            return null;
        } else if (postsCardList.size() == 2) {
            return postsCardList.get(NOW_INDEX);
        }

        return postsCardList.get(NEXT_INDEX);
    }
}
