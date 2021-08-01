package com.drogbalog.server.domain.posts.domain.dto;

import com.drogbalog.server.global.exception.DrogbalogException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

import static com.drogbalog.server.global.exception.messages.IllegalDataExceptionType.ILLEGAL_POSTS_CARD_DATA;

@NoArgsConstructor
@Getter
public class PostsCardList {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int MAX_SIZE = 2;

    private List<PostsCard> postsCardList;
    private PostsCard firstPostsCard;
    private PostsCard secondPostsCard;

    public PostsCardList(List<PostsCard> postsCardList) {
        if (postsCardList.size() > MAX_SIZE) {
            throw new DrogbalogException(ILLEGAL_POSTS_CARD_DATA.getMessage());
        }

        this.postsCardList = postsCardList;
        this.firstPostsCard = postsCardList.get(FIRST_INDEX);
        this.secondPostsCard = postsCardList.get(SECOND_INDEX);
    }

    public PostsCard getPreviousPostsCard() {
        if (secondPostsCard.getId() > firstPostsCard.getId()) {
            return firstPostsCard;
        }

        return secondPostsCard;
    }

    public PostsCard getNextPostsCard() {
        if (firstPostsCard.getId() > secondPostsCard.getId()) {
            return firstPostsCard;
        }

        return secondPostsCard;
    }
}
