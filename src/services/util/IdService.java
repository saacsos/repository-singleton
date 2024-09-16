package services.util;

public class IdService {
    private String lastId;

    public IdService() {
        lastId = "0";
    }

    public String getLastId() {
        return lastId;
    }

    public void compareLastId(HasId object) {
        int lastInt = Integer.parseInt(lastId);
        int objectId = Integer.parseInt(object.getId());
        lastId = "" + Math.max(lastInt, objectId);
    }

    public void setNextId(HasId object) {
        int lastInt = Integer.parseInt(lastId);
        lastInt++;
        lastId = "" + lastInt;
        object.setId(lastId);
    }
}
