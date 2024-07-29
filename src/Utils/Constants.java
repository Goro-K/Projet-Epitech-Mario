package Utils;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int NOT_MOVING = 0;
        public static final int RUNNING_RIGHT = 1;
        public static final int DYING = 2;
        public static final int JUMPING_RIGHT = 3;
        public static final int JUMPING_LEFT = 4;
        public static final int RUNNING_LEFT = 5;

        public static int getSpriteAmount(int state) {
            switch (state) {
                case NOT_MOVING:
                    return 0;
                case RUNNING_RIGHT:
                    return 3;
                case DYING:
                    return 0;
                case JUMPING_RIGHT:
                    return 0;
                case JUMPING_LEFT:
                    return 0;
                case RUNNING_LEFT:
                    return 3;
                default:
                    return 0;
            }
        }
    }

}