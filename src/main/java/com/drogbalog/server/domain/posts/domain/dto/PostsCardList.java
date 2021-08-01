package com.drogbalog.server.domain.posts.domain.dto;

import com.drogbalog.server.global.exception.DrogbalogException;
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

    public PostsCardList(List<PostsCard> postsCardList) {
        if (postsCardList.size() > MAX_SIZE) {
            throw new DrogbalogException(ILLEGAL_POSTS_CARD_DATA.getMessage());
        }

        this.postsCardList = postsCardList;
    }

    public PostsCard getPreviousPostsCard() {
        if (postsCardList.get(NOW_INDEX) == null) {
            return null;
        }
        return postsCardList.get(PREVIOUS_INDEX);
    }

    public PostsCard getNextPostsCard() {
        if (postsCardList.get(NEXT_INDEX) == null) {
            return null;
        }

        return postsCardList.get(NEXT_INDEX);
    }
}
