package bean;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 20:02
 */
public class Product {
    private int id;
    private String name;

    public Product(){

    }
    public Product(String name){
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Product){
            return this.name.equals(((Product) obj).name);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
