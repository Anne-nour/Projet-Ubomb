package fr.ubx.poo.view.image;

public enum ImageResource {
    BANNER_BOMB ("banner_bomb.png"),
    BANNER_RANGE ("banner_range.png"),
    HEART("heart.png"),
    KEY("key.png"),
    DIGIT_0 ("banner_0.jpg"),
    DIGIT_1 ("banner_1.jpg"),
    DIGIT_2 ("banner_2.jpg"),
    DIGIT_3 ("banner_3.jpg"),
    DIGIT_4 ("banner_4.jpg"),
    DIGIT_5 ("banner_5.jpg"),
    DIGIT_6 ("banner_6.jpg"),
    DIGIT_7 ("banner_7.jpg"),
    DIGIT_8 ("banner_8.jpg"),
    DIGIT_9 ("banner_9.jpg"),
    PLAYER_DOWN("player_down.png"),
    PLAYER_LEFT("player_left.png"),
    PLAYER_RIGHT("player_right.png"),
    PLAYER_UP("player_up.png"),
    STONE("stone.png"),
    TREE("tree.png"),
    BOX("box.png"),
    BOMB_NUMBER_INC("bonus_bomb_nb_inc.png"),
    BOMB_NUMBER_DEC("bonus_bomb_nb_dec.png"),
    BOMB_RANGE_INC("bonus_bomb_range_inc.png"),
    BOMB_RANGE_DEC("bonus_bomb_range_dec.png"),
    DOOR_NEXT_CLOSED("door_closed.png"),
    DOOR_NEXT_OPENED("door_opened.png"),
    DOOR_PREV_OPENED("door_opened.png"),
    PRINCESS("bomberwoman.png"),
    MONSTER("monster_down.png"),
    BOMB1("bomb1.png"),
    BOMB2("bomb2.png"),
    BOMB3("bomb3.png"),
    BOMB4("bomb4.png"),
    BOMB("banner_bomb.png"),
    EXPLOSION("explosion.png")
    ;

    private final String FileName;

    ImageResource(String fileName) {
        this.FileName = fileName;
    }

    public String getFileName() {
        return FileName;
    }
}
