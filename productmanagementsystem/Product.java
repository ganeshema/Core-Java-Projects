package productmanagementsystem;

public class Product {
    private int productId;
    private String productName;
    private String productType;
    private String productColor;

    public Product(int productId, String productName, String productType, String productColor) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productColor = productColor;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productColor='" + productColor + '\'' +
                '}';
    }
}
