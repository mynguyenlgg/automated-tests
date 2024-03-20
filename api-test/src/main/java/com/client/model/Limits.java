package com.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Limits {
    private Attachments attachments;

    @Getter
    @Setter
    public static class Attachments {
        private LimitsObject perBoard;

        @Getter
        @Setter
        public static class LimitsObject {
            private String status;
            private int disableAt;
            private int warnAt;
        }
    }
}
