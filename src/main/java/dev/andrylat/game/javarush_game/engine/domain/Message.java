package dev.andrylat.game.javarush_game.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Message {
    private Integer id;
    private String text;
    private List<Answer> answers;

    @Data
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Answer {
        String text;
        private Integer nextMessageId;
        private Integer questId;

        @Builder.Default
        private Boolean isFighting = false;
    }
}
