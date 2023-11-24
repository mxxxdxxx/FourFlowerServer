package controller;

public class UserForm {

    private String name;

    /**
     * post 방식으로 "name" id에 걸려서 이름을 얻어오게 됨
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
