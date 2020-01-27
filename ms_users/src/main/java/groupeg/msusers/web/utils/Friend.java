package groupeg.msusers.web.utils;

public class Friend {
    private String pseudoOfUser;
    private String pseudoOfFriend;


    public Friend() {
    }

    public Friend(String pseudoOfUser, String pseudoOfFriend) {
        this.pseudoOfUser = pseudoOfUser;
        this.pseudoOfFriend = pseudoOfFriend;
    }

    public String getPseudoOfUser() {
        return pseudoOfUser;
    }

    public String getPseudoOfFriend() {
        return pseudoOfFriend;
    }
}
