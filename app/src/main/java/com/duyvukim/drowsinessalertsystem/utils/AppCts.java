package com.duyvukim.drowsinessalertsystem.utils;

public class AppCts {
    public static class Notifications {
        public final static String NOTIFICATION_CHANNEL = "drowsy_alert";
        public final static int NOTIFICATION_ID = 2001;
    }

    public static class Identities {
        public final static String EXAM_CODE = "12345";
    }

    public static class Thresholds {
        public final static int FRAMES_CLOSED_THRESHOLD = 200;
        public final static int FRAMES_MULTIPLE_PEOPLE_THRESHOLD = 200;
        public static final int FRAMES_HEAD_POSE_PROBLEM_THRESHOLD = 200;
        public final static float LEFT_EYE_CLOSED_THRESHOLD = 0.2f;
        public final static float RIGHT_EYE_CLOSED_THRESHOLD = 0.2f;

    }
}
