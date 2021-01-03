package bean.executor;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:46
 */
public class Market implements IExecutor{
    private int id;
    private String name;

    public Market(){

    }
    public Market(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void executeTask() {

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Market){
            return this.name.equals(((Market) obj).name);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
