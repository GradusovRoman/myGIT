package gb.xokyopo.servlets.dao.entity.template;

//TODO добавить проверку что бы не использовались запятые.
public abstract class AbstractProduct {
    private long id;
    private String name;
    private int price;
    private String currencyType;
    private String measureUnit;

    public long getId() {
        return id;
    }

    public void setId(long id) throws AddProductException {
        if (id <= 0 ) throw new AddProductException("id in not positive or zero ");
        else this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws AddProductException {
        if (name == null || name.equals("")) throw new AddProductException("name not set");
        else this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws AddProductException {
        if (price <= 0) throw new AddProductException("price is not positive or zero");
        else this.price = price;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) throws AddProductException {
        if (currencyType == null || currencyType.equals("")) throw new AddProductException("currencyType not set");
        this.currencyType = currencyType;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) throws AddProductException {
        if (measureUnit == null || measureUnit.equals("")) throw new AddProductException("measureUnit not set");
        else this.measureUnit = measureUnit;
    }

    public void setFields(long id, String name, int price, String currencyType, String measureUnit) throws AddProductException {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setCurrencyType(currencyType);
        this.setMeasureUnit(measureUnit);
    }

    public void printInfo() {
        System.out.printf("id = %s, name = %s, price = %s, currencyType = %s, measureUnit = %s \n", this.id, this.name, this.price, this.currencyType, this.measureUnit);
    }

    public abstract AbstractProduct createNew();
}
