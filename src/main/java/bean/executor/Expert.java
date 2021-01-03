package bean.executor;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:42
 */
public class Expert implements IExecutor{
    private int id;
    private String name;

    public Expert(){

    }
    public Expert(String name){
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
        if(obj instanceof Expert){
            return this.name.equals(((Expert) obj).name);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
